package pl.air.sl.dao;

import java.util.List;

import pl.air.sl.model.*;

public interface ShoppingListDAO {

	// create

	Long save(ShoppingList object);

	// read
	ShoppingList findById(Long id);

	List<ShoppingList> findAll();
	
	List<ShoppingList> findAllByShoppingItem(ShoppingItem one);

	// update
	void update(ShoppingList object);

	// delete
	void delete(ShoppingList object);
	
	void deleteByShoppingItem(ShoppingItem one);
}
