package seedu.address.ui;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import seedu.address.model.person.ReadOnlyPerson;

//import javax.swing.text.html.ImageView;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static String[] colors = {"red", "yellow", "blue", "orange", "brown", "green", "pink"};
    private static HashMap<String, String> tagColors = new HashMap<String, String>();
    private static Random random = new Random();

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final ReadOnlyPerson person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label date;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label remark;
    @FXML
    private ImageView image;

    public PersonCard(ReadOnlyPerson person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        initTags(person);
        bindListeners(person);


    }
    /**
     * Adds a photo to a persons contact
     */
    public void assignImage(ReadOnlyPerson person) {


        String url =  person.getImage().getFilePath() ;

        File file = new File(url);
        Image imageDisplay = new Image(file.toURI().toString(), 100, 100,
                false, false);

        image.setImage(imageDisplay);
    }

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
            initTags(person);


        });
        assignImage(person);
    }

    /**
     * Initialize tag color for each tag
     *
     * @param person
     */
    private void initTags(ReadOnlyPerson person) {
        person.getTags().forEach(tag -> {
            Label tagLabel = new Label(tag.tagName);
            tagLabel.setStyle("-fx-background-color: " + tag.tagColor.tagColorName);
            tags.getChildren().add(tagLabel);
        });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
