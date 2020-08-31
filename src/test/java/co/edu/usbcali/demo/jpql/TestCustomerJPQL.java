package co.edu.usbcali.demo.jpql;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest
class TestCustomerJPQL {
	
	private final static Logger log=LoggerFactory.getLogger(TestCustomerJPQL.class);
	
	@Autowired
	public  EntityManager entityManager;
	
	@BeforeEach
	public void beforeEach() {
		log.info("beforeEach");
		assertNotNull(entityManager,"El entityManager es nulo");
	}
	
	@Test
	public void selectWhereParam() {
		log.info("selectWhereParam");
		String jqpl="SELECT cus FROM Customer cus WHERE cus.enable=:enable AND cus.email=:email";
		List<Customer> customers=entityManager.
				createQuery(jqpl,Customer.class).
				setParameter("enable", "Y").
				setParameter("email", "bropsdf@imgur.com").
				getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});
		
	}
	
	@Test
	public void selectWhereEnable() {
		log.info("selectWhereEnable");
		String jqpl="SELECT cus FROM Customer cus WHERE cus.enable='Y' ORDER BY cus.email";
		List<Customer> customers=entityManager.createQuery(jqpl,Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});
		
	}
	
	

	@Test
	public void selectLike() {
		log.info("selectLike");
		String jqpl="SELECT cus FROM Customer cus WHERE cus.name LIKE 'Mar%'";
		List<Customer> customers=entityManager.createQuery(jqpl,Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
		});
		
	}
	
	@Test
	public void selectAll() {
		log.info("selectAll");
		String jqpl="SELECT cus FROM Customer cus";
		List<Customer> customers=entityManager.createQuery(jqpl,Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
		});
		
		for (Customer customer : customers) {
			log.info(customer.getEmail());
			log.info(customer.getName());
		}
		
	}
	
	@Test
	public void selectWhereDisable() {
		log.info("selectWhereDisable");
		String jqpl="SELECT cus FROM Customer cus WHERE cus.enable='N' ORDER BY cus.name";
		List<Customer> customers=entityManager.createQuery(jqpl,Customer.class).getResultList();
		
		/*customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});*/
		for(Customer Xcustomer: customers) {
			log.info(Xcustomer.getName());
			log.info(Xcustomer.getPhone());
			log.info(Xcustomer.getAddress());
			
			
		}
		
	}
	@Test
	public void selectLike2() {
		log.info("selectLike2 adrres -> Scofield <-");
		String jqpl="SELECT cus FROM Customer cus WHERE cus.address LIKE '%Scofield%'";
		List<Customer> customers=entityManager.createQuery(jqpl,Customer.class).getResultList();
		
		customers.forEach(customer->{
			
			log.info(customer.getName());
			log.info(customer.getEmail());
		});
		
	}
	@Test
	public void selectLike3() {
		log.info("selectLike3 email ->@europa.eu<-");
		String jqpl="SELECT cus FROM Customer cus WHERE cus.email LIKE '%europa.eu'";
		List<Customer> customers=entityManager.createQuery(jqpl,Customer.class).getResultList();
		for(Customer Xcustomer : customers) {
			log.info(Xcustomer.getName());
			log.info(Xcustomer.getPhone());
			log.info(Xcustomer.getEnable());
			log.info("**************************************");
			
		}
		
	}
	@Test
	public void selectEmailsDisable() {
		log.info("selectEmailsDisable All");
		String jpql="SELECT cus FROM Customer cus WHERE cus.enable='N'";
		List<Customer> customersList = entityManager.createQuery(jpql,Customer.class).getResultList();
		customersList.forEach(customerList->{
			log.info(customerList.getName());
			log.info(customerList.getEmail());
		});
		
		
	}
	
	@Test
	public void selectEmailDisable2() {
		log.info("selectEmailDisable email->@europa.eu<-");
		String Correo0="economist.com";
		String Correo1="smugmug.com";
		String jpql="SELECT cus FROM Customer cus WHERE cus.email LIKE '%"+Correo0+"' AND cus.enable='N' ";
		List<Customer> customerListEmails0 = entityManager.createQuery(jpql,Customer.class).getResultList();
		
		jpql="SELECT cus FROM Customer cus WHERE cus.email LIKE '%"+Correo1+"'";
		log.info("Emails -> "+Correo0);
		for(Customer xCustomer: customerListEmails0) {
			log.info(xCustomer.getName());
			log.info(xCustomer.getEmail());
		
		}
		log.info("Emails -> "+Correo1);
		List<Customer> customerListEmails1 = entityManager.createQuery(jpql,Customer.class).getResultList();
		
		customerListEmails1.forEach(customerListEmail1->{
			log.info(customerListEmail1.getName());
			log.info(customerListEmail1.getEmail());
		});
		
		
		
		
	}
	

}
