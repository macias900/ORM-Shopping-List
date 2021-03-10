package pl.air.sl.menu;

import javax.persistence.NoResultException;

import pl.air.sl.dao.ShoppingItemDAO;
import pl.air.sl.dao.StockDAO;
import pl.air.sl.model.ShoppingItem;
import pl.air.sl.model.Stock;

public class StockByShoppingItem extends BaseMenuItem{

	@Override
	public String getCaption() {
		
		return "Wyświetl ze stanu produkt o podanym ID";
	}

	@Override
	public int execute() {
		
		Long id = readSrv.readLong("ID");
		
		if(id != null) {
			
			beginTransaction();
			ShoppingItemDAO sdao = getShoppingItemDAO();
			StockDAO dao = getStockDAO();
			
			ShoppingItem item = sdao.findById(id);
			
			if(item != null) {
				
				try {
					Stock stock = dao.findyByShoppingItem(item);
					printSrv.printStock(stock);
					
				} catch (NoResultException e) {
					System.out.println("Na stanie nie ma produktu o podanym ID");
				}
				
			} else {
				System.out.println("Brak produktu o podanym ID");
			}
			commitTransaction();
			
		}
		
		return STATUS_OK;
	}
	
	

}
