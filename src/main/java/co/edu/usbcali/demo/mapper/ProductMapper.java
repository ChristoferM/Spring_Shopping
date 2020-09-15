package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.demo.dto.ProductDTO;
import co.edu.usbcali.demo.domain.Product;

@Mapper
public interface ProductMapper {
	
	
public ProductDTO toProductDTO(Product product) ;
	
	public Product toCustomer(ProductDTO productDTO);

	public List<ProductDTO> toProductDTO(List<Product> products);
	
	public List<Product> toProduct(List<ProductDTO> productDTOs);

}
