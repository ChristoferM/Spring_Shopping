package co.edu.usbcali.demo.repository;


import java.util.List;
import java.util.Optional;

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
	
	@Query(value = "SELECT *  FROM  payment_method WHERE payment_method.enable='Y';", nativeQuery = true)
	public  List<PaymentMethod> finByAllEnable();
	
	@Query(value = "SELECT *  FROM  payment_method WHERE enable='Y' AND payment_method.pay_Id = ?1;", nativeQuery = true)
	public  Optional<PaymentMethod> finByAllByIdEnable(Integer payId);
	
	@Query(value = "SELECT pay_id, name, enable  FROM  payment_method;", nativeQuery = true)
	public  List<PaymentMethod> finByAll_1();
	@Modifying()
	@Query(value = "UPDATE shopping_cart set pay_id= ?1 , enable='N' WHERE shopping_cart.car_id= ?2  ;", nativeQuery = true)
	public void  PayCart(Integer payId, Integer carId);
	
}
