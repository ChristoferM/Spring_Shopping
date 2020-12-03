package co.edu.usbcali.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.PaymentMethod;


public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer>{
	
	
	@Modifying
	@Query(value = "UPDATE payment_method set enable='Y' WHERE payment_method.pay_Id= ?1 ;", nativeQuery = true)
	public void switchEnable(Integer payIding);
	
	@Modifying
	@Query(value = "UPDATE payment_method set enable='N' WHERE payment_method.pay_Id= ?1 ;", nativeQuery = true)
	public void switchDisable(Integer payIding);
	
	
}
