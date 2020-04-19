package bank.infrastructure.mapper;

import bank.domain.model.Product;
import bank.infrastructure.entity.ClientDto;
import bank.infrastructure.entity.ClientProductsDto;
import bank.infrastructure.entity.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductDto p) {
        Product product = null;
        if (p != null) {
            product = new Product(p.getId(), p.getName(), p.getProductType(), p.getProductLevel(),
                p.getApprobationStatus());
        }
        return product;
    }

    public ProductDto toDto(Product product, ClientDto clientDto) {

        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setProductLevel(product.getProductLevel());
        productDto.setProductType(product.getProductType());

        ClientProductsDto clientProductsDto = new ClientProductsDto();
        clientProductsDto.setProduct(productDto);
        clientProductsDto.setClient(clientDto);
        clientProductsDto.setApprobationStatus(product.getApprobationStatus());
        productDto.getClientProductsDto().add(clientProductsDto);

        return productDto;
    }
}
