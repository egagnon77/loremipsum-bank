package employee.domain.service;

import employee.domain.employee.BankEmployee;
import employee.domain.model.AddClient;
import employee.domain.model.Client;
import employee.domain.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {

    private BankEmployee bankEmployee;

    @Autowired
    public EmployeeService(BankEmployee bankEmployee) {
        this.bankEmployee = bankEmployee;
    }

    public AddClient addClient(AddClient addClient) {
        return bankEmployee.addClient(addClient);
    }

    public List<Product> getProducts(Client client) { return bankEmployee.getProducts(client); }

    public Client upgradeClient(Client client) {
        return bankEmployee.upgradeClient(client);
    }

    public Client downgradeClient(Client client) {
        return bankEmployee.downgradeClient(client);
    }
}
