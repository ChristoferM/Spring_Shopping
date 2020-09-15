package co.edu.usbcali.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.edu.usbcali.demo.dto.CustomerDTO;
import co.edu.usbcali.demo.mapper.CustomerMapper;
import co.edu.usbcali.demo.repository.CustomerRepository;
import co.edu.usbcali.demo.domain.Customer;

@RestController // Servicio
@RequestMapping("/api/customer") // Forma de llamar datos
public class CustomerController {

	private final static Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerMapper customerMapper;

	@RequestMapping("/save")
	public ResponseEntity<?> save(@RequestBody CustomerDTO customerDTO){
		//El DTO son los datos que necesitamos 
		//el la clase es la que contiene todos los datos incluyendo datos que el usuario no necesita ver Por ejemplo ID
		try {
			
			Customer customer=customerMapper.toCustomer(customerDTO);
			
			customer=customerRepository.save(customer);
			
			customerDTO=customerMapper.toCustomerDTO(customer);
			
			return ResponseEntity.ok().body(customerDTO); 
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return ResponseEntity.ok().body("Customer  Not Found");
		}
		
	}
	
	
	@RequestMapping("/finByAll")
	public ResponseEntity<?> finByAll() {
		// http://localhost:9090/api/customer/finByAll
		try {

			List<Customer> customers = customerRepository.findAll();
			List<CustomerDTO> customerDTOs = customerMapper.toCustomerDTO(customers);
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
			return ResponseEntity.ok().body(customerDTOs);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return ResponseEntity.ok().body("Customer  Not Found");
		}

	}

	@RequestMapping("/finById/{email}")
	public ResponseEntity<?> finById(@PathVariable("email") String email) {
		// http://localhost:9090/api/customer/finById/fgiraudot0@economist.com
		try {
			Optional<Customer> customerOptional = customerRepository.findById(email);
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
		catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
}
