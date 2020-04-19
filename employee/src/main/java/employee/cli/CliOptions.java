package employee.cli;

public enum CliOptions {
    ADD("add"),
    LIST("list"),
    UPGRADE("upgrade"),
    DOWNGRADE("downgrade");

    private String value;

    CliOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
