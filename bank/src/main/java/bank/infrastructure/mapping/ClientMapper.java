package bank.infrastructure.mapping;

import bank.domain.model.Client;

import java.util.Optional;

public class ClientMapper {

  private ClientMapper() {
    throw new IllegalStateException("Mapping class");
  }

  public static Client toClient (Optional<bank.infrastructure.entity.Client> client) {

    if (client.isPresent())
    {
      return  new Client(client.get().getClientId());
    } else
    {
      return null;
    }
  }

}
