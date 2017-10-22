package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

public class PersonPanelAddressPressedEvent extends BaseEvent{

    private String address;

    public PersonPanelAddressPressedEvent(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    /**
     * Get the address from the event
     */
    public String getAddress() {
        return address;
    }
}
