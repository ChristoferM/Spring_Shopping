package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ShoppingProductService extends GenericService<ShoppingProduct, Integer> {
	
	public Long totalShoppingProductByShoppingCart(Integer carId);
	public Integer quantityShoppingProductByShoppingCart(Integer carId);
	public ShoppingProduct findByShoppingCartAndProduct(Integer carId,String proId);
	
	public void deleteProductsByShoppingCart(Integer carId) throws Exception;
	
	public List<ShoppingProduct> findProductByShpId(String email);
	
	
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId);
	
	void deleteShoppingProduct(String pro_id, Integer carId) throws Exception;
	
}
