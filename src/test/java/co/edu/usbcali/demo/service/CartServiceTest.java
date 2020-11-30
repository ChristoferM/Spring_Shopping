package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final static Logger log=LoggerFactory.getLogger(CartServiceTest.class);
	@Test
	void debeCrearUnShoppingCart()throws Exception {
		//Arrange
		String email="abaglowbn@furl.net";
		// String email="sruberrya@spiegel.de";
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
		Integer carId=15;
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
		// APPL693
		// APPL666
		// APPL90
		Integer carId=15;
		String proId="APPL699";
		Integer quantity=1;
		ShoppingProduct shoppingProduct=null;
		
		//Act
		shoppingProduct=cartService.addProduct(carId, proId, quantity);
		
		proId="APPL693";
		quantity=1;
		shoppingProduct=cartService.addProduct(carId, proId, quantity);
		
		proId="APPL666";
		quantity=1;
		shoppingProduct=cartService.addProduct(carId, proId, quantity);
		
		proId="APPL90";
		quantity=1;
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
		// APPL693
		// APPL666
		// APPL90
		String proId="APPL45";
		
		//Act
		cartService.removeProduct(carId, proId);
	}
	
	@Test
	void debeRemoverProductAPPL90ShoppingCart()throws Exception {
		//Arrange
		Integer carId=9;
		String proId="APPL45";
		
		//Act
		cartService.removeProduct(carId, proId);
	}
	
	@Test
	void debeLimpiarElCart() throws Exception{
		//Arrange
		Integer carId=16;
		
		//Act
		cartService.clearCart(carId);
	}
		

	@Test
	void EncontrarCarritosDeCompra() throws Exception{
		//Arrange
		//Integer carId=16;
		String email="sruberrya@spiegel.de";
		//Act
		cartService.findShoppingCart(email);
	}
	

	@Test
	void EncontrarProductosDeCarritoDeCompra() throws Exception{
		//Arrange
		//Integer carId=16;
		String email="sruberrya@spiegel.de";
		//ActShoppingProduct shoppingProduct=null;
		
		//Act
		 List<ShoppingProduct> shoppingProducts =cartService.findProductByShpId(email);
		 for (ShoppingProduct shoppingProduct : shoppingProducts) {
				log.info(shoppingProduct.getShprId().toString());
				log.info(shoppingProduct.getQuantity().toString());
				log.info(shoppingProduct.getProduct().getProId());
				
			}
			
		
	}
}
