package pl.air.sl.dao;

import java.util.List;

import pl.air.sl.model.*;

public interface QuantityOfItemDAO {

	// create
	Long save(QuantityOfItem object);

	// read
	QuantityOfItem findById(Long id);

	List<QuantityOfItem> findAll();
	List<QuantityOfItem> findAllByShoppingList(ShoppingList one);
	List<QuantityOfItem> findAllByShoppingItem(ShoppingItem one);

	// update
	void update(QuantityOfItem object);

	// delete
	void delete(QuantityOfItem object);
	
	void deleteByShoppingList(ShoppingList one);
	
	void deleteByShoppingItem(ShoppingItem one);
}
