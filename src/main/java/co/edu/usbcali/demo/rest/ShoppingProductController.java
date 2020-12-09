package co.edu.usbcali.demo.rest;

import co.edu.usbcali.demo.domain.*;
import co.edu.usbcali.demo.dto.ProductDTO;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;
import co.edu.usbcali.demo.mapper.ShoppingProductMapper;
import co.edu.usbcali.demo.mapper.ShoppingProductMapperImpl;
import co.edu.usbcali.demo.service.CartService;
import co.edu.usbcali.demo.service.ShoppingProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/shoppingProduct")
@CrossOrigin(origins = "*")

public class ShoppingProductController {
	@Autowired
	private ShoppingProductService shoppingProductService;
	@Autowired
	private ShoppingProductMapper shoppingProductMapper;
	
	
	@Autowired
	CartService cartService;

	@RequestMapping("/findById/{shprId}")
	public ResponseEntity<?> findById(@PathVariable("shprId") Integer shprId) throws Exception {

		List<ShoppingProduct> productList = shoppingProductService.findShoppingProductByShoppingCart(shprId);
		if (productList.isEmpty() == true) {
			return ResponseEntity.ok().body("error Not Found");
		}
		
		
		//ShoppingProductDTO shoppingProductDTO=  shoppingProductMapper.listShoppingProductToListShoppingProductDTO(productList);
		
		
		return ResponseEntity.ok().body( shoppingProductMapper.listShoppingProductToListShoppingProductDTO(productList));
		/*
		 * 	eturn ResponseEntity.ok().body(productDTO);
			Optional<Product> productOptional = productService.findById(proId);
			if (productOptional.isPresent() == false) {
				return ResponseEntity.ok().body("Customer Not Found");
			}

			Product product = productOptional.get();
			ProductDTO productDTO = productMapper.toProductDTO(product);

			

			return ResponseEntity.ok().body(productDTO);*/
	}
	
	@RequestMapping("/findProductByShpId/{shprId}")
	public ResponseEntity<?> findProductByShpId(@PathVariable("shprId") String email) throws Exception {

		return ResponseEntity.ok().body(
				shoppingProductMapper.listShoppingProductToListShoppingProductDTO(shoppingProductService.findProductByShpId(email) ) );
	}


	@RequestMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception {

		return ResponseEntity.ok().body(
				shoppingProductMapper.listShoppingProductToListShoppingProductDTO(shoppingProductService.findAll()));
	}

	@RequestMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody ShoppingProductDTO shoppingProductDTO) throws Exception {

		ShoppingProduct shoppingProduct = shoppingProductMapper.shoppingProductDTOToShoppingProduct(shoppingProductDTO);
		shoppingProduct = shoppingProductService.save(shoppingProduct);

		return ResponseEntity.ok().body(shoppingProductMapper.shoppingProductToShoppingProductDTO(shoppingProduct));
	}
	
	@RequestMapping("/addShp/{carId}/{proId}")
	public ResponseEntity<?> addProductShoppingProduct(@PathVariable("carId") Integer carId,@PathVariable("proId") String proId) throws Exception {

		return ResponseEntity.ok().body(cartService.addProduct(carId, proId, 1) );
	}
	//removeProduct
	@RequestMapping("/deleteP/{carId}/{pro_id}")
	public ResponseEntity<?> deleteP(@PathVariable("carId")Integer carId,@PathVariable("pro_id") String pro_id) throws Exception {

			//shoppingProductService.deleteShoppingProduct(pro_id, carId);
			cartService.removeProduct(carId, pro_id);

			return ResponseEntity.ok().build();
		}
	@RequestMapping("/createCart/{email}")
	public ResponseEntity<?> createCart(@PathVariable("email") String email) throws Exception {
		return ResponseEntity.ok().body(cartService.createCart(email));
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody ShoppingProductDTO shoppingProductDTO) throws Exception {

		ShoppingProduct shoppingProduct = shoppingProductMapper.shoppingProductDTOToShoppingProduct(shoppingProductDTO);
		shoppingProduct = shoppingProductService.update(shoppingProduct);

		return ResponseEntity.ok().body(shoppingProductMapper.shoppingProductToShoppingProductDTO(shoppingProduct));
	}

	
	@DeleteMapping("/delete/{shprId}")
	public ResponseEntity<?> delete(@PathVariable("shprId") Integer shprId) throws Exception {

		shoppingProductService.deleteById(shprId);

		return ResponseEntity.ok().build();
	}
	
	
	@RequestMapping("/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(shoppingProductService.count());
	}
}
