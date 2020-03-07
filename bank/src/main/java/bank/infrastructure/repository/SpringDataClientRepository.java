package bank.infrastructure.repository;

import bank.domain.model.Client;
import bank.domain.repository.ClientRepository;
import bank.infrastructure.entity.ClientDto;
import bank.infrastructure.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringDataClientRepository implements ClientRepository {

    private final CrudClientRepository crudClientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public SpringDataClientRepository(CrudClientRepository crudClientRepository, ClientMapper clientMapper) {
        this.crudClientRepository = crudClientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Optional<Client> findById(String id) {
        Optional<ClientDto> clientDto = crudClientRepository.findById(id);
        return clientMapper.toClient(clientDto);
    }

    @Override
    public Client save(Client client) {
        ClientDto clientDto = crudClientRepository.save(clientMapper.toDto(client));
        return clientMapper.toClient(clientDto);
    }
}