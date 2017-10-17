package seedu.address.ui;

import static seedu.address.model.font.FontSize.FONT_SIZE_L_LABEL;
import static seedu.address.model.font.FontSize.FONT_SIZE_M_LABEL;
import static seedu.address.model.font.FontSize.FONT_SIZE_S_LABEL;
import static seedu.address.model.font.FontSize.FONT_SIZE_XL_LABEL;
import static seedu.address.model.font.FontSize.FONT_SIZE_XS_LABEL;

import java.util.HashMap;
import java.util.Random;

import com.google.common.eventbus.Subscribe;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.events.ui.ChangeFontSizeEvent;
import seedu.address.model.font.FontSize;
import seedu.address.model.person.ReadOnlyPerson;


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

    public PersonCard(ReadOnlyPerson person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        initTags(person);
        bindListeners(person);
        registerAsAnEventHandler(this);
        String currentFontSize = FontSize.getCurrentFontSizeLabel();
        setFontSize(currentFontSize);
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

    @Subscribe
    private void handleChangeFontSizeEvent(ChangeFontSizeEvent event) {
        setFontSize(event.fontSize);
    }

    private void setFontSize(String userPref) {
        switch (userPref) {
            case FONT_SIZE_XS_LABEL:
                setFontSizeHelper("x-small");
                break;

            case FONT_SIZE_S_LABEL:
                setFontSizeHelper("small");
                break;

            case FONT_SIZE_M_LABEL:
                setFontSizeHelper("normal");
                break;

            case FONT_SIZE_L_LABEL:
                setFontSizeHelper("x-large");
                break;

            case FONT_SIZE_XL_LABEL:
                setFontSizeHelper("xx-large");
                break;

            default:
                break;
        }
    }

    private void setFontSizeHelper(String fontSize) {
        name.setStyle("-fx-font-size: " + fontSize + ";");
        id.setStyle("-fx-font-size: " + fontSize + ";");
        phone.setStyle("-fx-font-size: " + fontSize + ";");
        address.setStyle("-fx-font-size: " + fontSize + ";");
        email.setStyle("-fx-font-size: " + fontSize + ";");
        date.setStyle("-fx-font-size: " + fontSize + ";");
        remark.setStyle("-fx-font-size: " + fontSize + ";");
        tags.getChildren().forEach(node -> node.setStyle("-fx-font-size: " + fontSize + ";"));
    }

}
