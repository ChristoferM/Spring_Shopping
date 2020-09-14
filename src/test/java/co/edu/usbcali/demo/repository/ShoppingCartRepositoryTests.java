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

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.PaymentMethod;
//import co.edu.usbcali.demo.repository.CustomerRepository;
//import co.edu.usbcali.demo.repository.PaymentMethodRepository;
@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)


class ShoppingCartRepositoryTests {
	
	private final static Logger log=LoggerFactory.getLogger( ShoppingCart.class);
	
	private static Integer carId=null;
	private static String email="abaglowbn@furl.net"; 
			
	private static Integer payId=1;
	
	@Autowired
	ShoppingCartRespository shoppingCartRespository;
	
	@Autowired
	CustomerRepository  customerRespository;
	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		ShoppingCart shoppingCart=new ShoppingCart();
		shoppingCart.setCarId(null);
		shoppingCart.setItems(2);
		shoppingCart.setTotal(12313234L);
		shoppingCart.setEnable("Y");
				
		Optional<Customer> CustomerOptional =customerRespository.findById(email);
		assertTrue(CustomerOptional.isPresent(),"El customer Con email"+email+" No existe"  );
		
		Customer customer =CustomerOptional.get();
		shoppingCart.setCustomer(customer);
		
		Optional<PaymentMethod> paymentMethodOptional =paymentMethodRepository.findById(payId);
		assertTrue(paymentMethodOptional.isPresent(),"El customer Con email"+payId+" No existe"  );
		
		PaymentMethod paymentMethod=  paymentMethodOptional.get();
		shoppingCart.setPaymentMethod(paymentMethod);
		
		shoppingCart = shoppingCartRespository.save(shoppingCart);
		
		carId=shoppingCart.getCarId();
		assertNotNull(carId,"El CardId Es nulo");
		log.info("");
	
		
	}
	
	@Test
	@Transactional
	@Order(2)
	void finById() {
		
		Optional<ShoppingCart> shoppingCartOptional = shoppingCartRespository.findById(carId);
		
		assertNotNull(shoppingCartOptional.isPresent(),"El shoppingCart con CarId "+carId+" No existe");
		
		
	}
	
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		
		Optional<ShoppingCart> shoppingCartOptional = shoppingCartRespository.findById(carId);
		
		assertNotNull(shoppingCartOptional.isPresent(),"El shoppingCart con CarId "+carId+" No existe");
		
		ShoppingCart shoppingCart =  shoppingCartOptional.get();
		shoppingCart.setEnable("N");
		
		shoppingCartRespository.save(shoppingCart);
		
		
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		
		Optional<ShoppingCart> shoppingCartOptional = shoppingCartRespository.findById(carId);
		
		assertNotNull(shoppingCartOptional.isPresent(),"El shoppingCart con CarId "+carId+" No existe");
		
		ShoppingCart shoppingCart =  shoppingCartOptional.get();
		shoppingCartRespository.delete(shoppingCart);
		
	}
	
	
}
