package co.edu.usbcali.demo.domain;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
=======
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
>>>>>>> fb4dcc3... 30Agosto
import javax.persistence.Table;



@Entity
<<<<<<< HEAD
@Table(name = "customer",schema = "public")
public class Customer {
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "enable")
	private String enable;
	
	
	public Customer() {
		super();
	}
	
	public Customer(String email, String address, String name, String phone, String token, String enable) {
		super();
		this.email = email;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.token = token;
		this.enable = enable;
	}

	public String getEmail() {
		return email;
=======
@Table(name = "customer", schema = "public")
public class Customer implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "enable", nullable = false)
	private String enable;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Column(name = "token", nullable = false)
	private String token;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
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

	
	public String getEmail() {
		return this.email;
>>>>>>> fb4dcc3... 30Agosto
	}

	public void setEmail(String email) {
		this.email = email;
	}

<<<<<<< HEAD
	public String getAddress() {
		return address;
=======
	
	public String getAddress() {
		return this.address;
>>>>>>> fb4dcc3... 30Agosto
	}

	public void setAddress(String address) {
		this.address = address;
	}

<<<<<<< HEAD
	public String getName() {
		return name;
=======
	
	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	
	public String getName() {
		return this.name;
>>>>>>> fb4dcc3... 30Agosto
	}

	public void setName(String name) {
		this.name = name;
	}

<<<<<<< HEAD
	public String getPhone() {
		return phone;
=======
	
	public String getPhone() {
		return this.phone;
>>>>>>> fb4dcc3... 30Agosto
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

<<<<<<< HEAD
	public String getToken() {
		return token;
=======
	
	public String getToken() {
		return this.token;
>>>>>>> fb4dcc3... 30Agosto
	}

	public void setToken(String token) {
		this.token = token;
	}

<<<<<<< HEAD
	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	

=======
	
	public List<ShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}

	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}
>>>>>>> fb4dcc3... 30Agosto
}
