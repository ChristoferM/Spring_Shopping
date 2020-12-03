package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Product;

// Se llama la clase (Product) y el tipo de dato que tiene la clave (String)
public interface ProductRepository  extends JpaRepository<Product, String> {

	public List<Product> findByEnable(String enable);
	
	public List<Product> findByName(String name);
	
	@Query("SELECT pro FROM Product pro WHERE pro.name like '%iPhone%'")
	public List<Product> finByIphone();
		
	@Query("SELECT pro FROM Product pro WHERE pro.detail  like '%Iphone X%'")
	public List<Product>findByLikeDetail();
	
	@Modifying
	@Query(value ="UPDATE product set enable='N' WHERE product.pro_id= ?1 ;", nativeQuery = true)
	public void switchDisable(String prodId);
	
	
	@Modifying
	@Query(value ="UPDATE product set enable='Y' WHERE product.pro_id= ?1 ;", nativeQuery = true)
	public void switchEnable(String prodId);
	/*@Query("SELECT pro.name, pro.detail,min(pro.price) FROM Product pro  GROUP BY (pro.name,pro.detail,pro.price)")
	public List<Product> finByCostMin();*/
}
