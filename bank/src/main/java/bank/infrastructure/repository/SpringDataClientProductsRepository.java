package bank.infrastructure.repository;

import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.domain.repository.ClientProductRepository;
import bank.infrastructure.entity.ClientProductsDto;
import bank.infrastructure.entity.ClientProductsPrimaryKeys;
import bank.infrastructure.mapper.ClientMapper;
import bank.infrastructure.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringDataClientProductsRepository implements ClientProductRepository {

    private final CrudClientProductsRepository crudClientProductsRepository;
    private final ClientMapper clientMapper;
    private final ProductMapper productMapper;

    @Autowired
    public SpringDataClientProductsRepository(CrudClientProductsRepository crudClientProductsRepository,
                                              ClientMapper clientMapper,
                                              ProductMapper productMapper) {
        this.crudClientProductsRepository = crudClientProductsRepository;
        this.clientMapper = clientMapper;
        this.productMapper = productMapper;
    }

//    @Override
//    public void save(Client client, Product product) {

        //TODO : INSERT CODE

//        ClientProductsPrimaryKeys clientProductsPrimaryKeys = new ClientProductsPrimaryKeys();
//        clientProductsPrimaryKeys.setClientDto(clientMapper.toDto(client));
//        clientProductsPrimaryKeys.setProductDto(productMapper.toDto(product));
//
//        Optional<ClientProductsDto> clientProductsDto = crudClientProductsRepository.findById(clientProductsPrimaryKeys);
//
//        if (clientProductsDto.isPresent()) {
////            clientProductsDto.get().setApprobationStatus(product.getApprobationStatus());
//            crudClientProductsRepository.save(clientProductsDto.get());
//        }

//    }
}