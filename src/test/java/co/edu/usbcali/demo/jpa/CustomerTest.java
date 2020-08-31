package co.edu.usbcali.demo.jpa;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.*;
=======
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
>>>>>>> fb4dcc3... 30Agosto

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

<<<<<<< HEAD

=======
>>>>>>> fb4dcc3... 30Agosto
import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest
@Rollback(false)
<<<<<<< HEAD
@TestMethodOrder(OrderAnnotation.class) // no permite dar el orden de ejecución de las pruebas
=======
@TestMethodOrder(OrderAnnotation.class)
>>>>>>> fb4dcc3... 30Agosto
class CustomerTest {
	
	private final static String email="dgomez@vortexbird.com";
	
<<<<<<< HEAD
	//  sfl4fj
	private final static Logger log=LoggerFactory.getLogger(Customer.class);
	
=======
	private final static Logger log=LoggerFactory.getLogger(CustomerTest.class);
>>>>>>> fb4dcc3... 30Agosto
	
	@Autowired
	EntityManager entityManager;

	@Test
	@Transactional
	@Order(1)
	void save() {
<<<<<<< HEAD
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
=======
		//Siga si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
		Customer customer=entityManager.find(Customer.class, email);
		
		//Continue la ejecucion  si es nulo
		assertNull(customer, "El cliente con email "+email+" ya existe");
		
		customer=new Customer();
		customer.setAddress("Avenida Siempre Viva 123");
>>>>>>> fb4dcc3... 30Agosto
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Diego Gomez");
		customer.setPhone("316 482 4629");
		customer.setToken("NKJH43232KJ423KJ4234");
		
<<<<<<< HEAD
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
=======
		
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
>>>>>>> fb4dcc3... 30Agosto
		
		customer.setEnable("N");
		
		entityManager.merge(customer);
<<<<<<< HEAD
}
	

=======
	}
	
>>>>>>> fb4dcc3... 30Agosto
	@Test
	@Transactional
	@Order(4)
	void delete() {
<<<<<<< HEAD
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
=======
		//Continue la ejecucion si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
	
		Customer customer=entityManager.find(Customer.class, email);
		
		//Continue la ejecucion si No es nulo
		assertNotNull(customer, "El cliente con email "+email+" no existe");
		
		entityManager.remove(customer);
	}
>>>>>>> fb4dcc3... 30Agosto

}
