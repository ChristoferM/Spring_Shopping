package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

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
@TestMethodOrder(OrderAnnotation.class) // no permite dar el orden de ejecución de las pruebas
class CustomerTest {
	
	private final static String email="dgomez@vortexbird.com";
	
	//  sfl4fj
	private final static Logger log=LoggerFactory.getLogger(Customer.class);
	
	
	@Autowired
	EntityManager entityManager;

	@Test
	@Transactional
	@Order(1)
	void save() {
		// verifica que el Entity no sea nulo -> Siga si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
		/*
		 *	Si no es nulo, el procedimineto continua 
		 * 
		 */
		
		// Se carga la siguiente acción a realizar
		Customer customer = entityManager.find(Customer.class, email);
		
		//Si la condición ES NULA continuar el procedimiento 
		assertNull(customer , "el cliente con email "+email+ " No esta registrado ");
		/*
		 *	Si es nulo (No existe), el procedimineto continua 
		 * 
		 */
		
		customer = new Customer();
		customer.setAddress("avenida siempre viva 123");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Diego Gomez");
		customer.setPhone("316 482 4629");
		customer.setToken("NKJH43232KJ423KJ4234");
		
		entityManager.persist(customer);
	}

	@Test
	@Transactional
	@Order(2)
	void findId() {
		
		// verifica que el Entity no sea nulo -> Siga si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
				
		// 
		Customer customer = entityManager.find(Customer.class, email);
		//Siga si NO es nulo
		assertNotNull(customer , "el cliente con email "+email+ "  ya esta registrado ");
		
			
		log.info(customer.getName());
				
	}
	@Test
	@Transactional
	@Order(3)
	void upDate() {
		// verifica que el Entity no sea nulo -> Siga si no es nulo
				assertNotNull(entityManager, "El entityManager es nulo");
				
		// 
		Customer customer = entityManager.find(Customer.class, email);
		//Siga si NO es nulo
		assertNotNull(customer , "el cliente con email "+email+ "  No existe ");
		
		customer.setEnable("N");
		
		entityManager.merge(customer);
}
	

	@Test
	@Transactional
	@Order(4)
	void delete() {
		// verifica que el Entity no sea nulo -> Siga si no es nulo
				assertNotNull(entityManager, "El entityManager es nulo");
				
		// 
		Customer customer = entityManager.find(Customer.class, email);
		//Continue la ejecucion Si no es nulo
		assertNotNull(customer , "el cliente con email "+email+ "  ya esta registrado ");
		/*
		 *	Si No es nulo (Es porque existe el registro buscado 	), el procedimineto continua 
		 * 
		 */
		
		entityManager.remove(customer);
		
		}

}
