package petStore.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import petStore.entity.Customer;

@Data
@NoArgsConstructor
public class PetStoreCustomer {
	
	public PetStoreCustomer(Customer customer) 
	{
		customerID = customer.getCustomerID();
		customerFirstName = customer.getCustomerFirstName();
		custoemrLastName = customer.getCustoemrLastName();
		customerEmail = customer.getCustomerEmail();
	}
	
	private Long customerID;
	private String customerFirstName;
	private String custoemrLastName;
	private String customerEmail;
	

}
