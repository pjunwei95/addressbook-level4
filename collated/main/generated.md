# generated
###### /java/seedu/address/logic/commands/UndoableCommand.java
``` java

        //Revert font size
        if (this instanceof ChangeFontSizeCommand) {
            FontSize.setCurrentFontSizeLabel(previousFontSize);
            EventsCenter.getInstance().post(new ChangeFontSizeEvent("", previousFontSize));
        }
    }

    /**
     * Executes the command and updates the filtered person
     * list to show all persons.
     */
    protected final void redo() {
        requireNonNull(model);
        try {
            executeUndoableCommand();
        } catch (CommandException ce) {
            throw new AssertionError("The command has been successfully executed previously; "
                    + "it should not fail now");
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
```
###### /java/seedu/address/logic/commands/UndoableCommand.java
``` java
    }

    @Override
    public final CommandResult execute() throws CommandException {
        saveAddressBookSnapshot();
        return executeUndoableCommand();
    }
}
```
###### /java/seedu/address/logic/Logic.java
``` java

    /** Returns the list of input entered by the user, encapsulated in a {@code ListElementPointer} object */
    ListElementPointer getHistorySnapshot();
}
```
###### /java/seedu/address/logic/LogicManager.java
``` java
    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }
}
```
###### /java/seedu/address/logic/parser/AddCommandParser.java
``` java
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            Remark remark;
            DateOfBirth date;
            FacebookUsername username;
            if (ParserUtil.parseDateOfBirth(argMultimap.getValue(PREFIX_DOB)).isPresent()) {
                date = ParserUtil.parseDateOfBirth(argMultimap.getValue(PREFIX_DOB)).get();
            } else {
                date = new DateOfBirth("");;
            }
            if (ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK)).isPresent()) {
                remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK)).get();
            } else {
                remark = new Remark("");
            }
```
###### /java/seedu/address/logic/parser/ParserUtil.java
``` java

    /**
     * Parses a {@code Optional<String> address} into an {@code Optional<Address>} if {@code address} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Address> parseAddress(Optional<String> address) throws IllegalValueException {
        requireNonNull(address);
        return address.isPresent() ? Optional.of(new Address(address.get())) : Optional.empty();
    }

```
###### /java/seedu/address/logic/parser/ParserUtil.java
``` java
    /**
     * Parses a {@code Optional<String> email} into an {@code Optional<Email>} if {@code email} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Email> parseEmail(Optional<String> email) throws IllegalValueException {
        requireNonNull(email);
        return email.isPresent() ? Optional.of(new Email(email.get())) : Optional.empty();
    }

    /**
     * Parses a {@code Optional<String> tagColor} into an {@code Optional<TagColor>} if {@code tagColor} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<TagColor> parseTagColor(Optional<String> tagColor) throws IllegalValueException {
        requireNonNull(tagColor);
        return tagColor.isPresent() ? Optional.of(new TagColor(tagColor.get())) : Optional.empty();
    }

    /**
     * Parses a {@code Optional<String> fontSize} into an {@code Optional<FontSize>} if {@code fontSize} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<FontSize> parseFontSize(Optional<String> fontSize) throws IllegalValueException {
        requireNonNull(fontSize);
        return fontSize.isPresent() ? Optional.of(new FontSize(fontSize.get())) : Optional.empty();
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws IllegalValueException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        return tagSet;
    }
}
```
###### /java/seedu/address/model/AddressBook.java
``` java
    private final UniqueTagList tags;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
```
###### /java/seedu/address/model/AddressBook.java
``` java
        persons = new UniquePersonList();
        tags = new UniqueTagList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons, Reminders and Tags in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    public void setPersons(List<? extends ReadOnlyPerson> persons) throws DuplicatePersonException {
        this.persons.setPersons(persons);
    }

    public void setTags(Set<Tag> tags) {
        this.tags.setTags(tags);
    }

```
###### /java/seedu/address/model/AddressBook.java
``` java
    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        try {
            setReminders(newData.getReminderList());
            setPersons(newData.getPersonList());
        } catch (DuplicatePersonException e) {
            assert false : "AddressBooks should not have duplicate persons";
        } catch (DuplicateReminderException ee) {
            assert false : "AddressBooks should not have duplicate reminders";
        }

        setTags(new HashSet<>(newData.getTagList()));
        syncMasterTagListWith(persons);
    }

    //// person-level operations

    /**
     * Adds a person to the address book.
     * Also checks the new person's tags and updates {@link #tags} with any new tags found,
     * and updates the Tag objects in the person to point to those in {@link #tags}.
     *
     * @throws DuplicatePersonException if an equivalent person already exists.
     */
    public void addPerson(ReadOnlyPerson p) throws DuplicatePersonException {
        Person newPerson = new Person(p);
        syncMasterTagListWith(newPerson);
        // TODO: the tags master list will be updated even though the below line fails.
        // This can cause the tags master list to have additional tags that are not tagged to any person
        // in the person list.
        persons.add(newPerson);
    }
```
###### /java/seedu/address/model/AddressBook.java
``` java

    /**
     * Replaces the given person {@code target} in the list with {@code editedReadOnlyPerson}.
     * {@code AddressBook}'s tag list will be updated with the tags of {@code editedReadOnlyPerson}.
     *
     * @throws DuplicatePersonException if updating the person's details causes the person to be equivalent to
     *      another existing person in the list.
     * @throws PersonNotFoundException if {@code target} could not be found in the list.
     *
     * @see #syncMasterTagListWith(Person)
     */
    public void updatePerson(ReadOnlyPerson target, ReadOnlyPerson editedReadOnlyPerson)
            throws DuplicatePersonException, PersonNotFoundException {
        requireNonNull(editedReadOnlyPerson);

        Person editedPerson = new Person(editedReadOnlyPerson);
        syncMasterTagListWith(editedPerson);
        // TODO: the tags master list will be updated even though the below line fails.
        // This can cause the tags master list to have additional tags that are not tagged to any person
        // in the person list.
        persons.setPerson(target, editedPerson);
    }

```
###### /java/seedu/address/model/AddressBook.java
``` java
    /**
     * Ensures that every tag in this person:
     *  - exists in the master list {@link #tags}
     *  - points to a Tag object in the master list
     */
    private void syncMasterTagListWith(Person person) {
        final UniqueTagList personTags = new UniqueTagList(person.getTags());
        tags.mergeFrom(personTags);

        // Create map with values = tag object references in the master list
        // used for checking person tag references
        final Map<Tag, Tag> masterTagObjects = new HashMap<>();
        tags.forEach(tag -> masterTagObjects.put(tag, tag));

        // Rebuild the list of person tags to point to the relevant tags in the master tag list.
        final Set<Tag> correctTagReferences = new HashSet<>();
        personTags.forEach(tag -> correctTagReferences.add(masterTagObjects.get(tag)));
        person.setTags(correctTagReferences);
    }

    /**
     * Ensures that every tag in these persons:
     *  - exists in the master list {@link #tags}
     *  - points to a Tag object in the master list
     *  @see #syncMasterTagListWith(Person)
     */
    private void syncMasterTagListWith(UniquePersonList persons) {
        persons.forEach(this::syncMasterTagListWith);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * @throws PersonNotFoundException if the {@code key} is not in this {@code AddressBook}.
     */
    public boolean removePerson(ReadOnlyPerson key) throws PersonNotFoundException {
        if (persons.remove(key)) {
            return true;
        } else {
            throw new PersonNotFoundException();
        }
    }

```
###### /java/seedu/address/model/AddressBook.java
``` java
    //// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        tags.add(t);
    }

    /**
     * Update the tag color pair in storage
     * @param modifyingTagList tags that need to be changed color
     * @param color
     * @throws IllegalValueException
     */
    public void updateTagColorPair(Set<Tag> modifyingTagList, TagColor color) throws IllegalValueException {
        // Set the list of tags to new list of tags
        setTags(getUpdatedTagColorPair(modifyingTagList, tags.toSet(), color));

        updateTagColorInEveryPerson(modifyingTagList, color);
    }

    /**
     * Get a list of given tags with updated color
     * @param modifyingTagList
     * @param existingTagList
     * @param color
     * @return list of updated tags
     * @throws IllegalValueException
     */
    private Set<Tag> getUpdatedTagColorPair(Set<Tag> modifyingTagList, Set<Tag> existingTagList, TagColor color)
            throws IllegalValueException {
        // To store updated list of tags
        Set<Tag> updatedTags = new HashSet<>();

        for (Tag existingTag: existingTagList) {
            for (Tag modifyingTag: modifyingTagList) {

                // Check whether a tag needs to be changed its color
                if (modifyingTag.equals(existingTag)) {

                    // Change the color of the tag
                    updatedTags.add(new Tag(modifyingTag.tagName, color.tagColorName));

                }
            }

            // This tag doesn't need to be changed
            if (!updatedTags.contains(existingTag)) {
                // Remain unchanged
                updatedTags.add(existingTag);
            }
        }

        return updatedTags;

    }

    /**
     * Update tag and color pair in every person
     */
    private void updateTagColorInEveryPerson(Set<Tag> modifyingTagList, TagColor tagColor)
            throws IllegalValueException {
        for (Person person : persons) {
            Set<Tag> updatedTagList = getUpdatedTagColorPair(modifyingTagList, person.getTags(), tagColor);
            person.setTags(updatedTagList);
        }
    }


    //// util methods

    @Override
    public String toString() {
        return persons.asObservableList().size() + " persons, " + tags.asObservableList().size() +  " tags";
        // TODO: refine later
    }

    @Override
    public ObservableList<ReadOnlyPerson> getPersonList() {
        return persons.asObservableList();
    }

```
###### /java/seedu/address/model/AddressBook.java
``` java

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && this.persons.equals(((AddressBook) other).persons)
                && this.reminders.equals(((AddressBook) other).reminders)
                && this.tags.equalsOrderInsensitive(((AddressBook) other).tags));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(persons, tags);
    }

}
```
###### /java/seedu/address/model/Model.java
``` java
    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyAddressBook newData);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Deletes the given person. */
    void deletePerson(ReadOnlyPerson target) throws PersonNotFoundException;

    /** Adds the given person */
    void addPerson(ReadOnlyPerson person) throws DuplicatePersonException;

```
###### /java/seedu/address/model/Model.java
``` java
    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<ReadOnlyPerson> getFilteredPersonList();

```
###### /java/seedu/address/model/Model.java
``` java
    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<ReadOnlyPerson> predicate);

    /**
     * Update color of tags
     * @param tagList
     * @param color
     */
    void updateTagColorPair(Set<Tag> tagList, TagColor color) throws IllegalValueException;

    /**
     * Updates the filter of the filtered reminder list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredReminderList(Predicate<ReadOnlyReminder> predicate);



}
```
###### /java/seedu/address/model/ModelManager.java
``` java

    @Override
    public synchronized void addPerson(ReadOnlyPerson person) throws DuplicatePersonException {

        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        indicateAddressBookChanged();

    }
```
###### /java/seedu/address/model/ModelManager.java
``` java
    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code ReadOnlyPerson} backed by the internal list of
     * {@code addressBook}
     */
    @Override
    public ObservableList<ReadOnlyPerson> getFilteredPersonList() {
        return FXCollections.unmodifiableObservableList(filteredPersons);
    }
```
###### /java/seedu/address/model/person/Person.java
``` java

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, DateOfBirth dateOfBirth,
                  Remark remark, FileImage image, FacebookUsername username, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = new SimpleObjectProperty<>(name);
        this.phone = new SimpleObjectProperty<>(phone);
        this.email = new SimpleObjectProperty<>(email);
        this.address = new SimpleObjectProperty<>(address);
```
###### /java/seedu/address/model/person/Person.java
``` java

    public void setPhone(Phone phone) {
        this.phone.set(requireNonNull(phone));
    }

    @Override
    public ObjectProperty<Phone> phoneProperty() {
        return phone;
    }

    @Override
    public Phone getPhone() {
        return phone.get();
    }

    public void setEmail(Email email) {
        this.email.set(requireNonNull(email));
    }

    @Override
    public ObjectProperty<Email> emailProperty() {
        return email;
    }

    @Override
    public Email getEmail() {
        return email.get();
    }

    public void setAddress(Address address) {
        this.address.set(requireNonNull(address));
    }

    @Override
    public ObjectProperty<Address> addressProperty() {
        return address;
    }

    @Override
    public Address getAddress() {
        return address.get();
    }

```
###### /java/seedu/address/model/person/Person.java
``` java

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    @Override
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags.get().toSet());
    }

    public ObjectProperty<UniqueTagList> tagProperty() {
        return tags;
    }

```
###### /java/seedu/address/model/person/ReadOnlyPerson.java
``` java
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
```
###### /java/seedu/address/storage/XmlSerializableAddressBook.java
``` java

    /**
     * Creates an empty XmlSerializableAddressBook.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableAddressBook() {
        persons = new ArrayList<>();
        tags = new ArrayList<>();
```
###### /java/seedu/address/storage/XmlSerializableAddressBook.java
``` java
    }

    /**
     * Conversion
     */
    public XmlSerializableAddressBook(ReadOnlyAddressBook src) {
        this();
        reminders.addAll(src.getReminderList().stream().map(XmlAdaptedReminder::new).collect(Collectors.toList()));
```
###### /java/seedu/address/storage/XmlSerializableAddressBook.java
``` java
        tags.addAll(src.getTagList().stream().map(XmlAdaptedTag::new).collect(Collectors.toList()));
    }

    @Override
    public ObservableList<ReadOnlyPerson> getPersonList() {
        final ObservableList<ReadOnlyPerson> persons = this.persons.stream().map(p -> {
            try {
                return p.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                //TODO: better error handling
                return null;
            }
        }).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return FXCollections.unmodifiableObservableList(persons);
    }
```
###### /java/seedu/address/storage/XmlSerializableAddressBook.java
``` java
    @Override
    public ObservableList<Tag> getTagList() {
        final ObservableList<Tag> tags = this.tags.stream().map(t -> {
            try {
                return t.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                //TODO: better error handling
                return null;
            }
        }).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return FXCollections.unmodifiableObservableList(tags);
    }

}
```
###### /java/seedu/address/ui/MainWindow.java
``` java
    private Config config;
    private UserPrefs prefs;
    private Storage storage;
    private AccountsStorage accPrefs;

    private CommandBox commandBox;

    @FXML
    private StackPane browserPlaceholder;
```
###### /java/seedu/address/ui/MainWindow.java
``` java

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    public MainWindow(Stage primaryStage, Config config, Storage storage, UserPrefs prefs, Logic logic,
                      AccountsStorage accPrefs) {
        super(FXML);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.config = config;
        this.prefs = prefs;
        this.storage = storage;
        this.accPrefs = accPrefs;

        // Configure the UI
        setTitle(config.getAppTitle());
        setIcon(ICON);
        setWindowMinSize();
        setWindowDefaultSize(prefs);
        Scene scene = new Scene(getRoot());
        primaryStage.setScene(scene);

        setAccelerators();
        registerAsAnEventHandler(this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BrowserPanel getBrowserPanel() {
        return browserPanel;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        browserPanel = new BrowserPanel();
        browserPlaceholder.getChildren().add(browserPanel.getRoot());

        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

```
###### /java/seedu/address/ui/MainWindow.java
``` java

        ResultDisplay resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(prefs.getAddressBookFilePath(),
                logic.getFilteredPersonList().size());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        this.commandBox = new CommandBox(logic);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    void hide() {
        primaryStage.hide();
    }

    private void setTitle(String appTitle) {
        primaryStage.setTitle(appTitle);
    }

    /**
     * Sets the given image as the icon of the main window.
     *
     * @param iconSource e.g. {@code "/images/help_icon.png"}
     */
    private void setIcon(String iconSource) {
        FxViewUtil.setStageIcon(primaryStage, iconSource);
    }

    /**
     * Sets the default size based on user preferences.
     */
    private void setWindowDefaultSize(UserPrefs prefs) {
        primaryStage.setHeight(prefs.getGuiSettings().getWindowHeight());
        primaryStage.setWidth(prefs.getGuiSettings().getWindowWidth());
        FontSize.setCurrentFontSizeLabel(prefs.getGuiSettings().getFontSize());
        if (prefs.getGuiSettings().getWindowCoordinates() != null) {
            primaryStage.setX(prefs.getGuiSettings().getWindowCoordinates().getX());
            primaryStage.setY(prefs.getGuiSettings().getWindowCoordinates().getY());
        }
    }

    private void setWindowMinSize() {
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
    }

    /**
     * Returns the current size and the position of the main Window.
     */
    GuiSettings getCurrentGuiSetting() {
        return new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY(), FontSize.getCurrentFontSizeLabel());
    }

    /**
     * Opens the help window.
     */
    @FXML
    public void handleHelp() {
        HelpWindow helpWindow = new HelpWindow();
        helpWindow.show();
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        raise(new ExitAppRequestEvent());
    }

    /**
     * Method for handle logout event.
     */
    @FXML
    private void handleLogoutEvent() throws IOException {
        logger.info("Trying to logout");
        this.hide();
        this.releaseResources();
        prefs.updateLastUsedGuiSetting(this.getCurrentGuiSetting());
        LoginPage loginPage = new LoginPage(primaryStage, config, storage, prefs, logic, accPrefs);
        loginPage.show();
    }

    public PersonListPanel getPersonListPanel() {
        return this.personListPanel;
    }

```
###### /java/seedu/address/ui/MainWindow.java
``` java

    void releaseResources() {
        browserPanel.freeResources();
    }

    @Subscribe
    private void handleShowHelpEvent(ShowHelpRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleHelp();
    }

    /**
     * Increase the font size.
     */
    @FXML
    private void handleIncreaseFontSize() throws CommandException, ParseException {
        commandBox.handleCommandInputChanged(FontSize.INCREASE_FONT_SIZE_COMMAND);
    }

    /**
     * Decrease the font size.
     */
    @FXML
    private void handleDecreaseFontSize() throws CommandException, ParseException {
        commandBox.handleCommandInputChanged(FontSize.DECREASE_FONT_SIZE_COMMAND);
    }

}
```
###### /java/seedu/address/ui/PersonCard.java
``` java

    /**
     * Binds the individual UI elements to observe their respective {@code Person} properties
     * so that they will be notified of any changes.
     */
    private void bindListeners(ReadOnlyPerson person) {
        name.textProperty().bind(Bindings.convert(person.nameProperty()));
        phone.textProperty().bind(Bindings.convert(person.phoneProperty()));
        address.textProperty().bind(Bindings.convert(person.addressProperty()));
        date.textProperty().bind(Bindings.convert(person.dateOfBirthProperty()));
        email.textProperty().bind(Bindings.convert(person.emailProperty()));
        remark.textProperty().bind(Bindings.convert(person.remarkProperty()));
        person.tagProperty().addListener((observable, oldValue, newValue) -> {
            tags.getChildren().clear();

            initTags(person, FontSize.getCurrentFontSizeLabel());

        });
```
