package employee.domain.service;

import employee.domain.employee.BankEmployee;
import employee.domain.model.AddClient;
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

}
