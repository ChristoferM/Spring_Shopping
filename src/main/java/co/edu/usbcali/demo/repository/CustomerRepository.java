package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import co.edu.usbcali.demo.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	
	public List<Customer> findByEnableAndEmail(String enable,String email);
	
	@Query("SELECT cus FROM Customer cus WHERE cus.name LIKE 'Mar%'")
	public List<Customer> findCustomerLikemar();
	
	
	@Modifying
	@Query(value ="UPDATE CUSTOMER set enable='Y' WHERE CUSTOMER.email= ?1 ;", nativeQuery = true)
	public void switchEnable(String email);
	
	
	
	@Query(value ="UPDATE customer set enable='N' WHERE email= ?1 ;", nativeQuery = true)
	@Modifying
	public void switchDisable(String email);
	

}
