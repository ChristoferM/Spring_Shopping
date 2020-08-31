package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;
<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertNull;
=======

>>>>>>> fb4dcc3... 30Agosto

import javax.persistence.EntityManager;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import co.edu.usbcali.demo.domain.Customer;
=======

>>>>>>> fb4dcc3... 30Agosto
import co.edu.usbcali.demo.domain.Product;




@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
public class ProductTest {

	private final static String idPro="APPL45";
	
	private final static Logger log = LoggerFactory.getLogger(ProductTest.class);
	//private final static Logger log=LoggerFactory.getLogger(Product.class);
	@Autowired
	EntityManager entityManager;
	
	@Test
	@Transactional
	@Order(1)
	void test() {
		// verifica que el Entity no sea nulo -> Siga si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
		
		Product product=entityManager.find(Product.class,idPro);
		
		//continua la ejecución si no es nulo
		assertNotNull(product,"El producto "+idPro+" no existe");
		
		log.info(product.getProId());
		log.info(product.getName());
		
		}
	
	@Test
	@Transactional
	@Order(2)
	void findId() {
		
		// verifica que el Entity no sea nulo -> Siga si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
				
		// 
		Product product = entityManager.find(Product.class, idPro);
		//Siga si NO es nulo
		assertNotNull(product , "elproducto con la identificacionl "+idPro+ "  ya esta registrado ");
		
		log.info(product.getProId());	
		log.info(product.getName());
				
	}
	@Test
	@Transactional
	@Order(3)
	void upDate() {
		/*
		 * actualizar un registro o el atributo de un registro
		 * Si el identificador exite 
		 * */
		// verifica que el Entity no sea nulo -> Siga si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
		// 
		Product product = entityManager.find(Product.class, idPro);
		//Siga si NO es nulo
		
		assertNotNull(product , "el producto con la identificacion "+idPro+ "  No existe ");
		
<<<<<<< HEAD
		product.setEnable("N");
=======
		product.setEnable("Y");
>>>>>>> fb4dcc3... 30Agosto
		
		entityManager.merge(product);
		
		
	}

	@Test
	@Transactional
	@Order(4)
	void delete() {
		// verifica que el Entity no sea nulo -> Siga si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
				
		// 
		Product product = entityManager.find(Product.class, idPro);
		//Continue la ejecucion Si no es nulo
		assertNotNull(product , "Producto con el identificador"+idPro+ "  ya esta registrado ");
		/*
		 *	Si No es nulo (Es porque existe el registro buscado 	), el procedimineto continua 
		 * 
		 */
		
		entityManager.remove(product);
		}
	
	@Test
	@Transactional
	@Order(5)
	void save() {
		// verifica que el Entity no sea nulo -> Siga si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
<<<<<<< HEAD
		System.out.println("**********************************************1");
		Product product=entityManager.find(Product.class,"APPL90");
		System.out.println("**********************************************2");
		//continua la ejecución si no es nulo
		assertNotNull(product,"El producto APPL90 no existe");
		System.out.println("**********************************************3");
=======
		
		Product product=entityManager.find(Product.class,"APPL90");
		
		//continua la ejecución si no es nulo
		assertNotNull(product,"El producto APPL90 no existe");
		
>>>>>>> fb4dcc3... 30Agosto
		log.info(product.getProId());
		log.info(product.getName());
		
		product = new Product();
		product.setProId(idPro);
		product.setName("iPhone X");
		product.setPrice(4500000);
		product.setDetail("Iphone X la nueva Generación");
<<<<<<< HEAD
		product.setImge("https://shopping-cart-usb.s3.amazonaws.com/images/iphone-11-pro-select-2019-family.jpeg");
=======
		product.setImage("https://shopping-cart-usb.s3.amazonaws.com/images/iphone-11-pro-select-2019-family.jpeg");
>>>>>>> fb4dcc3... 30Agosto
		product.setEnable("Y");
		
		entityManager.persist(product);
		
		}
	
}