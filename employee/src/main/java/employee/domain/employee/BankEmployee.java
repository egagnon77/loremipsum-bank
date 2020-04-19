package employee.domain.employee;

import employee.domain.model.AddClient;
import employee.domain.model.Client;
import employee.domain.model.Product;
import java.util.List;

public interface BankEmployee {

    public AddClient addClient(AddClient client);

    public List<Product> getProducts(Client client);
}
