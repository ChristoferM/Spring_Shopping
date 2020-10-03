package co.edu.usbcali.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
//import org.hibernate.validator.constraints.URL;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Entity
@Table(name = "product", schema = "public")
public class Product implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size( min=3 , max=255)
	private String proId;
	
	@NotBlank
	@Size( min=3 , max=255)
	@NotEmpty //-> no sea enblanco
	private String name;
	
	
	@NotNull
	@Min(0)
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
	
	private List<ShoppingProduct> shoppingProducts = new ArrayList<ShoppingProduct>(0);

	public Product() {
	}

	public Product(String proId, String detail, String enable, String image, String name, Integer price,
			List<ShoppingProduct> shoppingProducts) {
		this.proId = proId;
		this.detail = detail;
		this.enable = enable;
		this.image = image;
		this.name = name;
		this.price = price;
		this.shoppingProducts = shoppingProducts;
	}

	@Id
	@Column(name = "pro_id", unique = true, nullable = false)
	public String getProId() {
		return this.proId;
	}

	@Column(name = "detail", nullable = false)
	public String getDetail() {
		return this.detail;
	}

	
	@Column(name = "enable", nullable = false)
	public String getEnable() {
		return this.enable;
	}

	@Column(name = "image", nullable = false)
	public String getImage() {
		return this.image;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	@Column(name = "price", nullable = false)
	public Integer getPrice() {
		return this.price;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public List<ShoppingProduct> getShoppingProducts() {
		return this.shoppingProducts;
	}
	
	

	public void setProId(String proId) {
		this.proId = proId;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public void setShoppingProducts(List<ShoppingProduct> shoppingProducts) {
		this.shoppingProducts = shoppingProducts;
	}
}


