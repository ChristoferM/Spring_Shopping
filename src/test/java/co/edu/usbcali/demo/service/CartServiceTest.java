package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@SpringBootTest
@Rollback(false)
class CartServiceTest {
	
	@Autowired
	CartService cartService;
	private final static Logger log=LoggerFactory.getLogger(CartServiceTest.class);
	
	
	
	@Test
	void debeCrearUnShoppingCart()throws Exception {
		//Arrange
		String email="abaglowbn@furl.net";
		ShoppingCart shoppingCart=null;
		
		//Act
		shoppingCart=cartService.createCart(email);
		
		//Assert
		assertNotNull(shoppingCart);
	}
	
	@Test
	void noDebeCrearUnShoppingCartPorCustomerDisable()throws Exception {
		//Arrange
		String email="abeamondqq@harvard.edu";
		
		//Act
		assertThrows(Exception.class, ()->cartService.createCart(email));
		
	}
	
	@Test
	void noDebeCrearUnShoppingCartPorCustomerNull()throws Exception {
		//Arrange
		String email=null;
		
		//Act
		assertThrows(Exception.class, ()->cartService.createCart(email));		
	}
	
	@Test
	void noDebeCrearUnShoppingCartPorCustomerNoExiste()throws Exception {
		//Arrange
		String email="jperez@vvv.com";
		
		//Act
		assertThrows(Exception.class, ()->cartService.createCart(email));		
	}
	
	/*
	 * Tarea
	 * */
	
	@Test
	void debeAgregarProductShoppingCart()throws Exception {
		log.info("**** METODO debeAgregarProductShoppingCart****");
		//Arrange
		Integer carId=9;
		ShoppingProduct shoppingProduct=null;
		//Act
		String proId="APPL666";
		Integer quantity=3;

		shoppingProduct=cartService.addProduct(carId, proId, quantity);		
		/*
		//Act
		proId="APPL699";
		quantity=3;
		shoppingProduct=cartService.addProduct(carId, proId, quantity);
		
		//Act
		proId="APPL693";
		quantity=6;
		shoppingProduct=cartService.addProduct(carId, proId, quantity);
		*/
	
		//Assert
		assertNotNull(shoppingProduct, "El shoppingProduct es nulo");
	}
	
	@Test
	void deleteProduct()throws Exception {
		//Arrange
		Integer carId=9;
		String proId="APPL693";
		//Act
		cartService.removeProduct(carId, proId);	
	}
	@Test
	void clerarShoppingProduct()throws Exception {
		//Arrange
		Integer carId=9;	
		//Act
		cartService.clearCart(carId);		
	}
	
	@Test
	void findShoppingProductByShoppingCart()throws Exception {
		//Arrange
		Integer carId=9;	
		//Act
		List<ShoppingProduct> shoppingProducts =cartService.findShoppingProductByShoppingCart(carId);
		
		
		
		// Es correcto sacar el ShoppingProduct o la idea esa sacar una lista productos
	}
	
	
}