package employee.cli;

public enum CliOptions {
    ADD("add"),
    LIST("list"),
    UPGRADE("upgrade"),
    DOWNGRADE("downgrade"),
    ACCEPT("accept"),
    REJECT("reject"),
    CLIENT("client"),
    TASKS("tasks");

    private String value;

    CliOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
