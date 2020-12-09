package co.edu.usbcali.demo.service;

import co.edu.usbcali.demo.domain.Customer;


public interface CustomerService extends GenericService<Customer,String > {
	 
public void switchEnable(String email) throws Exception ;
	
	public void switchDisable(String email) throws Exception ;

}
