package co.edu.usbcali.demo.domain;

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


	public void setProId(String proId) {
		this.proId = proId;
	}


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


