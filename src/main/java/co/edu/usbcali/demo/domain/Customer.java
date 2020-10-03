package co.edu.usbcali.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//--
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Entity
@Table(name = "customer", schema = "public")
public class Customer implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Email
	@Size( min=3 , max=255)
	private String email;
	
	@NotBlank
	@Size( min=3 , max=255)
	@NotEmpty //-> no sea enblanco
	private String address;
	
	@NotBlank
	@Size( min=1 , max=1)
	@NotEmpty //-> no sea enblanco
	private String enable;
	
	@NotBlank
	@Size( min=3 , max=255)
	@NotEmpty //-> no sea enblanco
	private String name;
	
	@NotBlank
	@Size( min=3 , max=255)
	@NotEmpty //-> no sea enblanco
	private String phone;
	
	@NotBlank
	@Size( max=255)
	@NotEmpty //-> no sea enblanco
	private String token;
	
	
	private List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>(0);

	public Customer() {
	}

	public Customer(String email, String address, String enable, String name, String phone,
			List<ShoppingCart> shoppingCarts, String token) {
		this.email = email;
		this.address = address;
		this.enable = enable;
		this.name = name;
		this.phone = phone;
		this.token = token;
		this.shoppingCarts = shoppingCarts;
	}

	@Id
	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return this.email;
	}
	
	@Column(name = "address", nullable = false)
	public String getAddress() {
		return this.address;
	}
	
	@Column(name = "enable", nullable = false)
	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}
	
	@Column(name = "phone", nullable = false)
	public String getPhone() {
		return this.phone;
	}

	@Column(name = "token", nullable = false)
	public String getToken() {
		return this.token;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<ShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}
	
	

	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEnable() {
		return this.enable;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	
	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void setToken(String token) {
		this.token = token;
	}

	
	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}
}

