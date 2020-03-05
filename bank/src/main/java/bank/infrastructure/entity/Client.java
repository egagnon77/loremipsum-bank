package bank.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String clientId;

  private Client() {};

  public Client(String clientId) {
    this.clientId = clientId;
  }

  public String getClientId() {
    return clientId;
  }
}

