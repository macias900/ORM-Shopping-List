package pl.air.sl.menu;

import java.util.List;

import pl.air.sl.dao.MeasurmentTypeDAO;
import pl.air.sl.dao.QuantityOfItemDAO;
import pl.air.sl.dao.RecipieDAO;
import pl.air.sl.dao.ShoppingItemDAO;
import pl.air.sl.dao.ShoppingListDAO;
import pl.air.sl.dao.StockDAO;
import pl.air.sl.dao.hibernate.MeasurmentTypeDAOImpl;
import pl.air.sl.dao.hibernate.QuantityOfItemDAOImpl;
import pl.air.sl.dao.hibernate.RecipieDAOImpl;
import pl.air.sl.dao.hibernate.ShoppingItemDAOImpl;
import pl.air.sl.dao.hibernate.ShoppingListDAOImpl;
import pl.air.sl.dao.hibernate.StockDAOImpl;
import pl.air.sl.service.HibernateService;
import pl.air.sl.service.PrintService;
import pl.air.sl.service.PrintServiceConsole;
import pl.air.sl.service.ReadService;
import pl.air.sl.service.ReadServiceConsole;



public abstract class BaseMenuItem implements MenuItem {
	
	protected static ReadService readSrv = new ReadServiceConsole();
	protected static PrintService printSrv = new PrintServiceConsole();
	
	
	//transaction
	protected void beginTransaction() {
		HibernateService.beginTransaction();
	}
	
	protected void commitTransaction() {
		HibernateService.commitTransaction();
	}
	
	protected void rollbackTransaction() {
		HibernateService.rollbackTransaction();
	}
	
	//validation
	protected <T> boolean validate(T object) {
		List<String> errorList = HibernateService.validate(object);
		
		if(errorList == null) return true;
		
		System.out.println("Niepoprawne dane - lista błędów:");
		for(String error : errorList) {
			System.out.println(" - "+ error);
		}
		return false;
	}
	
	//dao
	protected MeasurmentTypeDAO getMeasurmentTypeDAO() {
		return new MeasurmentTypeDAOImpl();
	}
	
	protected ShoppingItemDAO getShoppingItemDAO() {
		return new ShoppingItemDAOImpl();
	}
	
	protected ShoppingListDAO getShoppingListDAO() {
		return new ShoppingListDAOImpl();
	}
	
	protected StockDAO getStockDAO() {
		return new StockDAOImpl();
	}
	
	protected QuantityOfItemDAO getQuantityOfItemDAO() {
		return new QuantityOfItemDAOImpl();
	}
	
	protected RecipieDAO getRecipieDAO() {
		return new RecipieDAOImpl();
	}
}
