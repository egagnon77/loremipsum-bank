package bank.infrastructure.repository;

import bank.infrastructure.entity.ProductDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudProductRepository extends CrudRepository<ProductDto, Integer> {

    @Query("FROM product WHERE product_level <= ?1")
    Optional<List<ProductDto>> findAvailable(Integer productLevel);

}
