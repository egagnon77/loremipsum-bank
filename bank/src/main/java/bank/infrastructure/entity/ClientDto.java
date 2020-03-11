package bank.infrastructure.entity;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class ClientDto {

    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}