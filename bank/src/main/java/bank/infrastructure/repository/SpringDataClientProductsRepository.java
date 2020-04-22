package bank.infrastructure.repository;

import bank.domain.model.ApprobationStatus;
import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.domain.repository.ClientProductRepository;
import bank.infrastructure.entity.ClientDto;
import bank.infrastructure.entity.ClientProductsDto;
import bank.infrastructure.entity.ClientProductsPrimaryKeys;
import bank.infrastructure.entity.ProductDto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringDataClientProductsRepository implements ClientProductRepository {

    private final CrudClientProductsRepository crudClientProductsRepository;
    private final CrudClientRepository crudClientRepository;
    private final CrudProductRepository crudProductRepository;


    @Autowired
    public SpringDataClientProductsRepository(CrudClientProductsRepository crudClientProductsRepository,
        CrudClientRepository crudClientRepository, CrudProductRepository crudProductRepository) {
        this.crudClientProductsRepository = crudClientProductsRepository;
        this.crudClientRepository = crudClientRepository;
        this.crudProductRepository = crudProductRepository;
    }

    @Override
    public ApprobationStatus findById(Client client, Product product) {

        ClientProductsPrimaryKeys clientProductsPrimaryKeys = new ClientProductsPrimaryKeys(client.getName(),
            product.getId());

        Optional<ClientProductsDto> clientProductsDto = crudClientProductsRepository
            .findById(clientProductsPrimaryKeys);

        if (clientProductsDto.isPresent()) {
            return ApprobationStatus.fromInteger(clientProductsDto.get().getApprobationStatus());
        }

        return null;
    }

    @Override
    public void save(Client client, Product product, ApprobationStatus approbationStatus) {

        ClientProductsPrimaryKeys clientProductsPrimaryKeys = new ClientProductsPrimaryKeys(client.getName(),
            product.getId());

        Optional<ClientProductsDto> clientProductsDto = crudClientProductsRepository
            .findById(clientProductsPrimaryKeys);

        if (clientProductsDto.isPresent()) {
            clientProductsDto.get().setApprobationStatus(approbationStatus.getValue());
            crudClientProductsRepository.save(clientProductsDto.get());
        } else {
            Optional<ClientDto> clientDto = crudClientRepository.findById(client.getName());
            Optional<ProductDto> productDto = crudProductRepository.findById(product.getId());
            if (clientDto.isPresent() && productDto.isPresent()) {
                ClientProductsDto newClientProductsDto = new ClientProductsDto(clientDto.get(), productDto.get());
                crudClientProductsRepository.save(newClientProductsDto);
            }

        }

    }
}
