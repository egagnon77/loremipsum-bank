package client.cli;

public enum CliOptionsValue {

    Name("n");

    private String value;

    CliOptionsValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
