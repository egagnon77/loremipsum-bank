package bank.infrastructure.mapper;

import bank.domain.model.Client;
import bank.domain.model.ClientId;
import bank.infrastructure.entity.ClientDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientMapper {

    public Optional<Client> toClient(Optional<ClientDto> clientDto) {

        if (clientDto.isPresent()) {

            Client client = new Client();

            ClientId clientId = new ClientId();
            clientId.setUuid(clientDto.get().getId());

            client.setClientId(clientId);
            client.setFirstName(clientDto.get().getFirstName());
            client.setLastName(clientDto.get().getLastName());
            client.setBirthDate(clientDto.get().getBirthDate());

            return Optional.of(client);
        }

        return Optional.empty();
    }

    public Client toClient(ClientDto clientDto) {

        Client client = new Client();

        ClientId clientId = new ClientId();
        clientId.setUuid(clientDto.getId());

        client.setClientId(clientId);
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setBirthDate(clientDto.getBirthDate());

        return client;
    }

    public ClientDto toDto(Client client) {

        ClientDto clientDto = new ClientDto();

        clientDto.setId(client.getClientId().getId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setBirthDate(client.getBirthDate());

        return clientDto;
    }
}
