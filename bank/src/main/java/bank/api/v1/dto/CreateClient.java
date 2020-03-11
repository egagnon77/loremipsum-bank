package bank.api.v1.dto;

public class CreateClient {

    private String name;

    public CreateClient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
