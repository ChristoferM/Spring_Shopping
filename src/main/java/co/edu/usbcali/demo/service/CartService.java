package co.edu.usbcali.demo.service;

import java.util.List;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface CartService {
	
	public ShoppingCart createCart(String email)throws Exception;
	public ShoppingProduct addProduct(Integer carId,String proId,Integer quantity )throws Exception;
	public void removeProduct(Integer carId,String proId)throws Exception;
	public void clearCart(Integer carId)throws Exception;
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId)throws Exception;
	// Buscar un ShoppingCar Con Base al Correo
	public List<ShoppingCart> findShoppingCart(String email)throws Exception;
	public List<ShoppingProduct> findProductByShpId(String email) throws Exception;
	public void deleteShoppingProduct(String pro_id,Integer carId ) throws Exception;
	

}
