package bank.api.v1.service;

import bank.api.v1.dto.CreateClient;
import bank.domain.exception.AlreadyExistsException;
import bank.domain.exception.InvalidArgumentException;
import bank.domain.exception.NotFoundException;
import bank.domain.model.ApprobationStatus;
import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.domain.model.ProductType;
import bank.domain.repository.ClientProductRepository;
import bank.domain.repository.ClientRepository;
import bank.domain.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    public static final String ERROR_CLIENT_AND_PRODUCT_NOT_FOUND = "Client and product not found.";
    public static final String ERROR_CLIENT_NOT_FOUND = "Client not found.";
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

        throw new NotFoundException(ERROR_CLIENT_NOT_FOUND);
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
        return productRepository.findAll().stream().filter(p ->
            p.getProductLevel() <= client.getProductLevel()).collect(Collectors.toList());

    }

    public Client upgradeStatus(String id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            client.get().upgradeStatus();
            return clientRepository.save(client.get());
        }

        throw new NotFoundException(ERROR_CLIENT_NOT_FOUND);
    }


    public Client downgradeStatus(String id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            client.get().downgradeStatus();
            return clientRepository.save(client.get());
        }

        throw new NotFoundException(ERROR_CLIENT_NOT_FOUND);
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

            if (isAccept && ApprobationStatus.WAITING_FOR_SUBSCRIPTION.equals(approbationStatus)) {
                clientProductRepository.save(client.get(), product.get(), ApprobationStatus.SUBSCRIBED);
            }
            if (isAccept && ApprobationStatus.WAITING_FOR_DELETION.equals(approbationStatus)) {
                clientProductRepository.deleteById(client.get(), product.get());
            }

            if (!isAccept && ApprobationStatus.WAITING_FOR_DELETION.equals(approbationStatus)) {
                clientProductRepository.save(client.get(), product.get(), ApprobationStatus.SUBSCRIBED);
            }
            if (!isAccept && ApprobationStatus.WAITING_FOR_SUBSCRIPTION.equals(approbationStatus)) {
                clientProductRepository.deleteById(client.get(), product.get());
            }

        } else {
            throw new NotFoundException(ERROR_CLIENT_AND_PRODUCT_NOT_FOUND);
        }
    }

    public void subscribeProduct(String clientName, Integer productId) {

        Optional<Client> client = clientRepository.findById(clientName);
        Optional<Product> product = productRepository.findById(productId);

        if (client.isPresent() && product.isPresent()) {
            if (product.get().getProductLevel() > client.get().getProductLevel()) {
                throw new InvalidArgumentException("Cannot subscribe: product not available for the client.");
            }
            ApprobationStatus approbationStatus = clientProductRepository
                .findById(client.get(), product.get());

            if (approbationStatus != null) {
                throw new AlreadyExistsException("Product is already subscribed.");
            }

            if (product.get().getProductType().equals(ProductType.AUTOMATIC.getValue())) {
                approbationStatus = ApprobationStatus.SUBSCRIBED;
            } else {
                approbationStatus = ApprobationStatus.WAITING_FOR_SUBSCRIPTION;
            }
            clientProductRepository.save(client.get(), product.get(), approbationStatus);
        } else {
            throw new NotFoundException(ERROR_CLIENT_AND_PRODUCT_NOT_FOUND);
        }
    }

    public void unSubscribeProduct(String clientName, Integer productId) {

        Optional<Client> client = clientRepository.findById(clientName);
        Optional<Product> product = productRepository.findById(productId);

        if (client.isPresent() && product.isPresent()) {

            ApprobationStatus approbationStatus = clientProductRepository
                .findById(client.get(), product.get());

            if (approbationStatus == null) {
                throw new NotFoundException("Product is not subscribed. Cannot unsubscribe.");
            } else if (approbationStatus.equals(ApprobationStatus.WAITING_FOR_DELETION)) {
                throw new AlreadyExistsException("Product is already waiting to be unsubscribe.");
            }

            if (product.get().getProductType().equals(ProductType.AUTOMATIC.getValue()) ||
                approbationStatus.equals(ApprobationStatus.WAITING_FOR_SUBSCRIPTION)) {
                clientProductRepository.deleteById(client.get(), product.get());
            } else {
                approbationStatus = ApprobationStatus.WAITING_FOR_DELETION;
                clientProductRepository.save(client.get(), product.get(), approbationStatus);
            }

        } else {
            throw new NotFoundException(ERROR_CLIENT_AND_PRODUCT_NOT_FOUND);
        }
    }


    public List<Client> findClientsWaitingProductApprobation() {

        List<Client> waitingForProductApprobations = new ArrayList<>();

        for (Client client : clientRepository.findAll()) {
            for (Product product : client.getProducts()) {

                ApprobationStatus approbationStatus = clientProductRepository.findById(client, product);

                if ((ApprobationStatus.WAITING_FOR_SUBSCRIPTION.equals(approbationStatus)
                    || ApprobationStatus.WAITING_FOR_DELETION.equals(approbationStatus))
                    && !waitingForProductApprobations.contains(client)) {
                    waitingForProductApprobations.add(client);
                }
            }
        }

        return waitingForProductApprobations;
    }
}