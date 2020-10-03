package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Validator;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.repository.ProductRepository;


@Service
@Scope("singleton")
public class ProductServiceimpl implements ProductService  {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	Validator validator;
	
	

	@Override
	public void validate(Product entity) throws Exception {
		/*
	private String proId; private String detail;
	 private String enable; private String image; private String name; private Integer price;
	*/
		if(entity ==null) {
			throw new Exception("El Producto Es nullo");
			
		} 	
		if(entity.getDetail()==null || entity.getDetail().isBlank()==true) {
			throw new Exception("Detalles Del producto son obligatorios ");
			
		}
		
		if(entity.getImage()==null || entity.getImage().isBlank()==true) {
		throw new Exception("Necesita La imagen de referencia  ");
		
		}
		
		if(entity.getName()==null  || entity.getName() .isBlank()==true) {
			throw new Exception("La direccion es obligatoria ");
			
		}
		if(entity.getPrice()==null ){//|| entity.getPrice().isBlank()==true) {
			throw new Exception("El precio del producto es obligatorio ");
			
		}
		
		
		Set<ConstraintViolation<Product>> constraintValidator=validator.validate(entity);
		if(constraintValidator.isEmpty()==false) {
			//El .isEmpty  es falso Si el Entity No esta vacio
			throw new ConstraintViolationException(constraintValidator);
			
		}
		
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return  productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Product> findById(String id) throws Exception {
		
		return productRepository.findById(id);
	}
	

	@Override
	@Transactional(readOnly = true)
	public Long count() {

		return productRepository.count();
	}
	
	
	@Override 						
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public Product save(Product entity) throws Exception {
		validate(entity);

		if(productRepository.existsById(entity.getProId()) ) {
			throw new Exception("El Producto con id "+entity.getProId() +" Ya existe");
		}
		
		return productRepository.save(entity);  
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public Product update(Product entity) throws Exception {
		validate(entity);
		if(productRepository.existsById(entity.getProId())==false ) {
			throw new Exception("El Prodcuto con  id "+entity.getProId() +" No existe");
		}
		return productRepository.save(entity);
	
	
	
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public void delete(Product entity) throws Exception {
		if (entity==null) {
			throw new Exception("El Producto es nulo");
		}
		if(entity.getProId()==null || entity.getProId().isBlank()==true) {
			throw new Exception("El identificador es obligatoria ");
			
		}
		if(productRepository.existsById(entity.getProId())==false ) {
			throw new Exception("El producto con el id "+entity.getProId() +" No existe (No se puede eliminar)");
		}
		
		productRepository.findById(entity.getProId()).ifPresent(product->{
			if(product.getShoppingProducts() !=null && product.getShoppingProducts().isEmpty()==false ) {
				//isEmpty()-> verifica que esta vacio
				throw new RuntimeException("El producto con id "+ entity.getProId()+" Tiene ShoppingCarts, No se puede borrar");
				}
			});
		productRepository.deleteById(entity.getProId());
		
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public void deleteById(String id) throws Exception {
		if(id==null || id.isBlank()==true) {
			throw new Exception("El Identificador es obligatoria");
		}
	
	if(productRepository.existsById(id)) {
		delete(productRepository.findById(id).get());
	}
	
		
	}

	

	
}
