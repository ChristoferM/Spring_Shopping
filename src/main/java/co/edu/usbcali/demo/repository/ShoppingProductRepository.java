package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ShoppingProductRepository extends JpaRepository<ShoppingProduct, Integer> {
	
	@Query("SELECT SUM(shpr.total) FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public Long totalShoppingProductByShoppingCart(Integer carId);
	
	@Query("SELECT SUM(shpr.quantity) FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public Integer quantityShoppingProductByShoppingCart(Integer carId);
	
	@Query("SELECT shpr FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId and shpr.product.proId=:proId")
	public ShoppingProduct findByShoppingCartAndProduct(Integer carId,String proId);
	
	@Query("SELECT shpr FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId);
	
	@Modifying
	@Query("DELETE FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public void deleteProductsByShoppingCart(Integer carId);
	
	@Query(value =" select * from SHOPPING_PRODUCT WHERE SHOPPING_PRODUCT.CAR_ID=(select shopping_cart.car_id from shopping_cart WHERE shopping_cart.email=?1 AND shopping_cart.enable='Y');  ", nativeQuery = true)
	public List<ShoppingProduct> findProductByShpId(String email);
}
