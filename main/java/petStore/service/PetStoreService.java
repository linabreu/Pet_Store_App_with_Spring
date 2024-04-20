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

import petStore.controller.model.PetStoreData;
import petStore.dao.PetStoreDao;
import petStore.entity.PetStore;

@Service
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;

	
	/*
	 *  the save method in the service class. 
	 *  In the service class, the savePetStore method should take a PetStoreData object as a parameter
	 *   and return a PetStoreData object
	 *  
	 */
	
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
				() -> new NoSuchElementException("Petstore with ID " + petStoreID + "was not found!"));
	}

	@Transactional(readOnly = true)
	public List<PetStoreData> retrieveAllPetStores() 
	{
		/*
		 * A stream in Java is a sequence of objects that supports various methods 
		 * which can be pipelined to produce the desired result. 
		 * The map method is used to return a stream consisting of the results
		 * of applying the given function to the elements of this stream.
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
	public void deletePetStoreByID(Long petStoreID) {
		PetStore petstore = findPetStoreById(petStoreID);
		petStoreDao.delete(petstore);
		
	}



}
