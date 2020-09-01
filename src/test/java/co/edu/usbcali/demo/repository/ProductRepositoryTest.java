package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
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


import co.edu.usbcali.demo.domain.Product;


@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductRepositoryTest {
	
	private final static Logger log=LoggerFactory.getLogger(ProductRepositoryTest.class);

	
	private final static String idPro="APPL45";
	
	// En EntityManager no se usa en los repositorios
	@Autowired
	ProductRepository productRepositoryTest;
			
	@Test
	void save() {
		
		Optional<Product> productOptional=productRepositoryTest.findById(idPro);

		//Siga si es falso quiere decir que no existe
		assertFalse(productOptional.isPresent(),"El Product ya existe !!!");
				
		Product product = new Product();
		product.setProId(idPro);
		product.setName("iPhone X");
		product.setPrice(4500000);
		product.setDetail("Iphone X la nueva Generación");
		product.setImage("https://shopping-cart-usb.s3.amazonaws.com/images/iphone-11-pro-select-2019-family.jpeg");
		product.setEnable("Y");
		
		productRepositoryTest.save(product);
		log.info("Se guardo el registro");
		
	}

	@Test
	@Transactional
	@Order(2)
	void findById() {
		log.info("findById");
		Optional<Product> productOptional=productRepositoryTest.findById(idPro);
		
		
		//Siga si es true. Quiere decir que existe
		assertTrue(productOptional.isPresent(),"El Producto no existe");
		
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		log.info("update");
		Optional<Product> productOptional=productRepositoryTest.findById(idPro);
		
		
		//Siga si es true. Quiere decir que existe
		assertTrue(productOptional.isPresent(),"El Prodcuto no existe");
		
		Product product=productOptional.get();
		
		product.setEnable("N");
		
		productRepositoryTest.save(product);		
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {		
		log.info("update");
	
		Optional<Product> productOptional=productRepositoryTest.findById(idPro);
		
		//Siga si es true. Quiere decir que existe
		assertTrue(productOptional.isPresent(),"El customer no existe");
		
		Product product=productOptional.get();
		
		productRepositoryTest.delete(product);		
	}
	
	@Test
	@Transactional
	@Order(5)
	void findAll() {
		
		//Tradicional
		List<Product> products = productRepositoryTest.findAll();
		for (Product product : products) {
			log.info("Name:"+product.getName());
			log.info("Email:"+product.getDetail());
		}
		
		//Funcional
		productRepositoryTest.findAll().forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Email:"+product.getDetail());
		});
		
	}
	
	@Test
	@Transactional
	@Order(6)
	void count() {
		//Retorna el total de customer
		log.info("Count:"+productRepositoryTest.count());
	}
	@Test
	@Transactional
	@Order(7)
	void findByEnable() {
		List<Product> products = productRepositoryTest.findByEnable("Y");
		assertFalse(products.isEmpty());
		//devuelve si la lista esta tienes datos -> asserTrue funciona para comprobar que la lsita viene vacia
		products.forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Detalle:"+product.getDetail());
		});
	}
	@Test
	@Transactional
	@Order(8)
	void findByName() {
		List<Product> products = productRepositoryTest.findByName("iPhone X");
		assertFalse(products.isEmpty());
		//devuelve si la lista esta tienes datos -> asserTrue funciona para comprobar que la lsita viene vacia
		products.forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Detalle:"+product.getDetail());
		});
	}
	@Test
	@Transactional
	@Order(9)
	void findByLikeDetail() {
		List<Product> products = productRepositoryTest.findByLikeDetail();
		assertFalse(products.isEmpty());
		//devuelve si la lista esta tienes datos -> asserTrue funciona para comprobar que la lsita viene vacia
		products.forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Detalle:"+product.getDetail());
		});
	}
	
	@Test
	@Transactional
	@Order(9)
	void finByIphone() {
		List<Product> products = productRepositoryTest.finByIphone();
		assertFalse(products.isEmpty());
		//devuelve si la lista esta tienes datos -> asserTrue funciona para comprobar que la lsita viene vacia
		products.forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Detalle:"+product.getDetail());
		});
	}
	
	@Test
	@Transactional
	@Order(9)
	void finByCostMin() {
		List<Product> products = productRepositoryTest.finByCostMin();
		assertFalse(products.isEmpty());
		//devuelve si la lista esta tienes datos -> asserTrue funciona para comprobar que la lsita viene vacia
		products.forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Detalle:"+product.getDetail());
		});
	}
}
