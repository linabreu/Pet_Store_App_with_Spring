package petStore.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import petStore.entity.Customer;
import petStore.entity.Employee;
import petStore.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {
	
	private Long petStoreID;
	
	private String petStoreAddress;
	private String petStoreCity;
	private Integer petStoreZip;
	private String petStorePhone;
	
	private Set<PetStoreCustomer> customers = new HashSet<>();
	private Set<PetStoreEmployee> employees = new HashSet<>();
	
	/*In the PetStoreData class add a constructor that takes a PetStore as a parameter. 
	 *Set all matching fields in the PetStoreData class to the data in the PetStore class. 
	 *Also set the employees and customers fields to the respective PetStoreCustomer and PetStoreEmployee objects. 
	 *These are Sets so use loops for this.
	 */
	public PetStoreData(PetStore petStore)
	{
		//set all matching fields in PetStoreData to be what they are in PetStore class
		petStoreID = petStore.getPetStoreID();
		petStoreCity = petStore.getPetStoreCity();
		petStoreZip = petStore.getPetStoreZip();
		petStorePhone = petStore.getPetStorePhone();
		petStoreAddress = petStore.getPetStoreAddress();
		
		//use loop to set PetStoreCustomer & PetStoreEmployee data
		for(Customer customer : petStore.getCustomers())
		{
			customers.add(new PetStoreCustomer(customer));
		}
		
		for(Employee employee : petStore.getEmployees())
		{
			employees.add(new PetStoreEmployee(employee));
		}
	}


}
