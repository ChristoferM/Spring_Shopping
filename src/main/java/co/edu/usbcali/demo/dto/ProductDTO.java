package co.edu.usbcali.demo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDTO {

	@NotBlank
	@Size( min=3 , max=255)
	private String proId;
	
	@NotBlank
	@Size( min=3 , max=255)
	@NotEmpty //-> no sea enblanco
	private String name;
	
	
	@NotNull
	@Min(1)
	private Integer price;
	
	@NotBlank
	@Size( min=3 , max=255)
	@NotEmpty //-> no sea enblanco
	private String detail;
	
	@NotBlank
	@Size( min=1 , max=1)
	@NotEmpty //-> no sea enblanco
	private String enable;
	
	@NotBlank
	@Size( min=5 , max=255)
	@NotEmpty //-> no sea enblanco
	private String image;

	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(String proId, String detail, String enable, String image, String name, Integer price) {
		super();
		this.proId = proId;
		this.detail = detail;
		this.enable = enable;
		this.image = image;
		this.name = name;
		this.price = price;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	
}
