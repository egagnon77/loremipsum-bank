package bank.domain.repository;

import bank.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<List<Product>> findAvailable(Integer productLevel);
}
