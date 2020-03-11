package bank.infrastructure.mapper;

import bank.domain.model.Client;
import bank.infrastructure.entity.ClientDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientMapper {

    public Optional<Client> toClient(Optional<ClientDto> clientDto) {

        if (clientDto.isPresent()) {
            return Optional.of(new Client(clientDto.get().getName()));
        }

        return Optional.empty();
    }

    public Client toClient(ClientDto clientDto) {
        return new Client(clientDto.getName());
    }

    public ClientDto toDto(Client client) {

        ClientDto clientDto = new ClientDto();
        clientDto.setName(client.getName());
        return clientDto;
    }
}
