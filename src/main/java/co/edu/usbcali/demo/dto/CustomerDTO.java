package co.edu.usbcali.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CustomerDTO {
	
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
	
	public CustomerDTO() {
		super();
	}

	public CustomerDTO(String email, String address, String enable, String name, String phone, String token) {
		super();
		this.email = email;
		this.address = address;
		this.enable = enable;
		this.name = name;
		this.phone = phone;
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
