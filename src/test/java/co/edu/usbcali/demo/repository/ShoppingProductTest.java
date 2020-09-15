package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;


@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ShoppingProductTest {

	
	
	@Autowired
	ShoppingProductRepository shoppingProductRepository;
	
	@Autowired
	ShoppingCartRespository shoppingCartRespository;
	
	@Autowired
	ProductRepository productRepository;
	
	private final static Logger log=LoggerFactory.getLogger( ShoppingProduct.class);
	
	private static Integer sphrId=null;
	
	private static String proId="APPL45"; 
			
	private static Integer cart=1;
	
	
	// ***** shooping_cart
	// carId 1; total 1231234 ; items 2; email abaglowbn@furl.net; payId 1; enable;
	// ***** product
	// proId APPL45; name iPhone X ; price 4500000; detail Iphone X la nueva Generación ;
	// image https://shopping-cart-usb.s3.amazonaws.com/images/iphone-11-pro-select-2019-family.jpeg;
	// Enable Y
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		ShoppingProduct shoppingProduct=new ShoppingProduct();
		shoppingProduct.setShprId(null);
		shoppingProduct.setQuantity(5);
		shoppingProduct.setTotal((long)50000);
 			
		Optional<Product> ProductOptional =productRepository.findById(proId);
		assertTrue(ProductOptional.isPresent(),"El Producto Con Identificador"+proId+" No existe"  );
		
		Product product =ProductOptional.get();
		shoppingProduct.setProduct(product);
		
		Optional<ShoppingCart> shoppingCartOptional =shoppingCartRespository.findById(cart);
		assertTrue(shoppingCartOptional.isPresent(),"El customer Con email"+cart+" No existe"  );
		
		ShoppingCart shoppingCart=  shoppingCartOptional.get();
		shoppingProduct.setShoppingCart(shoppingCart);
		
		shoppingProduct = shoppingProductRepository.save(shoppingProduct);
		
		sphrId=shoppingProduct.getShprId();
		assertNotNull(sphrId,"El CardId Es nulo");
		log.info("Datos Agregados Correctamente");
		
	}
	
	
	@Test
	@Transactional
	@Order(2)
	void finById() {
		//sphrId=1;
		Optional<ShoppingProduct> shoppingProductOptional = shoppingProductRepository.findById(sphrId);
		assertNotNull(shoppingProductOptional.isPresent(),"El ShoppingProduct con ID "+sphrId+" No existe \n Fallo Correctamente");
		log.info("Id encontrado");
		
	}
	@Test
	@Transactional
	@Order(3)
	void update() {
		//sphrId=1;
		Optional<ShoppingProduct> shoppingProductOptional = shoppingProductRepository.findById(sphrId);
		
		assertNotNull(shoppingProductOptional.isPresent(),"El ShoppingProduct con ID "+sphrId+" No existe \n Fallo Correctamente");
		
		ShoppingProduct shoppingProduct =  shoppingProductOptional.get();
		shoppingProduct.setTotal((long)66656);
		
		shoppingProductRepository.save(shoppingProduct);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		
		//sphrId=1;
				
		Optional<ShoppingProduct> shoppingProductOptional = shoppingProductRepository.findById(sphrId);
		
		assertNotNull(shoppingProductOptional.isPresent(),"El ShoppingProduct con ID "+sphrId+" No existe \n Fallo Correctamente");
		
		ShoppingProduct shoppingProduct =  shoppingProductOptional.get();
		shoppingProductRepository.delete(shoppingProduct);
		
	}
}
