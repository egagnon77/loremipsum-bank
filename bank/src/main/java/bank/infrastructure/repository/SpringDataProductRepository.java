package bank.infrastructure.repository;

import bank.domain.model.Product;
import bank.domain.repository.ProductRepository;
import bank.infrastructure.entity.ProductDto;
import bank.infrastructure.mapper.ProductMapper;
import java.util.ArrayList;
import java.util.List;
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
    public List<Product> findAll() {

        List<Product> products = new ArrayList<>();

        for (ProductDto productDto : crudProductRepository.findAll()) {
            products.add(productMapper.toProduct(productDto));
        }

        return products;
    }

}
