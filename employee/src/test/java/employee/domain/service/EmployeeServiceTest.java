package employee.domain.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import employee.domain.employee.BankEmployee;
import employee.domain.model.AddClient;
import employee.domain.model.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private BankEmployee bankEmployee;

    private EmployeeService testedClass;

    @Before
    public void setUp() {
        testedClass = new EmployeeService(bankEmployee);
    }

    @Test
    public void givenAnyClient_whenAddingIt_thenWeShouldCallBankEmployee() {
        testedClass.addClient(any(AddClient.class));

        verify(bankEmployee, times(1)).addClient(any(AddClient.class));
    }

    @Test
    public void givenAnyClient_whenGettingProducts_thenWeShouldCallBankEmployee() {
        testedClass.getProducts(any(Client.class));

        verify(bankEmployee, times(1)).getProducts(any(Client.class));
    }

    @Test
    public void givenAClient_whenUpgradeClient_thenWeShouldCallBankEmployee() {

        Client client = new Client();

        testedClass.upgradeClient(client);

        verify(bankEmployee, times(1)).upgradeClient(client);
    }

    @Test
    public void givenAClient_whenDowngradeClient_thenWeShouldCallBankEmployee() {

        Client client = new Client();

        testedClass.downgradeClient(client);

        verify(bankEmployee, times(1)).downgradeClient(client);
    }

    @Test
    public void givenAProductIdAndAClientName_whenAcceptProduct_thenWeShouldCallBankEmployee() {

        Integer aProductId = 88;
        String aClientName = "aClientName";

        testedClass.acceptProduct(aProductId, aClientName);

        verify(bankEmployee, times(1)).acceptProduct(aProductId, aClientName);
    }

    @Test
    public void givenAProductIdAndAClientName_whenRejectProduct_thenWeShouldCallBankEmployee() {

        Integer aProductId = 88;
        String aClientName = "aClientName";

        testedClass.rejectProduct(aProductId, aClientName);

        verify(bankEmployee, times(1)).rejectProduct(aProductId, aClientName);
    }

    @Test
    public void whenTask_thenWeShouldCallBankEmployee() {

        testedClass.task();

        verify(bankEmployee, times(1)).task();
    }
}
