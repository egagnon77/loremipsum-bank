package employee.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddClient {

    @JsonProperty
    private String name;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }

}
