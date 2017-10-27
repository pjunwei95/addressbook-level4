package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

public class ChangeThemeEvent extends BaseEvent {
    private String theme;

    public ChangeThemeEvent(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public String getTheme() {
        return theme;
    }
}
