package bank.infrastructure.repository;

import bank.infrastructure.entity.ClientProductsDto;
import bank.infrastructure.entity.ClientProductsPrimaryKeys;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudClientProductsRepository extends CrudRepository<ClientProductsDto, ClientProductsPrimaryKeys> {
}