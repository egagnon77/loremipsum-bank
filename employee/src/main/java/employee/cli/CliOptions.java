package employee.cli;

public enum CliOptions {
    ADD("add"),
    LIST("list"),
    UPGRADE("upgrade");

    private String value;

    CliOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
