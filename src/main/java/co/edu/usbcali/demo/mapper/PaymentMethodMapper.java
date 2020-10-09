package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import org.mapstruct.Mapper;


import co.edu.usbcali.demo.dto.PaymentMethodDTO;
import co.edu.usbcali.demo.domain.PaymentMethod;

@Mapper
public interface PaymentMethodMapper {

	public PaymentMethodDTO toMethodDTO(PaymentMethod paymentMethod) ;
	
	public PaymentMethod toMethod(PaymentMethodDTO paymentMethodDTO);

	public List<PaymentMethodDTO> toMethodDTOs(List<PaymentMethod> paymentMethods);
	
	public List<PaymentMethod> toMethods(List<PaymentMethodDTO> paymentMethodDTOs);
 
}
