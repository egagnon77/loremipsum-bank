package bank.api.v1.service;

import bank.api.v1.dto.CreateClient;
import bank.domain.exception.AlreadyExistsException;
import bank.domain.exception.NotFoundException;
import bank.domain.model.ApprobationStatus;
import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.domain.repository.ClientProductRepository;
import bank.domain.repository.ClientRepository;
import bank.domain.repository.ProductRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private ProductRepository productRepository;
    private ClientProductRepository clientProductRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, ProductRepository productRepository,
                         ClientProductRepository clientProductRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.clientProductRepository = clientProductRepository;
    }

    public Client get(String id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            return client.get();
        }

        throw new NotFoundException("Client not found.");
    }

    public CreateClient save(CreateClient createClient) {

        Client client = new Client(createClient.getName());

        if (!clientRepository.findById(client.getName()).isPresent()) {
            clientRepository.save(client);
            return createClient;
        } else {
            throw new AlreadyExistsException("Client already exists.");
        }
    }

    public List<Product> getProducts(String id) {
        Client client = get(id);
        return Collections.unmodifiableList(client.getProducts());

    }

    public List<Product> getAvailableProducts(String id) {
        Client client = get(id);
        List<Product> products = productRepository.findAll().stream().filter(p ->
            p.getProductLevel() <= client.getProductLevel()).collect(Collectors.toList());

        return products;
    }

    public Client upgradeStatus(String id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            client.get().upgradeStatus();
            return clientRepository.save(client.get());
        }

        throw new NotFoundException("Client not found.");
    }


    public Client downgradeStatus(String id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            client.get().downgradeStatus();
            return clientRepository.save(client.get());
        }

        throw new NotFoundException("Client not found.");
    }

    public void acceptManualProduct(String clientName, Integer productId) {
        acceptOrRejectManualProduct(clientName, productId, true);
    }

    public void rejectManualProduct(String clientName, Integer productId) {
        acceptOrRejectManualProduct(clientName, productId, false);
    }

    private void acceptOrRejectManualProduct(String clientName, Integer productId, boolean isAccept) {

        Optional<Client> client = clientRepository.findById(clientName);
        Optional<Product> product = productRepository.findById(productId);

        if (client.isPresent() && product.isPresent()) {
            ApprobationStatus approbationStatus = clientProductRepository.findById(client.get(), product.get());

            if (isAccept && ApprobationStatus.WAITING_FOR_SUBCRIPTION.equals(approbationStatus)) {
                clientProductRepository.save(client.get(), product.get(), ApprobationStatus.SUBSCRIBED);
            }

            if (!isAccept && ApprobationStatus.WAITING_FOR_DELETION.equals(approbationStatus)) {
                clientProductRepository.save(client.get(), product.get(), ApprobationStatus.NOT_SET);
            }
        } else {
            throw new NotFoundException("Client and product not found.");
        }

    }
}