package employee.domain.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import employee.domain.employee.BankEmployee;
import employee.domain.model.AddClient;
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
}
