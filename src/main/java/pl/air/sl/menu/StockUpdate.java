package pl.air.sl.menu;

import pl.air.sl.dao.StockDAO;
import pl.air.sl.model.Stock;

public class StockUpdate extends BaseMenuItem{

	@Override
	public String getCaption() {
		
		return "Zaktualizuj stan";
	}

	@Override
	public int execute() {
		
		Long id = readSrv.readLong("Podaj ID ze stanu, który chcesz zaktualizować");
		
		if(id != null) {
			
			beginTransaction();
			
			StockDAO dao = getStockDAO();
			
			Stock item = dao.findById(id);
			
			if(item != null) {
			
			printSrv.printStock(item);
			
			Integer qnt = readSrv.readInteger("Podaj ilość, która ma być przypisana do wybranego produktu");
			
			if(qnt != null) {
				
				item.setQuantity(qnt);
				
				dao.update(item);
				System.out.println("Zaktualizowan stan");
			}
			
			
			} else {
				System.out.println("Nie znaleziono produktu o podanym ID");
			}
			
			commitTransaction();
		}
		
		
		else {
			System.out.println("Nie znaleziono produktu o podanym ID");
		}
		
		
		return STATUS_OK;
	}

}
