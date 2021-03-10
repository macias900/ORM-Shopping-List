package pl.air.sl.menu;

import pl.air.sl.dao.StockDAO;
import pl.air.sl.model.Stock;

public class StockDelete extends BaseMenuItem{

	@Override
	public String getCaption() {
		
		return "Usuń produkt ze stanu";
	}

	@Override
	public int execute() {
		
		Long id = readSrv.readLong("Id z listy posiadanych produktów do usunięcia");
		if(id != null) {
			beginTransaction();
			StockDAO dao = getStockDAO();
			Stock one = dao.findById(id);
			if( one != null) {
				System.out.println("Dane do usunięcia : ");
				printSrv.printStock(one);
				String response = readSrv.readString("Potwierdz chęć usunięcia (t/n)");
				
				if(response != null && response.equals("t")) {
					
					dao.delete(one);
					System.out.println("Dane zostały usunięte");
				} else {
					System.out.println("Operacja usunięcia danych została anulowana");
				}
				
			} else {
				System.out.println("Na stanie nie ma obiektu o podanym id.");
			}
			commitTransaction();
		}
		
		
		return STATUS_OK;
	}

}
