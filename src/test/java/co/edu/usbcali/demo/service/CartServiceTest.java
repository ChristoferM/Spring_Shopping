package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
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
	@Disabled
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
	void debeAgregarProductAPPL45ShoppingCart()throws Exception {
		//Arrange
		Integer carId=9;
		String proId="APPL45";
		Integer quantity=5;
		ShoppingProduct shoppingProduct=null;
		
		//Act
		shoppingProduct=cartService.addProduct(carId, proId, quantity);
		
		//Assert
		assertNotNull(shoppingProduct, "El shoppingProduct es nulo");
	}
	
	@Test
	void debeAgregarProductShoppingCart()throws Exception {
		//Arrange
		Integer carId=9;
		String proId="APPL699";
		Integer quantity=1;
		ShoppingProduct shoppingProduct=null;
		
		//Act
		shoppingProduct=cartService.addProduct(carId, proId, quantity);
		
		//Assert
		assertNotNull(shoppingProduct, "El shoppingProduct es nulo");
	}
	
	@Test
	void debeRemoverProductShoppingCart()throws Exception {
		//Elimina los valores de Cantidad y de Valor 
		//No elimina el registro como tal
		//Arrange
		Integer carId=9;
		String proId="APPL699";
		
		//Act
		cartService.removeProduct(carId, proId);
	}
	
	@Test
	void debeRemoverProductAPPL90ShoppingCart()throws Exception {
		//Arrange
		Integer carId=9;
		String proId="APPL90";
		
		//Act
		cartService.removeProduct(carId, proId);
	}
	
	@Test
	void debeLimpiarElCart() throws Exception{
		//Arrange
		Integer carId=9;
		
		//Act
		cartService.clearCart(carId);
	}
	
	

}
