package pl.air.sl.dao;

import java.util.List;

import pl.air.sl.model.*;

public interface StockDAO {

	// create
	Long save(Stock object);

	// read
	Stock findById(Long id);
	List<Stock> findAll();
	Stock findyByShoppingItem(ShoppingItem one);

	// update
	void update(Stock object);

	// delete
	void delete(Stock object);
}
