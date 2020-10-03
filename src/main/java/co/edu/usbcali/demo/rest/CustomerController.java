package co.edu.usbcali.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import co.edu.usbcali.demo.dto.CustomerDTO;
import co.edu.usbcali.demo.mapper.CustomerMapper;
import co.edu.usbcali.demo.repository.CustomerRepository;
import co.edu.usbcali.demo.service.CustomerService;
import co.edu.usbcali.demo.domain.Customer;

@RestController // Servicio 
@RequestMapping("/api/customer") // Forma de llamar datos
public class CustomerController {

	private final static Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService ;

	@Autowired
	CustomerMapper customerMapper;

	@RequestMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {
		//El DTO son los datos que necesitamos 
		//el la clase es la que contiene todos los datos incluyendo datos que el usuario no necesita ver Por ejemplo ID
			Customer customer=customerMapper.toCustomer(customerDTO);
			
			customer=customerService.save(customer);
			
			customerDTO=customerMapper.toCustomerDTO(customer);
			
			return ResponseEntity.ok().body(customerDTO); 
	
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {
		//El DTO son los datos que necesitamos 
		//el la clase es la que contiene todos los datos incluyendo datos que el usuario no necesita ver Por ejemplo ID
			Customer customer=customerMapper.toCustomer(customerDTO);
			
			customer=customerService.update(customer);
			
			customerDTO=customerMapper.toCustomerDTO(customer);
			
			return ResponseEntity.ok().body(customerDTO); 
	
		
	}
	
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<?> delete(@PathVariable("email") String email) throws Exception{
		// http://localhost:9090/api/customer/delete/{}
		
		customerService.deleteById(email);
		
		return ResponseEntity.ok().build();
		/*		//El DTO son los datos que necesitamos 
		//el la clase es la que contiene todos los datos incluyendo datos que el usuario no necesita ver Por ejemplo ID
		Optional<Customer> customerOptional = customerService.findById(email);
		if (customerOptional.isPresent() == false) {
			throw new Exception("El customer Con ID:"+email+ " No Existe!;Error");
		}

		Customer customer = customerOptional.get();
		customerService.delete(customer);
*/

		
	}
	
	@RequestMapping("/finByAll")
	public ResponseEntity<?> finByAll() throws Exception{
		// http://localhost:9090/api/customer/finByAll
	
			List<Customer> customers = customerService.findAll();
			List<CustomerDTO> customerDTOs = customerMapper.toCustomerDTO(customers);
			
			return ResponseEntity.ok().body(customerDTOs);
			/*
			 * List<CustomerDTO> customerDTOs= new ArrayList<>();
			 * 
			 * customers.forEach(customer->{ CustomerDTO customerDTO = new CustomerDTO();
			 * customerDTO.setAddress(customer.getAddress());
			 * customerDTO.setEmail(customer.getEmail());
			 * customerDTO.setEnable(customer.getEnable());
			 * customerDTO.setName(customer.getName());
			 * customerDTO.setPhone(customer.getPhone());
			 * customerDTO.setToken(customer.getToken()); customerDTOs.add(customerDTO);
			 * 
			 * });
			 */	
	}

	@RequestMapping("/finById/{email}")
	public ResponseEntity<?> finById(@PathVariable("email") String email) throws Exception{
		// http://localhost:9090/api/customer/finById/fgiraudot0@economist.com
			Optional<Customer> customerOptional = customerService.findById(email);
			if (customerOptional.isPresent() == false) {
				return ResponseEntity.ok().body("Customer Not Found");
			}

			Customer customer = customerOptional.get();
			CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);

			// Se pasa la información del Entity al DTO
			/*
			 * CustomerDTO customerDTO = new CustomerDTO();
			 * customerDTO.setAddress(customer.getAddress());
			 * customerDTO.setEmail(customer.getEmail());
			 * customerDTO.setEnable(customer.getEnable());
			 * customerDTO.setName(customer.getName());
			 * customerDTO.setPhone(customer.getPhone());
			 * customerDTO.setToken(customer.getToken());
			 */

			return ResponseEntity.ok().body(customerDTO);
	

	}
}
