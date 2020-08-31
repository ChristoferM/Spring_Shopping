package co.edu.usbcali.demo.domain;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



//javax.persistence -> para Entity (clase que necesitamos para el entity)
@Entity
@Table(name ="product",schema="public")
public class Product {
	
	/*
	 * En java no se puede nombrar con rayaalpiso o guion bajo, pero en este caso, necesitamos esa referencia
	 * ya que la base de datos tiene nombrado el atributo como "pro_id"
	 * Entonces usamos el decorador @column y luego le decimos como se llamaría el atributo en la BD
	 * 
	 * */
	@Id //Llave primaria
	@Column(name= "pro_id") //ayuda a reocnocer el nombre en base de datos (en java no se debe nombrar las var con -> _ (Rayabaja)<-
	private String proId;
	
	@Column(name = "name")
	private String Name;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "detail")
	private String detail;

	@Column(name = "image")
	private String imge;

	@Column(name ="enable")
	private String enable;
	
	
	public Product() {
		super();
	}

	
	public Product(String proId, String name, Integer price, String detail, String imge, String enable) {
		super();
		this.proId = proId;
		this.Name = name;
		this.price = price;
		this.detail = detail;
		this.imge = imge;
		this.enable = enable;
	}




	public String getProId() {
		return proId;
	}


=======
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Entity
@Table(name = "product", schema = "public")
public class Product implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String proId;
	
	private String detail;
	
	private String enable;
	
	private String image;
	
	private String name;
	
	private Integer price;
	
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

>>>>>>> fb4dcc3... 30Agosto
	public void setProId(String proId) {
		this.proId = proId;
	}

<<<<<<< HEAD

	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public String getImge() {
		return imge;
	}


	public void setImge(String imge) {
		this.imge = imge;
	}


	public String getEnable() {
		return enable;
	}


	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	
	
}


=======
	@Column(name = "detail", nullable = false)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "enable", nullable = false)
	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Column(name = "image", nullable = false)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price", nullable = false)
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public List<ShoppingProduct> getShoppingProducts() {
		return this.shoppingProducts;
	}

	public void setShoppingProducts(List<ShoppingProduct> shoppingProducts) {
		this.shoppingProducts = shoppingProducts;
	}
}
>>>>>>> fb4dcc3... 30Agosto
