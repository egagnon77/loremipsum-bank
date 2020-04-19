package bank.infrastructure.repository;

import bank.infrastructure.entity.ProductDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudProductRepository extends CrudRepository<ProductDto, Integer> {


}
