//@@author ChenXiaoman
package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * An event requesting to show command usage.
 */
public class ShowCommandUsageEvent extends BaseEvent {
    private String commandWord;

    public ShowCommandUsageEvent(String commandWord) {
        this.commandWord = commandWord;
    }

    public String getCommandWord() {
        return commandWord;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
