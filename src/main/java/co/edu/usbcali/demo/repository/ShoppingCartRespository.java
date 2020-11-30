package co.edu.usbcali.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import co.edu.usbcali.demo.domain.ShoppingCart;

public interface ShoppingCartRespository extends JpaRepository<ShoppingCart,Integer>{

	//@Query("SELECT shpr FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	//select  * from shopping_cart WHERE car_Id=9 AND enable='Y';
	@Query(value = "select  * from shopping_cart WHERE email=?1 AND enable='Y'", nativeQuery = true)
	public Optional<ShoppingCart> findByIdEnable(String email);
	/*@Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
		User findUserByStatusAndName(Integer status, String name);

	 * */
	@Query(value = "select * from shopping_cart WHERE shopping_cart.email= ?1", nativeQuery = true)
	public List<ShoppingCart> findShoppingCart(String email);
	
	
	
	
	
}
