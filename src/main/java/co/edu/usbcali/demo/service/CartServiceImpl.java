package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private final static Logger log=LoggerFactory.getLogger( CartServiceImpl.class);
	
	
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public ShoppingCart createCart(String email) throws Exception {
		Customer customer=null;
		
		if(email==null || email.isBlank()==true) {
			throw new Exception("El email del cliente es nulo");
		}
		
		Optional<Customer> customerOptional=customerService.findById(email);
		if(customerOptional.isPresent()==false) {
			throw new Exception("No existe un customer con el email: "+email);
		}
		
		customer=customerOptional.get();
		
		if(customer.getEnable()==null || customer.getEnable().equals("N")==true) {
			throw new Exception("El cliente con email: "+email+" no esta habilitado");
		}
		
		ShoppingCart shoppingCart=new ShoppingCart(0, customer, null,0, 0L, "Y", null);
		
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
		
		if(carId==null || carId<=0) {
			throw new Exception("El carId es nulo o menor a cero");
		}
		
		if(proId==null || proId.isBlank()==true) {
			throw new Exception("El proId es nulo o menor a esta en blanco");
		}
		
		if(quantity==null || quantity<=0) {
			throw new Exception("El quantity es nulo o menor a cero");
		}
		
		if(shoppingCartService.findById(carId).isPresent()==false) {
			
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
		
		ShoppingProduct shoppingProduct=new ShoppingProduct();
		shoppingProduct.setProduct(product);
		shoppingProduct.setQuantity(quantity);
		shoppingProduct.setShoppingCart(shoppingCart);
		shoppingProduct.setShprId(0);
		totalShoppingProduct=Long.valueOf(product.getPrice()*quantity);
		shoppingProduct.setTotal(totalShoppingProduct);
		
		shoppingProduct=shoppingProductService.save(shoppingProduct);
		
		totalShoppingCart=shoppingProductService.totalShoppingProductByShoppingCart(carId);
		
		shoppingCart.setTotal(totalShoppingCart);
		shoppingCartService.update(shoppingCart);
		
		
		return shoppingProduct;
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void removeProduct(Integer carId, String proId) throws Exception {

		ShoppingCart shoppingCart=null;
		ShoppingProduct shoppingProduct=new ShoppingProduct();
		
		
		shoppingCart=shoppingCartService.findById(carId).get();
		/*Pasos
		 * 1. Encontrar el ShoppingCart Por medio del carId *
		 * 2. Encontrar el Producto dentro del CarId En elshopping * 
		 * 3. Eliminar el Producto (Con servicio)
		 * */
		
		log.info(shoppingCart.getCarId().toString());
		log.info("Metodo de Pago: ");
		//log.info(shoppingCart.getPaymentMethod().getName());
		log.info("Customer: ");
		log.info(shoppingCart.getCustomer().getName());
		log.info(shoppingCart.getTotal().toString());
		
		if(productService.findById(shoppingCart.getCarId().toString()).isPresent()) {
			throw new Exception(" *ERROR*! El shoppingCart esta No se encontro ! ");
		}
		
		if(shoppingProductService.findProductById(proId).getProduct().getProId().isEmpty()) {
			throw new Exception(" *ERROR*! El shoppingCart esta No se encontro ! ");
		}
		
		if (!shoppingProduct.getProduct().getProId().equals(proId)) {
			throw new Exception("El product no existe ");
		}
		shoppingProduct =shoppingProductService.findProductById(proId);
		log.info(shoppingProduct.getProduct().getName());
		log.info(shoppingProduct.getProduct().getProId());
		//Hasta acá Todo bien, Encuentra cualquier Idpro dentro de un correspondiente IdCar
		
		
		shoppingProductService.delete(shoppingProduct);
		//Elimina bien segun el Id Del producto

	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void clearCart(Integer carId) throws Exception {
		// Borar el todos los shopping product de un ShoopingCart Puntual
		ShoppingCart shoppingCart= new ShoppingCart();
		shoppingCart=shoppingCartService.findById(carId).get();
		
		List<ShoppingProduct> shoppingProducts =shoppingCart.getShoppingProducts();
				
		for (ShoppingProduct tempShoppingProduct : shoppingProducts) {
			shoppingProductService.delete(tempShoppingProduct);
	        }
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId) throws Exception {
		ShoppingCart shoppingCart= new ShoppingCart();
		shoppingCart=shoppingCartService.findById(carId).get();
		
		List<ShoppingProduct> shoppingProducts =shoppingCart.getShoppingProducts();
		

		return shoppingProducts;
	}

}
