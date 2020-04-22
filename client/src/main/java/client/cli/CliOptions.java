package client.cli;

public enum CliOptions {

    STATUS("status"),
    AVAILABLE("avail"),
    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe");

    private String value;

    CliOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
