package pl.air.sl.menu;

import java.util.ArrayList;
import java.util.List;


import pl.air.sl.dao.ShoppingItemDAO;
import pl.air.sl.dao.StockDAO;

import pl.air.sl.model.ShoppingItem;
import pl.air.sl.model.Stock;

public class StockNew  extends BaseMenuItem{

	@Override
	public String getCaption() {
		
		return "Dodaj produkt do stanu";
	}

	@Override
	public int execute() {
		
		
		Long id = readSrv.readLong("ID produktu");
		Integer quantity = readSrv.readInteger("Ilość");
		beginTransaction();
		ShoppingItemDAO dao = getShoppingItemDAO();
		ShoppingItem shopItem = dao.findById(id);
		
		if(shopItem == null) {
			
			System.out.println("Nie ma produktu o podanym ID");
			rollbackTransaction();
			return STATUS_OK;
		}
		
		
		StockDAO stdao = getStockDAO();
		List<Stock> list = stdao.findAll();
		List<ShoppingItem> silist = new ArrayList<>();
		
		for(Stock one : list) {
			silist.add(one.getShopItem());
		}
		
		if( silist.contains(shopItem)) {
			
			System.out.println("Taki produkt o poadnym ID = " + shopItem.getId() + " już istnieje w posiadanym stanie, aby zmienić jego ilość użyj funkji aktualizacji stanu");
			rollbackTransaction();
			return STATUS_OK;
			
		}
		
	
		
		Stock one = new Stock(shopItem, quantity);

		
		
		
		boolean isValid = validate(one);
		if(isValid) {
			//beginTransaction();
			
			Long ida = stdao.save(one);
			one = stdao.findById(ida);
			if ( one != null) {
				
				System.out.println("Dodano produkt : " + one.getShopItem().getName() + "\n W ilości: " + quantity);
			}
			else {
				System.out.println("Produkt nie został dodany do stanu");
			}
			commitTransaction();
			
		} else {
			System.out.println("Produkt nie został dodany do stanu");
		}
		
		return STATUS_OK;
	}

}
