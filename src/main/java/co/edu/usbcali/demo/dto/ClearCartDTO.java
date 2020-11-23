package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotNull;

public class ClearCartDTO {
	
	@NotNull
	private Integer carId;
	
	
	public ClearCartDTO() {
		super();
	}
	

	public ClearCartDTO(@NotNull Integer carId) {
		super();
		this.carId = carId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	
}
