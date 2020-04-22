package bank.domain.model;

public enum ApprobationStatus {
    NOT_SET(-1),
    SUBSCRIBED(0),
    WAITING_FOR_SUBSCRIPTION(1),
    WAITING_FOR_DELETION(2);

    private Integer value;

    ApprobationStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static ApprobationStatus fromInteger(int x) {
        switch(x) {
            case 0:
                return SUBSCRIBED;
            case 1:
                return WAITING_FOR_SUBSCRIPTION;
            case 2:
                return WAITING_FOR_DELETION;
            default:
                return NOT_SET;
        }
    }
}
