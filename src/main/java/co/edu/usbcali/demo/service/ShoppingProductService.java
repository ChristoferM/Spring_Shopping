package co.edu.usbcali.demo.service;


import co.edu.usbcali.demo.domain.ShoppingProduct;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ShoppingProductService extends GenericService<ShoppingProduct, Integer> {
	
	public Long totalShoppingProductByShoppingCart(Integer carId);
	public ShoppingProduct findProductById(String proId);
	public Integer findShprByIdProduct(String proId);
	public void updateQuantityById(String proId, Integer quantity);
}
