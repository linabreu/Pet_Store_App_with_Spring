package petStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import petStore.entity.Employee;


/*1.Create a new DAO interface named EmployeeDao.
  2. The interface should extend JpaRepository. 
  It is very similar to PetStoreDao but it refers to an Employee object in the Generic
*/

public interface EmployeeDao extends JpaRepository<Employee, Long>{}
