package bank.domain.service;

import bank.domain.model.Client;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    public Client getClient(String clientId) {
        return new Client(clientId);
    }

}
