package bank.infrastructure.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "client")
public class ClientDto {

    @Id
    public String id;
    public String firstName;
    public String lastName;
    public LocalDate birthDate;

}