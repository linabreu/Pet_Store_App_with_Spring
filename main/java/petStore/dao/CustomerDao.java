package petStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import petStore.entity.Customer;


public interface CustomerDao extends JpaRepository<Customer, Long> {}
