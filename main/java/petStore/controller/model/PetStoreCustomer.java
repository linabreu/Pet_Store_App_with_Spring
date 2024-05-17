package petStore.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import petStore.entity.Customer;

@Data
@NoArgsConstructor
public class PetStoreCustomer {
	
	public PetStoreCustomer(Customer customer) 
	{
		customerId = customer.getCustomerId();
		customerFirstName = customer.getCustomerFirstName();
		customerLastName = customer.getCustomerLastName();
		customerEmail = customer.getCustomerEmail();
	}
	
	private Long customerId;
	private String customerFirstName;
	private String customerLastName;
	private String customerEmail;
	

}
