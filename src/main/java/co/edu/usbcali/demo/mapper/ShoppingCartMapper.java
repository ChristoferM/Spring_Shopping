package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.dto.ShoppingCartDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org/
* www.zathuracode.org
*
* Mapper Build with MapStruct https://mapstruct.org/
* MapStruct is a code generator that greatly simplifies the
* implementation of mappings between Java bean type
* based on a convention over configuration approach.
*/
@Mapper
public interface ShoppingCartMapper {
    @Mapping(source = "customer.email", target = "customerEmail")
    @Mapping(source = "paymentMethod.payId", target = "paymentMethodId")
    public ShoppingCartDTO shoppingCartToShoppingCartDTO(
        ShoppingCart shoppingCart);

    @Mapping(source = "customerEmail", target = "customer.email")
    @Mapping(source = "paymentMethodId", target = "paymentMethod.payId")
    public ShoppingCart shoppingCartDTOToShoppingCart(
        ShoppingCartDTO shoppingCartDTO);

    public List<ShoppingCartDTO> listShoppingCartToListShoppingCartDTO(
        List<ShoppingCart> shoppingCarts);

    public List<ShoppingCart> listShoppingCartDTOToListShoppingCart(
        List<ShoppingCartDTO> shoppingCartDTOs);
}
