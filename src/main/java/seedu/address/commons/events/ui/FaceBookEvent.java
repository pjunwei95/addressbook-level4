package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.person.ReadOnlyPerson;

public class FaceBookEvent extends BaseEvent {

    private final ReadOnlyPerson person;
    private final String username;

    public FaceBookEvent(ReadOnlyPerson person, String username) {
        this.person = person;
        this.username = username;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public ReadOnlyPerson getPerson() {
        return person;
    }

    public String getUsername() {
        return username;
    }
}
