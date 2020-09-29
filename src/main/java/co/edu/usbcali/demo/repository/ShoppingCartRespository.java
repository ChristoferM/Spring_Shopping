package co.edu.usbcali.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.demo.domain.ShoppingCart;

public interface ShoppingCartRespository extends JpaRepository<ShoppingCart,Integer>{
	

}
