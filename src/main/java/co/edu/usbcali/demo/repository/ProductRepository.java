package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Product;

// Se llama la clase (Product) y el tipo de dato que tiene la clave (String)
public interface ProductRepository  extends JpaRepository<Product, String> {

	public List<Product> findByEnable(String enable);
	public List<Product> findByName(String name);
	
	@Query("SELECT cus FROM Product cus WHERE cus.name like '%iPhone%'")
	public List<Product> finByIphone();
		
	@Query("SELECT cus FROM Product cus WHERE cus.detail  like '%Iphone X%'")
	public List<Product>findByLikeDetail();
	
	@Query("SELECT cus.name, cus.detail, cus.min(price)" + 
			"FROM Product cus " + 
			"GROUP BY (cus.name,cus.detail,cus.price)")
	public List<Product> finByCostMin();
}
