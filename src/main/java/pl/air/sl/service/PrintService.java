package pl.air.sl.service;

import java.util.List;

import pl.air.sl.model.MeasurmentType;
import pl.air.sl.model.QuantityOfItem;
import pl.air.sl.model.Recipie;
import pl.air.sl.model.ShoppingItem;
import pl.air.sl.model.ShoppingList;
import pl.air.sl.model.Stock;

public interface PrintService {
	
	void printItem(ShoppingItem one);
	
	void printItemList(List<ShoppingItem> list);
	
	void printShoppingList(List<QuantityOfItem> one);
	
	void printShoppingLists(List<ShoppingList> list);
	
	void printQuantityOfItems(List<QuantityOfItem> list);
	
	void printStock(Stock one);
		
	void printStockList(List<Stock> list);
	
	void printMeasurmentType(MeasurmentType one);
	
	void printMeasurmentTypes(List<MeasurmentType> list);
	
	void printRecipie(Recipie one);
	
	void printRecipieList(List<Recipie> list);

}
