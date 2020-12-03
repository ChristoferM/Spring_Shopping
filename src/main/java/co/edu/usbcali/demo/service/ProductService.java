package co.edu.usbcali.demo.service;

import co.edu.usbcali.demo.domain.Product;

public interface ProductService extends GenericService<Product,String >  {

	
	public void switchEnable(String prodId) throws Exception ;
	
	public void switchDisable(String prodId) throws Exception ;
}
