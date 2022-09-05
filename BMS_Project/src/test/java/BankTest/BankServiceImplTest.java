package BankTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.maven.bank.dao.BankDaoJDBC;
import com.maven.bank.exception.ApplicationException;
import com.maven.bank.pojo.CustomerPojo;
import com.maven.bank.service.BankService;


@ExtendWith(MockitoExtension.class)
public class BankServiceImplTest {

	@InjectMocks
	BankService bankService;

	@Mock
	BankDaoJDBC bankDao;

	@Test
	public void testGetATransactionWithMockito() {

		@SuppressWarnings("unused")
		CustomerPojo dummyBookPojo = new CustomerPojo("test101", "test", "test", "test");
		try {
			when(bankDao.getCustomer("test101","test")).thenReturn(new CustomerPojo("test101", "test", "test", "test"));

		} catch (ApplicationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		CustomerPojo expectedPojo = new CustomerPojo("test101", "test", "test", "test");
		CustomerPojo actualPojo = null;

		try {
			actualPojo = bankService.getCustomer("test101","test");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expectedPojo.getCustomerID(), actualPojo.getCustomerID()); 
	}



}
