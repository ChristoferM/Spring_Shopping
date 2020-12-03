package co.edu.usbcali.demo.rest;

import co.edu.usbcali.demo.domain.*;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;
import co.edu.usbcali.demo.mapper.ShoppingProductMapper;
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

		ShoppingProduct shoppingProduct = (shoppingProductService.findById(shprId).isPresent() == true)
				? shoppingProductService.findById(shprId).get()
				: null;

		return ResponseEntity.ok().body(shoppingProductMapper.shoppingProductToShoppingProductDTO(shoppingProduct));
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
	
	//removeProduct
	@DeleteMapping("/deleteP/{carId}/{pro_id}")
	public ResponseEntity<?> deleteP(@PathVariable("carId")Integer carId,@PathVariable("pro_id") String pro_id) throws Exception {

		//shoppingProductService.deleteShoppingProduct(pro_id, carId);
		cartService.removeProduct(carId, pro_id);

		return ResponseEntity.ok().build();
	}
	@RequestMapping("/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(shoppingProductService.count());
	}
}
