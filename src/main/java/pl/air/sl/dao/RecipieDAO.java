package pl.air.sl.dao;

import java.util.List;

import pl.air.sl.model.*;

public interface RecipieDAO {

	// create
	Long save(Recipie object);

	// read
	Recipie findById(Long id);

	List<Recipie> findAll();
	
	List<Recipie> findAllByShoppingList(ShoppingList list);

	// update
	void update(Recipie object);

	// delete
	void delete(Recipie object);
	
	void deleteByShoppingList(ShoppingList list);
	
}
