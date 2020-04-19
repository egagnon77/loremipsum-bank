package client.cli;

public enum CliOptions {

    Status("status"),
    Available("avail");

    private String value;

    CliOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
