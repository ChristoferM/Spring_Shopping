package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerTest {
	
	private final static String email="dgomez@vortexbird.com";
	
	private final static Logger log=LoggerFactory.getLogger(CustomerTest.class);
	
	@Autowired
	EntityManager entityManager;

	@Test
	@Transactional
	@Order(1)
	void save() {
		//Siga si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
		Customer customer=entityManager.find(Customer.class, email);
		
		//Continue la ejecucion  si es nulo
		assertNull(customer, "El cliente con email "+email+" ya existe");
		
		customer=new Customer();
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Diego Gomez");
		customer.setPhone("316 482 4629");
		customer.setToken("NKJH43232KJ423KJ4234");
		
		
		//entityManager.getTransaction().begin();
			entityManager.persist(customer);
		//entityManager.getTransaction().commit();
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		//Continue la ejecucion si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
	
		Customer customer=entityManager.find(Customer.class, email);
		
		//Continue la ejecucion si No es nulo
		assertNotNull(customer, "El cliente con email "+email+" no existe");
		
		log.info(customer.getName());
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		//Continue la ejecucion si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
	
		Customer customer=entityManager.find(Customer.class, email);
		
		//Continue la ejecucion si No es nulo
		assertNotNull(customer, "El cliente con email "+email+" no existe");
		
		customer.setEnable("N");
		
		entityManager.merge(customer);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		//Continue la ejecucion si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
	
		Customer customer=entityManager.find(Customer.class, email);
		
		//Continue la ejecucion si No es nulo
		assertNotNull(customer, "El cliente con email "+email+" no existe");
		
		entityManager.remove(customer);
	}

}

