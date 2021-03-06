package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@Service
@Scope("singleton")
public class CartServiceImpl implements CartService {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ShoppingProductService shoppingProductService;


	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingCart createCart(String email) throws Exception {
		Customer customer=null;
		ShoppingCart shoppingCart=null;
		
		if(email==null || email.isBlank()==true) {
			throw new Exception("El email del cliente es nulo");
		}
		
		Optional<Customer> customerOptional=customerService.findById(email);
		if(customerOptional.isPresent()==false) {
			throw new Exception("No existe un customer con el email: "+email);
		}
		
		customer=customerOptional.get();
		
		if(customer.getEnable()==null || customer.getEnable().equals("N")==true) {
			throw new Exception("El cliente con email: "+email+" esta InHabilitado");
		}
		if(shoppingCartService.findShoppingCart(email).isEmpty()==false ) {
			
			throw new Exception("El cliente con email: "+email+" Tiene un Carrito Habilitado");
		}
		shoppingCart=new ShoppingCart(0, customer, null,0, 0L, "Y", null);
		
		shoppingCart=shoppingCartService.save(shoppingCart);
		
		return shoppingCart;
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public ShoppingProduct addProduct(Integer carId, String proId, Integer quantity) throws Exception {
		
		ShoppingCart shoppingCart=null;
		Product product=null;
		Long totalShoppingProduct=0L;
		Long totalShoppingCart=0L;
		Integer quantityShoppingCart=0;
		
		/*if( carId<=0) {
			throw new Exception("El carId es nulo o menor a cero");
		}*/
		
		if(proId==null || proId.isBlank()==true) {
			throw new Exception("El proId es nulo o menor a esta en blanco");
		}
		
		if(quantity==null || quantity<=0) {
			throw new Exception("El quantity es nulo o menor a cero");
		}
		
		if(shoppingCartService.findById(carId).isPresent()==false) {
			throw new Exception("El shoppingCartService no existe");
		}
		
		shoppingCart=shoppingCartService.findById(carId).get();
		
		if(shoppingCart.getEnable().equals("N")==true) {
			throw new Exception("El shoppingCart esta inhabilitado");
		}
		
		if(productService.findById(proId).isPresent()==false) {
			throw new Exception("El product no existe");
		}
		
		product=productService.findById(proId).get();
		
		if(product.getEnable().equals("N")==true) {
			throw new Exception("El product esta inhabilitado");
		}
		
		
		ShoppingProduct shoppingProduct=shoppingProductService.findByShoppingCartAndProduct(carId, proId);
		if(shoppingProduct==null ) {
			shoppingProduct=new ShoppingProduct();
			shoppingProduct.setProduct(product);
			shoppingProduct.setQuantity(quantity);
			shoppingProduct.setShoppingCart(shoppingCart);
			shoppingProduct.setShprId(0);
			totalShoppingProduct=Long.valueOf(product.getPrice()*quantity);
			shoppingProduct.setTotal(totalShoppingProduct);
			
			shoppingProduct=shoppingProductService.save(shoppingProduct);
		}else {
			shoppingProduct.setQuantity(quantity+shoppingProduct.getQuantity());
			totalShoppingProduct=Long.valueOf(product.getPrice()*(quantity+shoppingProduct.getQuantity()));
			shoppingProduct.setTotal(totalShoppingProduct);
			
			shoppingProduct=shoppingProductService.update(shoppingProduct);
		}
		
		totalShoppingCart=shoppingProductService.totalShoppingProductByShoppingCart(carId);
		quantityShoppingCart=shoppingProductService.quantityShoppingProductByShoppingCart(carId);
		
		shoppingCart.setItems(quantityShoppingCart);
		shoppingCart.setTotal(totalShoppingCart);
		shoppingCartService.update(shoppingCart);
		
		
		
		return shoppingProduct;
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void removeProduct(Integer carId, String proId) throws Exception {
		ShoppingCart shoppingCart=null;
		Product product=null;
		Long totalShoppingProduct=0L;
		Long totalShoppingCart=0L;
		Integer quantityShoppingCart=0;
		
		if(carId==null || carId<=0) {
			throw new Exception("El carId es nulo o menor a cero");
		}
		
		if(proId==null || proId.isBlank()==true) {
			throw new Exception("El proId es nulo o menor a esta en blanco");
		}
		
		if(shoppingCartService.findById(carId).isPresent()==false) {
			throw new Exception("El shoppingCart no existe");
		}
		
		shoppingCart=shoppingCartService.findById(carId).get();
		
		if(shoppingCart.getEnable().equals("N")==true) {
			throw new Exception("El shoppingCart esta inhabilitado");
		}
		
		if(productService.findById(proId).isPresent()==false) {
			throw new Exception("El product no existe");
		}
		
		product=productService.findById(proId).get();
		
		if(product.getEnable().equals("N")==true) {
			throw new Exception("El product esta inhabilitado");
		}
		
		
		ShoppingProduct shoppingProduct=shoppingProductService.findByShoppingCartAndProduct(carId, proId);
		if(shoppingProduct==null) {
			throw new Exception("El shoppingCart no tiene un product con id:"+proId);
		}
		//
		if (shoppingProduct.getQuantity()-1<= 0) {
			//deleteShoppingProduct
			//deleteShoppingProduct(String pro_id, Integer carId)
			deleteShoppingProduct(proId, carId);
			
			shoppingCart.setItems(0);
			shoppingCart.setTotal((long) 0);
			shoppingCartService.update(shoppingCart);
			
		}else {
			shoppingProduct.setQuantity(shoppingProduct.getQuantity()-1);
			
			totalShoppingProduct=Long.valueOf(product.getPrice()*shoppingProduct.getQuantity());
			shoppingProduct.setTotal(totalShoppingProduct);
			
			shoppingProduct=shoppingProductService.update(shoppingProduct);
			
			
			totalShoppingCart=shoppingProductService.totalShoppingProductByShoppingCart(carId);
			quantityShoppingCart=shoppingProductService.quantityShoppingProductByShoppingCart(carId);
			
			shoppingCart.setItems(quantityShoppingCart);
			shoppingCart.setTotal(totalShoppingCart);
			shoppingCartService.update(shoppingCart);


		}
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void clearCart(Integer carId) throws Exception {
		ShoppingCart shoppingCart=null;
		if(carId==null || carId<=0) {
			throw new Exception("El carId es nulo o menor a cero");
		}
		
		if(shoppingCartService.findById(carId).isPresent()==false) {
			throw new Exception("El shoppingCart no existe");
		}
		
		shoppingCart=shoppingCartService.findById(carId).get();
		
		shoppingCart.setItems(0);
		shoppingCart.setTotal(0L);
		shoppingCartService.update(shoppingCart);
		
		shoppingProductService.deleteProductsByShoppingCart(carId);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId) throws Exception {
		return shoppingProductService.findShoppingProductByShoppingCart(carId);
	}
	@Override
	@Transactional(readOnly = true)
	public List<ShoppingProduct> findProductByShpId(String email) throws Exception {
		return shoppingProductService.findProductByShpId(email);
	}
	
	@Override
	public List<ShoppingCart> findShoppingCart(String email) throws Exception {
		// TODO Auto-generated method stub
		//BUSCA LOS CARROS POR EMAIL
		if(email== null) {
			throw new Exception("Corre Null");
			
		}
		List<ShoppingCart> shoppingCart=shoppingCartService.findShoppingCart(email);
		
		return shoppingCart;
	}

	
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteShoppingProduct(String pro_id, Integer carId) throws Exception {
		// (String pro_id,Integer carId )
		
		shoppingProductService.deleteShoppingProduct(pro_id, carId);
	}

	
}
