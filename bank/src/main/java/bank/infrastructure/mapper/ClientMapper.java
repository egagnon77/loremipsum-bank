package bank.infrastructure.mapper;

import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.infrastructure.entity.ClientDto;
import bank.infrastructure.entity.ProductDto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClientMapper {

    private ProductMapper productMapper;

    public ClientMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public Optional<Client> toClient(Optional<ClientDto> clientDto) {

        if (clientDto.isPresent()) {
            return Optional.of(toClient(clientDto.get()));
        }

        return Optional.empty();
    }

    public Client toClient(ClientDto clientDto) {
        Client client = new Client(clientDto.getId());

        List<Product> products = new ArrayList<>();
        for(ProductDto product : clientDto.getProducts()){
            products.add(productMapper.toProduct(product));
        }        

        client.setProducts(products);
        return client;
    }

    public ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getName());
        return clientDto;
    }
}
