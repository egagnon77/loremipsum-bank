package bank.domain.model;

import java.util.stream.Stream;

public enum ApprobationStatus {
    NOT_SET(-1),
    SUBSCRIBED(0),
    WAITING_FOR_SUBCRIPTION(1),
    WAITING_FOR_DELETION(2);

    private Integer value;

    ApprobationStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static boolean isValid(Integer approbationStatus) {
        return Stream.of(ApprobationStatus.values())
            .anyMatch(e -> e.getValue().equals(approbationStatus));
    }
}
