package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.demo.dto.CustomerDTO;
import co.edu.usbcali.demo.domain.Customer;

@Mapper
public interface CustomerMapper {
	
	public CustomerDTO toCustomerDTO(Customer Customer) ;
	
	public Customer toCustomer(CustomerDTO customerDTO);

	public List<CustomerDTO> toCustomerDTO(List<Customer> customers);
	
	public List<Customer> toCustomers(List<CustomerDTO> customerDTOs);
	
}
