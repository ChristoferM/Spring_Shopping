
package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import co.edu.usbcali.demo.domain.ShoppingProduct;


/*
 * Interface Generica que tiene los metodos minimos para un componente de negocio
 * Guardar
 * buscar todo -> Lista
 * buscar por id -> Registro 
 * borrar -> Por Id
 * actualizar -> registr por Id
 * contar*/
public interface GenericService<T,ID> {
	
	public List<T> findAll();
	//Devuelve una lista del T

    //REaliza delete Solo por Id
    public Optional<T> findById(ID id) throws Exception;
	
	public Long count();
	
    public T save(T entity) throws Exception;
   //Recibe el Save y devuelve un dato del mismo tipo-> Usado con el AutoIncremento

    public T update(T entity) throws Exception;

    public void delete(T entity) throws Exception;
    //REaliza delete con todo el objeto

    public void deleteById(ID id) throws Exception;

    public void validate(T entity) throws Exception;

	

}
