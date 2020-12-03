package co.edu.usbcali.demo.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.dto.ProductDTO;
//import co.edu.usbcali.demo.repository.ProductRepository;
import co.edu.usbcali.demo.service.ProductService;
import co.edu.usbcali.demo.mapper.ProductMapper;

@RestController // Servicio
@RequestMapping("/api/product") // Forma de llamar datos
@CrossOrigin
public class ProductController {
	private final static Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductMapper productMapper;
	
	
	@RequestMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody ProductDTO productDTO)  throws Exception{
		//El DTO son los datos que necesitamos 
		//el la clase es la que contiene todos los datos incluyendo datos que el usuario no necesita ver Por ejemplo ID
			Product product=productMapper.toCustomer(productDTO);
			
			product = productService.save(product);
			
			productDTO=productMapper.toProductDTO(product);
			
			return ResponseEntity.ok().body(productDTO); 
		
		}

	
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody ProductDTO productDTO) throws Exception {
		//El DTO son los datos que necesitamos 
		//el la clase es la que contiene todos los datos incluyendo datos que el usuario no necesita ver Por ejemplo ID
			Product product=productMapper.toCustomer(productDTO);
			
			product=productService.update(product);
			
			productDTO=productMapper.toProductDTO(product);
			
			return ResponseEntity.ok().body(productDTO); 
	
		
	}
	
	@PutMapping("/Enable/{proId}")
	public ResponseEntity<?> switchEnable(@PathVariable("proId") String proId) throws Exception{
		if (proId == null) {
			throw new Exception("Erro Con el Id Del producto ");
			
		}
		productService.switchEnable(proId);
		return ResponseEntity.ok().build();
		
	}
	@PutMapping("/Disable/{proId}")
	public ResponseEntity<?> switchDisable(@PathVariable("proId") String proId) throws Exception{
		if (proId == null) {
			throw new Exception("Erro Con el Id Del producto ");
				
		}
		productService.switchDisable(proId);
		return ResponseEntity.ok().build();
	}

	
	@RequestMapping("/finByAll")
	public ResponseEntity<?> finByAll() throws Exception{
		// http://localhost:9090/api/product/finByAll
			List<Product> products = productService.findAll();
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
	
	
	}
	
	
	//APPL45
	@RequestMapping("/finById/{proId}")
	public ResponseEntity<?> finById(@PathVariable("proId") String proId)throws Exception{
		// http://localhost:9090/api/customer/finById/APPL45
	
			Optional<Product> productOptional = productService.findById(proId);
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
	
	@DeleteMapping("/delete/{proId}")
	public ResponseEntity<?> delete(@PathVariable("proId") String proId)throws Exception{
		// http://localhost:9090/api/customer/delete/{}
		
		productService.deleteById(proId);
		
		return ResponseEntity.ok().build();
		/*		//El DTO son los datos que necesitamos 
		//el la clase es la que contiene todos los datos incluyendo datos que el usuario no necesita ver Por ejemplo ID
		Optional<Customer> customerOptional = customerService.findById(email);
		if (customerOptional.isPresent() == false) {
			throw new Exception("El customer Con ID:"+email+ " No Existe!;Error");
		}

		Customer customer = customerOptional.get();
		customerService.delete(customer);
		}*/
	}


		
	
	
}
