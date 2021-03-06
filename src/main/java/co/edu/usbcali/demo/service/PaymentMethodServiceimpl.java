package co.edu.usbcali.demo.service;


import java.util.List;
import java.util.Optional;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.repository.PaymentMethodRepository;



@Service
@Scope("singleton")
public class PaymentMethodServiceimpl implements PaymentMethodService{
	
	private final static Logger log=LoggerFactory.getLogger(PaymentMethodServiceimpl.class);

	@Autowired
	PaymentMethodRepository paymentMethodRepository;

	@Autowired
	Validator validator;
	/*
	 * @Transactional(readOnly = true) -> Solo lectura
	 * 
	 * */	
	
	@Override
	//@Transactional(readOnly = false)
	public List<PaymentMethod> finByAll_1() {
		return paymentMethodRepository.finByAll_1();
	}
	
	@Override
	@Transactional
	public List<PaymentMethod> findAll() {
		log.debug("finding all PaymentMethod instances");
		return paymentMethodRepository.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<PaymentMethod> finByAllEnable() throws Exception {
		return paymentMethodRepository.finByAllEnable();
	}
	
	
	
	//@Transactional(readOnly = true)
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void PayCart(Integer payId, Integer carId) throws Exception{
		
		if(paymentMethodRepository.existsById(payId)==false) {
			
			throw new Exception("El paymentMethod no existe");
		}
		/* Optional<PaymentMethod> paymentMethod=paymentMethodRepository.finByAllByIdEnable(payId);
		if (paymentMethod.isEmpty()) {
			throw new Exception("ERROR CON EL PAYMENT mETHOD");
		}
		if(paymentMethod.get().getEnable().equals("N") ) {
			throw new Exception("ERROR CON EL PAYMENT mETHOD");
		}*/
		paymentMethodRepository.PayCart(payId,carId);
		
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Optional<PaymentMethod> findById(Integer id) throws Exception {
 		return paymentMethodRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return paymentMethodRepository.count();
	}
	
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public PaymentMethod save(PaymentMethod entity) throws Exception {
		validate(entity);
		
	
		return paymentMethodRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public PaymentMethod update(PaymentMethod entity) throws Exception {
		validate(entity);
		
		if(paymentMethodRepository.existsById(entity.getPayId())==false) {
			throw new Exception("El paymentMethod  con id:"+entity.getPayId()+" no existe");
		}
		return paymentMethodRepository.save(entity);
	}
	
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void switchEnable(Integer payId) throws Exception {
		if(payId == null) {
			throw new Exception("Erro con el PayMethods");
		}
		 paymentMethodRepository.switchEnable(payId);
	}
	
	
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void switchDisable(Integer payId) throws Exception {
		if(payId == null) {
			throw new Exception("Erro con el PayMethods");
		}
		 paymentMethodRepository.switchDisable(payId);
	}
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(PaymentMethod entity) throws Exception {
	 
		if(entity==null) {
			throw new Exception("El paymentMethod es nulo");
		}
		
		if(entity.getPayId()==null || entity.getPayId()<0) {
			throw new Exception("El payId es obligatorio");
		}
		
		if(paymentMethodRepository.existsById(entity.getPayId())==false) {
			throw new Exception("El paymentMethod con id:"+entity.getPayId()+" no existe. No se puede borrar");
		}
		//Validacion de las referencia de la tabla ShoopingCart
		paymentMethodRepository.findById(entity.getPayId()).ifPresent(paymentMethod->{
			if(paymentMethod.getShoppingCarts()!=null && paymentMethod.getShoppingCarts().isEmpty()==false) {
				//isEmpty()-> verifica que esta vacio 
				// Si es vacio, retorna  true, si tiene datos entonces es falso
				throw new RuntimeException("El Paymen con Id "+ entity.getPayId()+" Tiene ShoppingCarts;Error No se puede borrar");
			}
		});
		
		paymentMethodRepository.deleteById(entity.getPayId());
	}

 	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if(id == null || id<0){
			throw new Exception("El payId es obligatorio");
		}
		
		if(paymentMethodRepository.existsById(id)) {
			delete(paymentMethodRepository.findById(id).get());
		}else {
			throw new Exception("El PaymentMethod con id :" + id + " no existe");
		}

	}

	@Override
	public void validate(PaymentMethod entity) throws Exception {
		if(entity==null) {
			throw new Exception("El PaymentMethod es nulo");
		}
		
		/*if(entity.getPayId()==null ) {
		//	throw new Exception("El PayId es obligatoria");}
		 * */
		
		if(entity.getName()==null || entity.getName().isBlank()==true) {
			throw new Exception("El Name es obligatoria");
		}

		if(entity.getEnable()==null || entity.getEnable().isBlank()==true) {
			throw new Exception("El Enable es obligatoria");
		}
	}
	

	

 
}

