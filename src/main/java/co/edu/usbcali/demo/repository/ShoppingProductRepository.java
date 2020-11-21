package co.edu.usbcali.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ShoppingProductRepository extends JpaRepository<ShoppingProduct, Integer> {
	
	@Query("SELECT SUM(shpr.total) FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public Long totalShoppingProductByShoppingCart(Integer carId);
	
	// @Query("SELECT shpr FROM ShoppingProduct shpr WHERE shpr.product.pro_id=:pro_id")
	// @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
	@Query(value = "SELECT  * FROM shopping_product WHERE shopping_product.pro_id= ?1", nativeQuery = true)
	public ShoppingProduct FindProductByShoppingProduct(String proId);
	
	
	//select  shpr_id from shopping_product where shopping_product.pro_id='APPL666' ;
	@Query(value = "SELECT  shopping_product.shpr_id FROM shopping_product   WHERE shopping_product.pro_id= ?1", nativeQuery = true)
	public Integer findByIdShoppingProduct(String proId);
	
	
	
	
	@Query(value = "update shopping_product  \r\n" + 
			"set QUANTITY= ?2 +(select QUANTITY from shopping_product where shopping_product.pro_id=?1  )\r\n" + 
			"where shopping_product.pro_id=?1 ;", nativeQuery = true)
	@Modifying
	public void updateQuantityById(String proId, Integer quantity );
}
