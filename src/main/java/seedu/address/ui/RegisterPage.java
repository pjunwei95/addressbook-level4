package seedu.address.ui;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FxViewUtil;
import seedu.address.logic.Logic;
import seedu.address.model.UserPrefs;
import seedu.address.model.font.FontSize;
import seedu.address.storage.AccountsStorage;
import seedu.address.storage.StorageManager;

/**
 * The register page.
 */
public class RegisterPage extends UiPart<Region> {

    private static final String FXML = "RegisterPage.fxml";
    private static final String ICON = "/images/address_book_32.png";

    private static final int MIN_HEIGHT = 600;
    private static final int MIN_WIDTH = 450;

    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private Stage primaryStage;
    private AccountsStorage accPrefs;
    private UserPrefs prefs;
    private StorageManager storage;
    private Config config;
    private Logic logic;
    private LoginPage loginPage;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField password1;

    public RegisterPage(Stage primaryStage, Config config, StorageManager storage, UserPrefs prefs,
                        Logic logic, AccountsStorage accPrefs) {
        super(FXML);
        // Set dependencies
        this.primaryStage = primaryStage;
        this.accPrefs = accPrefs;
        this.config = config;
        this.storage = storage;
        this.prefs = prefs;
        this.logic = logic;

        // Configure the UI
        setTitle(config.getAppTitle());
        setIcon(ICON);
        setWindowMinSize();
        setWindowDefaultSize(prefs);
        Scene scene = new Scene(getRoot());
        primaryStage.setScene(scene);
        registerAsAnEventHandler(this);
        //loginPage = new LoginPage(primaryStage, config, storage, prefs, logic, accPrefs);
    }

    /**
     * @return validity of account
     */
    private boolean checkValid() {
        if (accPrefs.getHm().get(username.getText()) != null) {
            logger.info("Register faild");
            return false;
        } else {
            logger.info("Register successful");
            return password.getText().equals(password1.getText());
        }
    }

    /**
     * Method for handle register event
     */
    @FXML
    private void handleRegisterEvent() {
        try {
            logger.info("Trying to register");
            if (checkValid()) {
                accPrefs.getHm().put(username.getText(), password.getText());
                accPrefs.saveAccountsPrefs(accPrefs, accPrefs.getUserPrefsFilePath());
                loginPage = new LoginPage(primaryStage, config, storage, prefs, logic, accPrefs);
                this.hide();
                loginPage.show();
            }
        } catch (IOException e) {
            logger.info("Invalid input");
        }
    }

    /**
     * Method for going back login page.
     */
    @FXML
    private void handleBackEvent() {
        logger.info("Going back to login page");
        loginPage = new LoginPage(primaryStage, config, storage, prefs, logic, accPrefs);
        this.hide();
        loginPage.show();
    }

    /**
     * Handles the key press event, {@code keyEvent}.
     */
    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            handleRegisterEvent();
        }
    }

    private void setTitle(String appTitle) {
        primaryStage.setTitle(appTitle);
    }

    private void setWindowMinSize() {
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
    }

    private void setWindowDefaultSize(UserPrefs prefs) {
        primaryStage.setHeight(prefs.getGuiSettings().getWindowHeight());
        primaryStage.setWidth(prefs.getGuiSettings().getWindowWidth());
        FontSize.setCurrentFontSizeLabel(prefs.getGuiSettings().getFontSize());
        if (prefs.getGuiSettings().getWindowCoordinates() != null) {
            primaryStage.setX(prefs.getGuiSettings().getWindowCoordinates().getX());
            primaryStage.setY(prefs.getGuiSettings().getWindowCoordinates().getY());
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Sets the given image as the icon of the main window.
     *
     * @param iconSource e.g. {@code "/images/help_icon.png"}
     */
    private void setIcon(String iconSource) {
        FxViewUtil.setStageIcon(primaryStage, iconSource);
    }

    void hide() {
        primaryStage.hide();
    }

    void releaseResources() {
        this.hide();
    }
}