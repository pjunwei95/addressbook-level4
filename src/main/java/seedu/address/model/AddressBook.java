package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.reminder.ReadOnlyReminder;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.UniqueReminderList;
import seedu.address.model.reminder.exceptions.DuplicateReminderException;
import seedu.address.model.reminder.exceptions.ReminderNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagColor;
import seedu.address.model.tag.UniqueTagList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .equals comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    //@@author RonakLakhotia
    private final UniqueReminderList reminders;
    //@@author generated
    private final UniqueTagList tags;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        //@@author RonakLakhotia
        reminders = new UniqueReminderList();
        //@@author generated
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

    //@@author RonakLakhotia
    public void setReminders(List<? extends ReadOnlyReminder> reminders) throws DuplicateReminderException {
        this.reminders.setReminders(reminders);
    }
    //@@author generated
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
    //@@author RonakLakhotia
    /**
     * Adds a reminder to the address book.
     * @throws DuplicateReminderException if an equivalent person already exists.
     */
    public void addReminder(ReadOnlyReminder r) throws DuplicateReminderException {
        Reminder newReminder = new Reminder(r);
        reminders.add(newReminder);
    }
    //@@author generated

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

    //@@author RonakLakhotia
    /**
     * Replaces the given reminder {@code target} in the list with {@code changeReadOnlyReminder}.
     * {@code Weaver}'s tag list will be updated with the tags of {@code changeReadOnlyReminder}.
     *
     * @throws DuplicateReminderException if updating the reminder's details causes the reminder to be equivalent to
     *      another existing reminder in the list.
     * @throws ReminderNotFoundException if {@code target} could not be found in the list.
     *
     */
    public void updateReminder(ReadOnlyReminder target, ReadOnlyReminder changeReadOnlyReminder)
            throws DuplicateReminderException, ReminderNotFoundException {
        requireNonNull(changeReadOnlyReminder);

        Reminder changedReminder = new Reminder(changeReadOnlyReminder);
        reminders.setReminder(target, changedReminder);
    }

    //@@author generated
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

    //@@author RonakLakhotia
    /**
     * Removes {@code key} from this {@code AddressBook}.
     * @throws ReminderNotFoundException if the {@code key} is not in this {@code AddressBook}.
     */
    public boolean removeReminder(ReadOnlyReminder key) throws ReminderNotFoundException {
        if (reminders.remove(key)) {
            return true;
        } else {
            throw new ReminderNotFoundException();
        }
    }

    //@@author generated
    //// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        tags.add(t);
    }
    //@@author ChenXiaoman
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
    //@@author

    //// util methods

    @Override
    public String toString() {
        return persons.asObservableList().size() + " persons, "
                + tags.asObservableList().size() +  " tags" + reminders.asObservableList().size() + " reminders";
        // TODO: refine later
    }

    @Override
    public ObservableList<ReadOnlyPerson> getPersonList() {
        return persons.asObservableList();
    }

    //@@author RonakLakhotia
    @Override
    public ObservableList<ReadOnlyReminder> getReminderList() {
        return reminders.asObservableList();
    }
    //@@author generated

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
