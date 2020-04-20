package bank.infrastructure.mapper;

import bank.domain.model.Product;
import bank.infrastructure.entity.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductDto p) {
        Product product = null;
        if (p != null) {
            product = new Product(p.getId(), p.getName(), p.getProductType(), p.getProductLevel());
        }
        return product;
    }

    public ProductDto toDto(Product product) {

        ProductDto productDto = new ProductDto();

        productDto.setProductType(product.getProductType());
        productDto.setProductLevel(product.getProductLevel());
        productDto.setName(product.getName());
        productDto.setId(product.getId());

        return productDto;
    }
}
