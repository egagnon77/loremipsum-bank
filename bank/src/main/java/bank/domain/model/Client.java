package bank.domain.model;

import java.time.LocalDate;

public class Client {

    private ClientId id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public ClientId getClientId() {
        return id;
    }

    public void setClientId(ClientId id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
