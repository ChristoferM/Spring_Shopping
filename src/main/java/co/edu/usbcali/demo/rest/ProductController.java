package co.edu.usbcali.demo.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.dto.CustomerDTO;
import co.edu.usbcali.demo.dto.ProductDTO;
import co.edu.usbcali.demo.repository.ProductRepository;
import co.edu.usbcali.demo.mapper.ProductMapper;

@RestController // Servicio
@RequestMapping("/api/product") // Forma de llamar datos
public class ProductController {
	private final static Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductMapper productMapper;
	
	
	@RequestMapping("/save")
	public ResponseEntity<?> save(@RequestBody ProductDTO productDTO){
		//El DTO son los datos que necesitamos 
		//el la clase es la que contiene todos los datos incluyendo datos que el usuario no necesita ver Por ejemplo ID
		try {
			
			Product product=productMapper.toCustomer(productDTO);
			
			product=productRepository.save(product);
			
			productDTO=productMapper.toProductDTO(product);
			
			return ResponseEntity.ok().body(productDTO); 
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return ResponseEntity.ok().body("Product  Not Found");
		}
		
	}
	@RequestMapping("/finByAll")
	public ResponseEntity<?> finByAll() {
		// http://localhost:9090/api/product/finByAll
		try {
			List<Product> products = productRepository.findAll();
			
			List<ProductDTO> productDTOs = productMapper.toProductDTO(products);
						 
			/*
			 * List<ProductDTO> productDTOs= new ArrayList<>();
			 Product product = new Product();
			  products.forEach(product->{ ProductDTO productDTO = new ProductDTO();
			  productDTO.setDetail(product.getDetail());
			  productDTO.setEnable(product.getEnable());
			  productDTO.setImage(product.getImage());
			  productDTO.setName(product.getName());
			  productDTO.setPrice(product.getPrice());
			  productDTO.setProId(product.getProId());
			  
			 * });
			 */
			return ResponseEntity.ok().body(productDTOs);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return ResponseEntity.ok().body("Customer  Not Found");
		}
	
	}
	
	
	
	//APPL45
	@RequestMapping("/finById/{proId}")
	public ResponseEntity<?> finById(@PathVariable("proId") String proId) {
		// http://localhost:9090/api/customer/finById/APPL45
		try {
			Optional<Product> productOptional = productRepository.findById(proId);
			if (productOptional.isPresent() == false) {
				return ResponseEntity.ok().body("Customer Not Found");
			}

			Product product = productOptional.get();
			ProductDTO productDTO = productMapper.toProductDTO(product);

			// Se pasa la información del Entity al DTO
			/*
			 * List<ProductDTO> productDTOs= new ArrayList<>();
			 Product product = new Product();
			  products.forEach(product->{ ProductDTO productDTO = new ProductDTO();
			  productDTO.setDetail(product.getDetail());
			  productDTO.setEnable(product.getEnable());
			  productDTO.setImage(product.getImage());
			  productDTO.setName(product.getName());
			  productDTO.setPrice(product.getPrice());
			  productDTO.setProId(product.getProId());
			 */

			return ResponseEntity.ok().body(productDTO);
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	
}
