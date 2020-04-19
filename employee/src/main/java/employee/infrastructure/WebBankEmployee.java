package employee.infrastructure;


import employee.domain.exception.DataSourceBadResponseException;
import employee.domain.employee.BankEmployee;
import employee.domain.model.AddClient;
import employee.domain.model.Client;
import employee.domain.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class WebBankEmployee implements BankEmployee {

    private ResponseBuilder responseBuilder;

    @Autowired
    public WebBankEmployee(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    @Override
    public AddClient addClient(AddClient addClient) {

        try {

            Mono<AddClient> mono = responseBuilder.addClient(addClient);

            return mono.block();

        } catch (Exception e) {
            throw new DataSourceBadResponseException(e.getMessage());
        }
    }

    @Override
    public List<Product> getProducts(Client client) {
        List<Product> products = null;
        try {
            Flux<Product> flux = responseBuilder.listProducts(client);
            products = flux.collectList().block();

        } catch (Exception e) {

            throw new DataSourceBadResponseException(e.getMessage());
        }
        return products;
    }

    @Override
    public Client upgradeClient(Client client) {
        try {
            Mono<Client> mono = responseBuilder.upgradeClient(client);
            return mono.block();
        } catch (Exception e) {
            throw new DataSourceBadResponseException(e.getMessage());
        }
    }

    @Override
    public Client downgradeClient(Client client) {
        try {
            Mono<Client> mono = responseBuilder.downgradeClient(client);
            return mono.block();
        } catch (Exception e) {
            throw new DataSourceBadResponseException(e.getMessage());
        }
    }


}



