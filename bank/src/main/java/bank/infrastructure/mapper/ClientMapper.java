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
            clientId.setUuid(clientDto.get().id);

            client.setClientId(clientId);
            client.setFirstName(clientDto.get().firstName);
            client.setLastName(clientDto.get().lastName);
            client.setBirthDate(clientDto.get().birthDate);

            return Optional.of(client);
        }

        return Optional.empty();
    }

    public Client toClient(ClientDto clientDto) {

        Client client = new Client();

        ClientId clientId = new ClientId();
        clientId.setUuid(clientDto.id);

        client.setClientId(clientId);
        client.setFirstName(clientDto.firstName);
        client.setLastName(clientDto.lastName);
        client.setBirthDate(clientDto.birthDate);

        return client;
    }

    public ClientDto toDto(Client client) {

        ClientDto clientDto = new ClientDto();

        clientDto.id = client.getClientId().getId();
        clientDto.firstName = client.getFirstName();
        clientDto.lastName = client.getLastName();
        clientDto.birthDate = client.getBirthDate();

        return clientDto;
    }
}
