package seedu.address.model.font;

import org.junit.Test;

import static org.junit.Assert.*;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FONT_SIZE;

public class FontSizeTest {
    @Test
    public void isValidFontSize() throws Exception {
        assertTrue(FontSize.isValidFontSize(FontSize.FONT_SIZE_L_LABEL));
        assertFalse(FontSize.isValidFontSize(INVALID_FONT_SIZE));
        assertFalse(FontSize.isValidFontSize(""));
    }

}
