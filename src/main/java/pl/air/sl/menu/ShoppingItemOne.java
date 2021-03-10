package pl.air.sl.menu;

import pl.air.sl.dao.*;

import pl.air.sl.model.*;

public class ShoppingItemOne extends BaseMenuItem {

	@Override
	public String getCaption() {
		return "Wyświetl produkt o podanym ID";
	}

	@Override
	public int execute() {

		Long id = readSrv.readLong("id");
		if(id != null) {
			beginTransaction();
			ShoppingItemDAO dao = getShoppingItemDAO();
			ShoppingItem one = dao.findById(id);
			if(one != null) {
				
				printSrv.printItem(one);
			}
			else {
				System.out.println("Brak produktu o podanym id.");
			}
			commitTransaction();
		}
		return STATUS_OK;
	}

}
