package co.edu.usbcali.demo.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import co.edu.usbcali.demo.domain.ShoppingCart;

public class PaymentMethodDTO {
	
	
	//@NotBlank
	//@Size(min=3 , max=255)
	private Integer payId;
	
	@NotBlank
	@Size( min=1 , max=1)
	@NotEmpty //-> no sea enblanco
	private String enable;
	
	@NotBlank
	@Size( min=3 , max=255)
	@NotEmpty //-> no sea enblanco
	private String name;
	
	
	private List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>(0);

	public PaymentMethodDTO() {
	}

	public PaymentMethodDTO(Integer payId, String enable, String name, List<ShoppingCart> shoppingCarts) {
		this.payId = payId;
		this.enable = enable;
		this.name = name;
		this.shoppingCarts = shoppingCarts;
	}


	public Integer getPayId() {
		return this.payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	
	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<ShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}

	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}
}
