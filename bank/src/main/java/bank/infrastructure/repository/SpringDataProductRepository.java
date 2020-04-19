package bank.infrastructure.repository;

import bank.domain.model.Product;
import bank.domain.repository.ProductRepository;
import bank.infrastructure.entity.ProductDto;
import bank.infrastructure.mapper.ProductMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringDataProductRepository implements ProductRepository {

    private final CrudProductRepository crudProductRepository;
    private final ProductMapper productMapper;

    @Autowired
    public SpringDataProductRepository(CrudProductRepository crudProductRepository,
        ProductMapper productMapper) {
        this.crudProductRepository = crudProductRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Optional<List<Product>> findAvailable(Integer productLevel) {
        Optional<List<ProductDto>> productsDto = crudProductRepository.findAvailable(productLevel);
        List<Product> products = new ArrayList<>();

        Stream<ProductDto> productDtoStream = productsDto.map(List::stream).orElse(Stream.empty());
        productDtoStream.forEach(el -> products.add(productMapper.toProduct(el)));

        return Optional.of(products);
    }
}
