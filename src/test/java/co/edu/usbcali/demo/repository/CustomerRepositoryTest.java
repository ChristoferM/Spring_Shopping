package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerRepositoryTest {
	
	private final static Logger log=LoggerFactory.getLogger(CustomerRepositoryTest.class);
	
	private final static String email="dgomez@vortexbird.com";
	
	@Autowired
	CustomerRepository customerRepository;
	

	@Test
	@Transactional
	@Order(1)
	void save() {
		log.info("save");
	
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		//Siga si es falso quiere decir que no existe
		assertFalse(customerOptional.isPresent(),"El customer ya existe");
		
		Customer customer=new Customer();
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Diego Gomez");
		customer.setPhone("316 482 4629");
		customer.setToken("NKJH43232KJ423KJ4234");		

		customerRepository.save(customer);
		
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		log.info("findById");
	
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		//Siga si es true. Quiere decir que existe
		assertTrue(customerOptional.isPresent(),"El customer no existe");
		
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		log.info("update");
	
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		//Siga si es true. Quiere decir que existe
		assertTrue(customerOptional.isPresent(),"El customer no existe");
		
		Customer customer=customerOptional.get();
		
		customer.setEnable("N");
		
		customerRepository.save(customer);		
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {		
		log.info("update");
	
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		//Siga si es true. Quiere decir que existe
		assertTrue(customerOptional.isPresent(),"El customer no existe");
		
		Customer customer=customerOptional.get();
		
		customerRepository.delete(customer);		
	}
	
	@Test
	@Transactional
	@Order(5)
	void findAll() {
		
		//Tradicional
		List<Customer> customers=customerRepository.findAll();
		for (Customer customer : customers) {
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());
		}
		
		//Funcional
		customerRepository.findAll().forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());
		});
		
	}
	
	@Test
	@Transactional
	@Order(6)
	void count() {
		//Retorna el total de customer
		log.info("Count:"+customerRepository.count());
	}
	
	@Test
	@Transactional
	@Order(7)
	void findByEnableAndEmail() {
		List<Customer> customers=customerRepository.findByEnableAndEmail("Y", "bropsdf@imgur.com");
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());
		});
	}
	
	@Test
	@Transactional
	@Order(8)
	void findCustomerLikemar() {
		List<Customer> customers=customerRepository.findCustomerLikemar();
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());
		});
	}
	
}
