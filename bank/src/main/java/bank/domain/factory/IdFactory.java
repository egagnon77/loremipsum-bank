package bank.domain.factory;

import bank.domain.model.ClientId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdFactory {

    public ClientId generateClientId() {
        ClientId clientId = new ClientId();
        clientId.setUuid(UUID.randomUUID().toString());
        return clientId;
    }
}