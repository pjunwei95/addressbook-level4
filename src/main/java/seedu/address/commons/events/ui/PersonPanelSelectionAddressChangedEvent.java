package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

public class PersonPanelSelectionAddressChangedEvent extends BaseEvent{
    private String address;

    public PersonPanelSelectionAddressChangedEvent(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
