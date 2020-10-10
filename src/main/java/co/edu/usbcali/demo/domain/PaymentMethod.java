package co.edu.usbcali.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "payment_method", schema = "public")
public class PaymentMethod implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	//@NotBlank
	//@Size( min=3 , max=255)
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

	public PaymentMethod() {
	}

	public PaymentMethod(Integer payId, String enable, String name, List<ShoppingCart> shoppingCarts) {
		this.payId = payId;
		this.enable = enable;
		this.name = name;
		this.shoppingCarts = shoppingCarts;
	}

	@Id
	@Column(name = "pay_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPayId() {
		return this.payId;
	}
	@Column(name = "enable", nullable = false)
	public String getEnable() {
		return this.enable;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentMethod")
	public List<ShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}
	
	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}
}
