package bank.domain.service;

import bank.domain.model.Client;
import bank.domain.repository.BankRepository;
import bank.infrastructure.mapping.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BankService {

  @Autowired
  BankRepository bankRepository;

  public Client getClient(String clientId) {
    return ClientMapper.toClient(bankRepository.findById(clientId));
  }

}
