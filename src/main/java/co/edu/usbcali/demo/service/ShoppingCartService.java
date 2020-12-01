package co.edu.usbcali.demo.service;


import java.util.List;

import co.edu.usbcali.demo.domain.ShoppingCart;

public interface ShoppingCartService  extends GenericService<ShoppingCart,Integer> {

	Integer findByIdEnable(String email);
	//ShoppingCartService

	public List<ShoppingCart> findShoppingCart(String email);
	
}
