package petStore.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import petStore.controller.model.PetStoreCustomer;
import petStore.controller.model.PetStoreData;
import petStore.controller.model.PetStoreEmployee;
import petStore.dao.CustomerDao;
import petStore.dao.EmployeeDao;
import petStore.dao.PetStoreDao;
import petStore.entity.Customer;
import petStore.entity.Employee;
import petStore.entity.PetStore;

@Service
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private CustomerDao customerDao;

	
	/*
	 *  the save method in the service class. 
	 *  In the service class, the savePetStore method should take a PetStoreData object as a parameter
	 *   and return a PetStoreData object
	 *  
	 */
	
	//PETSTORE SERVICE METHODS ---------------------------------------------------------------------------
	
	@Transactional(readOnly = false)
	public PetStoreData savePetStore (PetStoreData petStoreData) 
	{
		Long petStoreID = petStoreData.getPetStoreID();
		PetStore petStore = findOrCreatePetStore(petStoreID);
		
		//Call copyPetStoreFields(). 
		copyPetStoreFields(petStore, petStoreData);
		return new PetStoreData(petStoreDao.save(petStore));

	}
	/*
	 * This method takes a PetStore object and a PetStoreData object as parameters. 
	 * Matching fields are copied from the PetStoreData object to the PetStore object. 
	 * Do not copy the customers or employees fields.
	 */
	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) 
	{
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
	}
	/*
	 * This method returns a new PetStore object if the pet store ID is null. 
	 * If not null, the method should call findPetStoreById, which returns a PetStore object
	 *  if a pet store with matching ID exists in the database. 
	 *  If no matching pet store is found, the method should throw a NoSuchElementException with an appropriate message.
	 */
	private PetStore findOrCreatePetStore(Long petStoreID)
	{
		PetStore petStore;
		if(Objects.isNull(petStoreID))
		{
			petStore = new PetStore();
		}
		else
		{
			petStore = findPetStoreById(petStoreID);
		}
		return petStore;
	}
	private PetStore findPetStoreById(Long petStoreID) 
	{
		return petStoreDao.findById(petStoreID).orElseThrow(
				() -> new NoSuchElementException("Petstore with ID " + petStoreID + " was not found!"));
	}
	
	@Transactional(readOnly = true)
	public List<PetStoreData> retrieveAllPetStores() 
	{
		/*
		 * A stream in Java is a sequence of objects that supports various methods 
		 * which can be pipelined to produce the desired result. 
		 * The map method is used to return a stream consisting of the results
		 * of applying the given function to the elements of this stream
		 */
		//@formatter:off
		return petStoreDao.findAll().stream().map(PetStoreData::new).toList();
		//@formatter:on
	}
	@Transactional(readOnly = true)
	public PetStoreData retrievePetStoreByID(Long petStoreID) 
	{
		PetStore petStore = findPetStoreById(petStoreID);
		return new PetStoreData(petStore);
		
	}

	//find the petstore by id then delete it
	@Transactional(readOnly = false)
	public void deletePetStoreByID(Long petStoreID) 
	{
		PetStore petstore = findPetStoreById(petStoreID);
		petStoreDao.delete(petstore);
		
	}
	
	//EMPLOYEEE SERVICE METHODS ------------------------------------------------------------------------
	
	/*
	 *  the save employee method in the service class. 
	 *  In the service class, the saveEmployee method should take a PetStoreEmployee object as a parameter
	 *   and return an Employee object
	 *  
	 */
	
	@Transactional(readOnly = false)
	public PetStoreEmployee saveEmployee (PetStoreEmployee employeeData,Long petStoreID)
	{
		Long employeeID = employeeData.getEmployeeID();
		Employee employee = findOrCreateEmployee(employeeID, petStoreID);
		copyEmployeeFields(employee, employeeData);
		return new PetStoreEmployee(employeeDao.save(employee));
	}
	
	private void copyEmployeeFields(Employee employee, PetStoreEmployee employeeData) 
	{
		employee.setEmployeeID(employeeData.getEmployeeID());
		employee.setEmployeeFirstName(employeeData.getEmployeeFirstName());
		employee.setEmployeeLastName(employeeData.getEmployeeLastName());
		employee.setEmployeeJobTitle(employeeData.getEmployeeJobTitle());
		employee.setEmployeePhoneNumber(employeeData.getEmployeePhoneNumber());
	}
	
	private Employee findEmployeeById(Long employeeID, Long petStoreID) 
	{
		return employeeDao.findById(employeeID).orElseThrow(
				()-> new NoSuchElementException("Employee with ID " + employeeID + " was not found!"));
	}
	
	private Employee findOrCreateEmployee(Long employeeID, Long petStoreID)
	{
		Employee employee;
		if(Objects.isNull(employeeID))
		{
			employee = new Employee();
		}
		else
		{
			employee = findEmployeeById(employeeID, petStoreID);
		}
		return employee;
	}
	
	//CUSTOMER SERVICE METHODS ------------------------------------------------------------------------------
	public PetStoreCustomer saveCustomer(PetStoreCustomer customerData) 
	{
		Long customerID = customerData.getCustomerID();
		Customer customer = findOrCreateCustomer(customerID);
		copyCustomerFields(customer, customerData);
		return new PetStoreCustomer(customerDao.save(customer));
	}
	
	private Customer findOrCreateCustomer(Long customerID) 
	{
		Customer customer;
		if (Objects.isNull(customerID))
		{
			customer = new Customer();
		}
		else
		{
			customer = findCustomerById(customerID);
		}
		return customer;
	}
	
	private Customer findCustomerById(Long customerID) 
	{
		return customerDao.findById(customerID).orElseThrow(
				()-> new NoSuchElementException("Customer with ID " + customerID + " was not found!"));
	}
	
	private void copyCustomerFields(Customer customer, PetStoreCustomer customerData) 
	{
		customer.setCustomerFirstName(customerData.getCustomerFirstName());
		customer.setCustomerLastName(customerData.getCustomerLastName());
		customer.setCustomerEmail(customerData.getCustomerEmail());
		
	}




}
