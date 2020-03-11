package bank.infrastructure.repository;

import bank.infrastructure.entity.ClientDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudClientRepository extends CrudRepository<ClientDto, String> {
}
