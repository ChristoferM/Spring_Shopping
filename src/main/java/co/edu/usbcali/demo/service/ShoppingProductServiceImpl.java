package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.repository.ShoppingProductRepository;


@Scope("singleton")
@Service
public class ShoppingProductServiceImpl implements ShoppingProductService {
	
	private final static Logger log = LoggerFactory.getLogger(ShoppingProductServiceImpl.class);
	
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
    @Autowired
    private ShoppingProductRepository shoppingProductRepository;
    @Autowired
    private Validator validator;
    
    @Autowired
	ShoppingProductService shoppingProductService;

    @Override
    public void validate(ShoppingProduct shoppingProduct)
        throws ConstraintViolationException {
        Set<ConstraintViolation<ShoppingProduct>> constraintViolations = validator.validate(shoppingProduct);

        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return shoppingProductRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShoppingProduct> findAll() {
        log.debug("finding all ShoppingProduct instances");

        return shoppingProductRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ShoppingProduct save(ShoppingProduct entity)
        throws Exception {
        log.debug("saving ShoppingProduct instance");

        if (entity == null) {
            throw new Exception("El ShoppingProduct es nulo");
        }

        validate(entity);

        if (shoppingProductRepository.existsById(entity.getShprId())) {
        	throw new Exception("El ShoppingProduct con id:"+entity.getShprId()+" Ya existe");
        }

        return shoppingProductRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(ShoppingProduct entity) throws Exception {
        log.debug("deleting ShoppingProduct instance");

        if (entity == null) {
        	throw new Exception("El ShoppingProduct es nulo");
        }

        if (entity.getShprId() == null) {
        	throw new Exception("El ShoppingProduct id es nulo");
        }

        if (shoppingProductRepository.existsById(entity.getShprId()) == false) {
        	throw new Exception("El ShoppingProduct con id:"+entity.getShprId()+" No existe");
        }

        shoppingProductRepository.deleteById(entity.getShprId());
        log.debug("delete ShoppingProduct successful");
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteById(Integer id) throws Exception {
        log.debug("deleting ShoppingProduct instance");

        if (id == null) {
        	throw new Exception("El ShoppingProduct id es nulo");
        }

        if (shoppingProductRepository.existsById(id)) {
            delete(shoppingProductRepository.findById(id).get());
        }
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteShoppingProduct(String pro_id,Integer carId )  throws Exception{
    	log.info("\n ***************** \n");
        log.info("deleting ShoppingProduct BY PRO_ID AND CAR_ID");

        if (pro_id == null || carId == null) {
        	throw new Exception("El ShoppingProduct id es nulo");
        }
        log.info("**************deleting ShoppingProduct BY PRO_ID AND CAR_ID");
        shoppingProductRepository.deleteShoppingProduct(pro_id, carId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ShoppingProduct update(ShoppingProduct entity)
        throws Exception {
        log.debug("updating ShoppingProduct instance");

        if (entity == null) {
        	throw new Exception("El ShoppingProduct es nulo");
        }

        validate(entity);

        if (shoppingProductRepository.existsById(entity.getShprId()) == false) {
        	throw new Exception("El ShoppingProduct con id:"+entity.getShprId()+" No existe");
        }

        return shoppingProductRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShoppingProduct> findById(Integer shprId)
        throws Exception {
        log.debug("getting ShoppingProduct instance");

        return shoppingProductRepository.findById(shprId);
    }

	@Override
	@Transactional(readOnly = true)
	public Long totalShoppingProductByShoppingCart(Integer carId) {	
		return shoppingProductRepository.totalShoppingProductByShoppingCart(carId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Integer quantityShoppingProductByShoppingCart(Integer carId) {
		return shoppingProductRepository.quantityShoppingProductByShoppingCart(carId);
	}

	@Override
	@Transactional(readOnly = true)
	public ShoppingProduct findByShoppingCartAndProduct(Integer carId, String proId) {
		return shoppingProductRepository.findByShoppingCartAndProduct(carId, proId);
	}
	
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteProductsByShoppingCart(Integer carId) throws Exception{
		 if (carId == null) {
	        throw new Exception("El ShoppingCart id es nulo");
	     }
		 if(shoppingCartService.findById(carId).isPresent()==false) {
			 throw new Exception("El ShoppingCart con id:"+carId+" No existe");
		 }
		 
		 ShoppingCart shoppingCart=shoppingCartService.findById(carId).get();
		 
		 if(shoppingCart.getPaymentMethod()!=null) {
			 throw new Exception("El ShoppingCart con id:"+carId+" ya se encuentra pagado no se pueden eliminar");
		 }
		 
		 shoppingProductRepository.deleteProductsByShoppingCart(carId);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingProduct> findProductByShpId(String email) {
		return shoppingProductRepository.findProductByShpId(email);
		
	}

	@Override
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}

