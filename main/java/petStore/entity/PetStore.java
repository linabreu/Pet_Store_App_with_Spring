package petStore.entity;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
public class PetStore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Getter @Setter Long petStoreID;
	
	private @Getter @Setter String petStoreAddress;
	private @Getter @Setter String petStoreCity;
	private @Getter @Setter Integer petStoreZip;
	private @Getter @Setter String petStorePhone;
	
	//how to set up many to many
	//declare join table
	//declare join columns
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "customer", joinColumns = 
		@JoinColumn(name = "pet_store_id"), inverseJoinColumns =
		@JoinColumn(name = "customer_id"))
	private Set<Customer> customers = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Employee> employees = new HashSet<>();

}
