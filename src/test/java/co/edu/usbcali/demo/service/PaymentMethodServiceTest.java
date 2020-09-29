package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.PaymentMethod;




@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class PaymentMethodServiceTest {

	
	private final static Logger log=LoggerFactory.getLogger( PaymentMethodServiceTest.class);
	
	private static Integer payId=null;
	
	@Autowired
	PaymentMethodService paymentMethodService;
	

	@Test
	@Transactional 
	@Order(1)
	void save() throws Exception {
		log.info("SAVE");
		
		PaymentMethod paymentMethod=new PaymentMethod();
		paymentMethod.setEnable("Y");
		paymentMethod.setName("DAVIPLATA");
		
		paymentMethod=paymentMethodService.save(paymentMethod);
		payId=paymentMethod.getPayId();
		assertNotNull(payId,"Error El payId es nulo");
		log.info("Todo bien todo bien");
		/*
		private Integer payId;
		private String enable;
		private String name;
		private List<ShoppingCart>
		
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Diego Gomez");
		customer.setPhone("316 482 4629");
		customer.setToken("NKJH43232KJ423KJ4234");		

		customerService.save(customer);*/
		
	}
	@Test
	@Transactional 
	@Order(2)
	void findById() throws Exception{
		log.info("FindByID");
	
		Optional<PaymentMethod> paymentMethodOptional=paymentMethodService.findById(payId);
		
		//Siga si es true. Quiere decir que existe
		assertTrue(paymentMethodOptional.isPresent(),"El PaymentMethod no existe;Error");
	}
	@Test
	@Transactional 
	@Order(3)
	void update() throws Exception {
		log.info("UPDATE");
	
		Optional<PaymentMethod> customerOptional=paymentMethodService.findById(payId);
		
		//Siga si es true. Quiere decir que existe
		assertTrue(customerOptional.isPresent(),"El PaymentMethod no existe");
		
		PaymentMethod paymentMethod=customerOptional.get();
		
		paymentMethod.setEnable("Y");
		
		paymentMethodService.save(paymentMethod);		
	}
	@Test
	@Transactional 
	@Order(4)
	void delete() throws Exception{		
		log.info("DELETE");
	
		Optional<PaymentMethod> customerOptional=paymentMethodService.findById(payId);
		
		//Siga si es true. Quiere decir que existe
		assertTrue(customerOptional.isPresent(),"El PaymentMethod no existe");
		
		PaymentMethod paymentMethod=customerOptional.get();
		
		paymentMethodService.delete(paymentMethod);		
	}
	
	
	

}
