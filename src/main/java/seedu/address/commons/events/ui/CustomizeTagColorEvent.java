package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

public class CustomizeTagColorEvent extends BaseEvent{
    String color;

    public CustomizeTagColorEvent(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
