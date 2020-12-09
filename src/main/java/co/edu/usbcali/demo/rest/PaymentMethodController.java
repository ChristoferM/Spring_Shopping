package co.edu.usbcali.demo.rest;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.dto.PaymentMethodDTO;
import co.edu.usbcali.demo.mapper.PaymentMethodMapper;
import co.edu.usbcali.demo.service.PaymentMethodService;
//import co.edu.usbcali.demo.repository.ProductRepository;


@RestController // Servicio 
@RequestMapping("/api/PayMethod") // Forma de llamar datos
@CrossOrigin
public class PaymentMethodController {

	private final static Logger log = LoggerFactory.getLogger(PaymentMethodController.class);
	
	@Autowired
	PaymentMethodService paymentMethodService;
	
	@Autowired
	PaymentMethodMapper paymentMethodMapper;
	
	@RequestMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody PaymentMethodDTO paymentMethodDTO) throws Exception {
		//http://localhost:9090/api/PayMethod/save
		//El DTO son los datos que necesitamos 
		//el la clase es la que contiene todos los datos incluyendo datos que el usuario no necesita ver Por ejemplo ID
		PaymentMethod paymentMethod=paymentMethodMapper.toMethod(paymentMethodDTO);
			
		paymentMethod=paymentMethodService.save(paymentMethod);
			
		paymentMethodDTO=paymentMethodMapper.toMethodDTO(paymentMethod);
			
		return ResponseEntity.ok().body(paymentMethodDTO); 
	
		
	}
	@RequestMapping("/Disable/{payId}")
	public ResponseEntity<?> switchDisable(@PathVariable("payId") Integer payId) throws Exception {
		if(payId == null) {
			throw new Exception("Erro con el PayMethods");
		}
		paymentMethodService.switchDisable(payId);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping("/Enable/{payId}")
	public ResponseEntity<?> switchEnable(@PathVariable("payId") Integer payId) throws Exception {
		if(payId == null) {
			throw new Exception("Erro con el PayMethods");
		}
		paymentMethodService.switchEnable(payId);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody PaymentMethodDTO paymentMethodDTO) throws Exception {
		// http://localhost:9090/api/PayMethod/update
		//El DTO son los datos que necesitamos 
		//el la clase es la que contiene todos los datos incluyendo datos que el usuario no necesita ver Por ejemplo ID
			PaymentMethod paymentMethod=paymentMethodMapper.toMethod(paymentMethodDTO);
			
			paymentMethod=paymentMethodService.update(paymentMethod);
			
			paymentMethodDTO=paymentMethodMapper.toMethodDTO(paymentMethod);
			
			return ResponseEntity.ok().body(paymentMethod); 
	
		
	}
	
	@DeleteMapping("/delete/{payId}")
	public ResponseEntity<?> delete(@PathVariable("payId") Integer payId) throws Exception{
		//  http://localhost:9090/api/PayMethod/delete/{}
		
		paymentMethodService.deleteById(payId);
		
		return ResponseEntity.ok().build();
		/*		//El DTO son los datos que necesitamos 
		//el la clase es la que contiene todos los datos incluyendo datos que el usuario no necesita ver Por ejemplo ID
		Optional<PaymentMethod> PaymentMethodOptional = paymentMethodService.findById(email);
		if (PaymentMethodOptional.isPresent() == false) {
			throw new Exception("El PaymentMethod Con ID:"+email+ " No Existe!;Error");
		}

		PaymentMethod PaymentMethod = PaymentMethodOptional.get();
		paymentMethodService.delete(PaymentMethod);
*/

		
	}
	
	@RequestMapping("/findAll")
	public ResponseEntity<?> finByAll() throws Exception{
		//  http://localhost:9090/api/PayMethod/findAll
	
		return ResponseEntity.ok()
				.body(paymentMethodMapper.listPaymentMethodToListPaymentMethodDTO(paymentMethodService.findAll()));
			/*
			 * List<PaymentMethodDTO> PaymentMethodDTOs= new ArrayList<>();
			 * 
			 * PaymentMethods.forEach(PaymentMethod->{ PaymentMethodDTO PaymentMethodDTO = new PaymentMethodDTO();
			 * PaymentMethodDTO.setAddress(PaymentMethod.getAddress());
			 * PaymentMethodDTO.setEmail(PaymentMethod.getEmail());
			 * PaymentMethodDTO.setEnable(PaymentMethod.getEnable());
			 * PaymentMethodDTO.setName(PaymentMethod.getName());
			 * PaymentMethodDTO.setPhone(PaymentMethod.getPhone());
			 * PaymentMethodDTO.setToken(PaymentMethod.getToken()); PaymentMethodDTOs.add(PaymentMethodDTO);
			 * 
			 * });
			 */	
	}
	@RequestMapping("/finByAllEnable")
	public ResponseEntity<?> finByAllEnable() throws Exception{
		//  http://localhost:9090/api/PayMethod/findAll
	
			List<PaymentMethod> PaymentMethods = paymentMethodService.finByAllEnable();
			List<PaymentMethodDTO> PaymentMethodDTOs = paymentMethodMapper.toMethodDTOs(PaymentMethods);
			return ResponseEntity.ok().body(PaymentMethodDTOs);
	}
	
	
	@RequestMapping("/PayCart/{payId}/{carId}")
	public ResponseEntity<?> PayCart (@PathVariable("payId")  Integer payId,@PathVariable("carId") Integer carId) throws Exception{
		//  http://localhost:9090/api/PayMethod/findAll
			log.info("**************************************");
			 paymentMethodService.PayCart(payId,carId);
			 log.info("**************************************");
			return ResponseEntity.ok().build();
	}
	
	
	@RequestMapping("/findById/{payId}")
	public ResponseEntity<?> finById(@PathVariable("payId")  Integer payId) throws Exception{
		// http://localhost:9090/api/PayMethod/findById/1
			Optional<PaymentMethod> PaymentMethodOptional = paymentMethodService.findById(payId);
			if (PaymentMethodOptional.isPresent() == false) {
				return ResponseEntity.ok().body("PaymentMethod Not Found");
			}

			PaymentMethod PaymentMethod = PaymentMethodOptional.get();
			PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toMethodDTO(PaymentMethod);

			// Se pasa la información del Entity al DTO
			/*
			 * PaymentMethodDTO PaymentMethodDTO = new PaymentMethodDTO();
			 * PaymentMethodDTO.setAddress(PaymentMethod.getAddress());
			 * PaymentMethodDTO.setEmail(PaymentMethod.getEmail());
			 * PaymentMethodDTO.setEnable(PaymentMethod.getEnable());
			 * PaymentMethodDTO.setName(PaymentMethod.getName());
			 * PaymentMethodDTO.setPhone(PaymentMethod.getPhone());
			 * PaymentMethodDTO.setToken(PaymentMethod.getToken());
			 */

			return ResponseEntity.ok().body(paymentMethodDTO);
	

	}
	
	
	
	
}
