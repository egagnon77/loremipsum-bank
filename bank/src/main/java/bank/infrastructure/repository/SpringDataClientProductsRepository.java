package bank.infrastructure.repository;

import bank.domain.model.ApprobationStatus;
import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.domain.repository.ClientProductRepository;
import bank.infrastructure.entity.ClientProductsDto;
import bank.infrastructure.entity.ClientProductsPrimaryKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringDataClientProductsRepository implements ClientProductRepository {

    private final CrudClientProductsRepository crudClientProductsRepository;

    @Autowired
    public SpringDataClientProductsRepository(CrudClientProductsRepository crudClientProductsRepository) {
        this.crudClientProductsRepository = crudClientProductsRepository;
    }

    @Override
    public ApprobationStatus findById(Client client, Product product) {

        ClientProductsPrimaryKeys clientProductsPrimaryKeys = new ClientProductsPrimaryKeys(client.getName(),product.getId());

        Optional<ClientProductsDto> clientProductsDto = crudClientProductsRepository.findById(clientProductsPrimaryKeys);

        if (clientProductsDto.isPresent()) {
            return ApprobationStatus.fromInteger(clientProductsDto.get().getApprobationStatus());
        }

        return null;
    }

    @Override
    public void save(Client client, Product product, ApprobationStatus approbationStatus) {

        ClientProductsPrimaryKeys clientProductsPrimaryKeys = new ClientProductsPrimaryKeys(client.getName(),product.getId());

        Optional<ClientProductsDto> clientProductsDto = crudClientProductsRepository.findById(clientProductsPrimaryKeys);

        if (clientProductsDto.isPresent()) {
            clientProductsDto.get().setApprobationStatus(approbationStatus.getValue());
            crudClientProductsRepository.save(clientProductsDto.get());
        }
    }
}