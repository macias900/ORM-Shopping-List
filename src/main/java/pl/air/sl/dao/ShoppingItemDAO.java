package pl.air.sl.dao;

import java.util.List;

import pl.air.sl.model.*;

public interface ShoppingItemDAO {

	// create

	Long save(ShoppingItem object);

	// read
	ShoppingItem findById(Long id);

	List<ShoppingItem> findAll();

	// update
	void update(ShoppingItem object);

	// delete
	void delete(ShoppingItem object);
}
