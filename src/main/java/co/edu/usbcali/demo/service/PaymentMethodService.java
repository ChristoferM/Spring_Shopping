package co.edu.usbcali.demo.service;

import java.util.List;
import co.edu.usbcali.demo.domain.PaymentMethod;

public interface PaymentMethodService extends GenericService<PaymentMethod,Integer >{
	
	public void switchEnable(Integer payId) throws Exception ;
	
	public void switchDisable(Integer payId) throws Exception ;
	
	public List<PaymentMethod> finByAllEnable() throws Exception;
	
	public List<PaymentMethod> finByAll_1() throws Exception;
	
	public void PayCart(Integer payId, Integer carId) throws Exception;
	
}
