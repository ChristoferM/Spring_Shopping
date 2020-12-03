package co.edu.usbcali.demo.service;

import co.edu.usbcali.demo.domain.PaymentMethod;

public interface PaymentMethodService extends GenericService<PaymentMethod,Integer >{
	
	public void switchEnable(Integer payId) throws Exception ;
	
	public void switchDisable(Integer payId) throws Exception ;

}
