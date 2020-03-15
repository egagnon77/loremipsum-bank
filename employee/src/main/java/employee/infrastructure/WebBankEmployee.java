package employee.infrastructure;


import employee.cli.DataSourceBadResponseException;
import employee.domain.employee.BankEmployee;
import employee.domain.model.AddClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class WebBankEmployee implements BankEmployee {

    private MonoBuilder monoBuilder;

    @Autowired
    public WebBankEmployee(MonoBuilder monoBuilder) {
        this.monoBuilder = monoBuilder;
    }

    @Override
    public AddClient addClient(AddClient addClient) {

        try {

            Mono<AddClient> mono = monoBuilder.addClient(addClient);

            return mono.block();

        } catch (Exception e) {
            throw new DataSourceBadResponseException(e.getMessage());
        }
    }


}



