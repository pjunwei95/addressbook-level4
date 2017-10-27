package seedu.address.ui;

import static seedu.address.model.font.FontSize.getassociatefxfontsizestring;

import java.io.File;

import java.util.HashMap;
import java.util.Random;

import com.google.common.eventbus.Subscribe;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import seedu.address.commons.events.ui.ChangeFontSizeEvent;
import seedu.address.commons.events.ui.ChangeTagColorEvent;
import seedu.address.commons.events.ui.PersonPanelAddressPressedEvent;
import seedu.address.logic.commands.PhotoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.font.FontSize;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.reminder.ReadOnlyReminder;

//import javax.swing.text.html.ImageView;


/**
 * An UI component that displays information of a {@code Reminder}.
 */
public class ReminderCard extends UiPart<Region> {

    private static final String FXML = "ReminderListCard.fxml";
    private static Random random = new Random();

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */



    public final ReadOnlyReminder reminder;

    @FXML
    private HBox cardPane;
    @FXML
    private Label about;
    @FXML
    private Label id;
    @FXML
    private Label priority;
    @FXML
    private Label date;
    @FXML
    private ImageView image;



    public ReminderCard(ReadOnlyReminder reminder, int displayedIndex) {
        super(FXML);
        this.reminder = reminder;
        id.setText(displayedIndex + ". ");
        bindListeners(reminder);
        registerAsAnEventHandler(this);
        String currentFontSize = FontSize.getCurrentFontSizeLabel();
        setFontSize(currentFontSize);
    }

    /**
     * Get the label about
     */
    public Label getAboutLabel() {
        return about;
    }

    /**
     * Adds a photo to a persons contact
     */
  /*  public void assignImage(String filePath) throws ParseException {

        String url;
        String Message_Image_Removed = "The image may have been removed from"
                + " the previous location!";

        if (filePath.equals("")) {
            url = "/images/address_book_32.png";
            Image Display = new Image(url);
            image.setImage(Display);
        }
        else {

            if (filePath.endsWith("g")) {

                url = filePath + "";

                File file = new File(url);
                boolean FileExists = file.exists();

                if (!FileExists) {

                    url = "/images/address_book_32.png";
                    Image Display = new Image(url);
                    image.setImage(Display);


                    throw new ParseException(
                            String.format(Message_Image_Removed, PhotoCommand.MESSAGE_USAGE)
                    );
                }
                else {
                    Image display = new Image(file.toURI().toString());
                    image.setImage(display);
                }
            } else {

                url = "src/main/resources/images/" + person.getImage().getFilePath() + ".jpg";
                File stored = new File(url);
                Image display = new Image(stored.toURI().toString(), 100, 100,
                        false, false);

                image.setImage(display);

            }
        }
    }
*/
    /**
     * Binds the individual UI elements to observe their respective {@code Reminder} properties
     * so that they will be notified of any changes.
     */
    private void bindListeners(ReadOnlyReminder reminder) {
        about.textProperty().bind(Bindings.convert(reminder.detailsProperty()));
        priority.textProperty().bind(Bindings.convert(reminder.priorityProperty()));
        date.textProperty().bind(Bindings.convert(reminder.dueDateProperty()));


    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReminderCard)) {
            return false;
        }

        // state check
        ReminderCard card = (ReminderCard) other;
        return id.getText().equals(card.id.getText())
                && reminder.equals(card.reminder);
    }

    @Subscribe
    private void handleChangeFontSizeEvent(ChangeFontSizeEvent event) {
        setFontSize(event.getFontSize());
        //setFontSizeForAllImages(event.getFontSize());
    }

    private void setFontSize(String newFontSize) {
        assert (FontSize.isValidFontSize(newFontSize));

        String fxFormatFontSize = getassociatefxfontsizestring(newFontSize);
        setFontSizeForAllAttributesExceptTag(fxFormatFontSize);
    }


    private void setFontSizeForAllAttributesExceptTag(String fontSize) {
        about.setStyle(fontSize);
        id.setStyle(fontSize);
        priority.setStyle(fontSize);
        date.setStyle(fontSize);
    }


}
