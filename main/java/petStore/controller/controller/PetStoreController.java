package petStore.controller.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import petStore.controller.model.PetStoreCustomer;
import petStore.controller.model.PetStoreData;
import petStore.controller.model.PetStoreEmployee;
import petStore.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	
	@Autowired
	private PetStoreService petStoreService;
	
	
	//CREATE METHODS
	
	//create a pet store
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData)
	{
		log.info("Creating new pet store");
		return petStoreService.savePetStore(petStoreData);
	}
	
	//create an employee
	@PostMapping("/{petStoreID}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee createEmployee(@RequestBody PetStoreEmployee employeeData, @PathVariable Long petStoreID)
	{
		log.info("Creating new employee!");
		return petStoreService.saveEmployee(employeeData, petStoreID);
	}
	
	//create a customer
	@PostMapping("/{petStoreID}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer createCustomer (@RequestBody PetStoreCustomer customerData, @PathVariable Long petStoreID)
	{
		log.info("Creating new customer!");
		return petStoreService.saveCustomer(customerData, petStoreID);
	}
	
	
	//UPDATE METHODS
	@PutMapping("/{petStoreID}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreID, @RequestBody PetStoreData petStoreData)
	{
		petStoreData.setPetStoreID(petStoreID);
		log.info("Updating petStore #{}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
		
	}
	
	//RETRIEVE METHODS
	//retrieve all petStores
	@GetMapping("/pet_store")
	public List<PetStoreData> retrieveAllPetStores()
	{
		log.info("Retrieving all pet stores");
		return petStoreService.retrieveAllPetStores();
	}
	
	//pet_store is the resource ID
	//pet_store is application
	
	@GetMapping("/{petStoreID}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreID)
	{
		log.info("Retrieving pet store with ID = {petStoreID}");
		return petStoreService.retrievePetStoreByID(petStoreID);
		
	}
	
	//DELETE METHODS
	@DeleteMapping
	public void deteleAllPetStores()
	{
		log.info("Attempting to delete all pet stores!");
		throw new UnsupportedOperationException("Deleting all pet stores is not allowed!");
	}
	
	//delete single pet store
	@DeleteMapping("{petStoreID}")
	public Map<String, String> deletePetStoreByID (@PathVariable Long petStoreID) {
		log.info("Deleting pet store with ID = {petStoreID}");
		petStoreService.deletePetStoreByID(petStoreID);
		return Map.of("message", "Deletion of petstore with ID = " + petStoreID + " was sucessful");
	}

}
