package petStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import petStore.entity.PetStore;

/*
 * Create a new package named pet.store.dao
 * In this package, create a new interface named PetStoreDao. 
 * The interface should extend JpaRepository<PetStore, Long>.
 */
public interface PetStoreDao extends JpaRepository<PetStore, Long> 
{
	
}
