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
		customerLastName = customer.getCustomerLastName();
		customerEmail = customer.getCustomerEmail();
	}
	
	private Long customerID;
	private String customerFirstName;
	private String customerLastName;
	private String customerEmail;
	

}
