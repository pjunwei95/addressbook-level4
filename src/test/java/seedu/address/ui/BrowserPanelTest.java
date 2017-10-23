package seedu.address.ui;

import static guitests.guihandles.WebViewUtil.waitUntilBrowserLoaded;
import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.EventsUtil.postNow;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.ui.BrowserPanel.DEFAULT_PAGE;
import static seedu.address.ui.BrowserPanel.FACEBOOK_PROFILE_PAGE;
import static seedu.address.ui.BrowserPanel.GOOGLE_SEARCH_URL_PREFIX;
import static seedu.address.ui.BrowserPanel.GOOGLE_SEARCH_URL_SUFFIX;
import static seedu.address.ui.UiPart.FXML_FILE_FOLDER;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.BrowserPanelHandle;
import seedu.address.MainApp;
import seedu.address.commons.events.ui.FaceBookEvent;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;

public class BrowserPanelTest extends GuiUnitTest {
    private PersonPanelSelectionChangedEvent selectionChangedEventStub;
    private FaceBookEvent selectionChanged;
    private BrowserPanel browserPanel;
    private BrowserPanelHandle browserPanelHandle;

    @Before
    public void setUp() {
        selectionChangedEventStub = new PersonPanelSelectionChangedEvent(new PersonCard(ALICE, 0));
        selectionChanged = new FaceBookEvent(ALICE, "ronak.lakhotia");
        guiRobot.interact(() -> browserPanel = new BrowserPanel());
        uiPartRule.setUiPart(browserPanel);

        browserPanelHandle = new BrowserPanelHandle(browserPanel.getRoot());
    }

    @Test
    public void display() throws Exception {
        // default web page
        URL expectedDefaultPageUrl = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        assertEquals(expectedDefaultPageUrl, browserPanelHandle.getLoadedUrl());

        // associated web page of a person
        postNow(selectionChangedEventStub);
        URL expectedPersonUrl = new URL(GOOGLE_SEARCH_URL_PREFIX
                + ALICE.getName().fullName.replaceAll(" ", "+") + GOOGLE_SEARCH_URL_SUFFIX);

        // System.out.println(browserPanelHandle.getLoadedUrl());
        waitUntilBrowserLoaded(browserPanelHandle);
        assertEquals(expectedPersonUrl, browserPanelHandle.getLoadedUrl());
    }

    //GUI tests for loading facebook page
    @Test
    public void faceBookDisplay() throws Exception {

        URL expectedFaceBookPersonUrl = new URL(FACEBOOK_PROFILE_PAGE + "ronak.lakhotia");
        //postNow(selectionChangedEventStub);
        postNow(selectionChanged);

        System.out.println(browserPanelHandle.getLoadedUrl() + " " + expectedFaceBookPersonUrl);

        assertEquals(expectedFaceBookPersonUrl, browserPanelHandle.getLoadedUrl());

    }

}

