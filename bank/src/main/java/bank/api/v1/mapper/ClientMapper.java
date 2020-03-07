package bank.api.v1.mapper;

import bank.api.v1.dto.ClientDto;
import bank.domain.factory.IdFactory;
import bank.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    private IdFactory idFactory;

    @Autowired
    public ClientMapper(IdFactory idFactory) {
        this.idFactory = idFactory;
    }

    public Client toClient(ClientDto clientDto) {

        Client client = new Client();

        client.setClientId(idFactory.generateClientId());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setBirthDate(clientDto.getBirthDate());

        return client;
    }

}
