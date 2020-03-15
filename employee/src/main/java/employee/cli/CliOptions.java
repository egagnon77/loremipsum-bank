package employee.cli;

public enum CliOptions {
    ADD("add");

    private String value;

    CliOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
