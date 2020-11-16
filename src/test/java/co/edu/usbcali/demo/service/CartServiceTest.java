package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@SpringBootTest
@Rollback(false)
class CartServiceTest {
	
	@Autowired
	CartService cartService;

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
	
	@Test
	void debeAgregarProductShoppingCart()throws Exception {
		//Arrange
		Integer carId=9;
		//String proId="APPL666";
		//Integer quantity=10;
		//String proId="APPL699";
		//Integer quantity=3;
		String proId="APPL693";
		Integer quantity=6;
		ShoppingProduct shoppingProduct=null;
		
		//Act
		shoppingProduct=cartService.addProduct(carId, proId, quantity);
		
		//Assert
		assertNotNull(shoppingProduct, "El shoppingProduct es nulo");
	}
	
	@Test
	void eliminarProductoDeUnShoppingCarShoppingProduct()throws Exception {
		//Arrange
		Integer carId=9;
		String proId="APPL693";
		
		//Act
		cartService.removeProduct(carId, proId);
		
		//Assert
		
	
	}
	
	
}