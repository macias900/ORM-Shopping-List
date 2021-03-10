package pl.air.sl.menu;

import java.util.List;

import pl.air.sl.dao.*;

import pl.air.sl.model.*;

public class ShoppingListOne extends BaseMenuItem {

	@Override
	public String getCaption() {
		return "Wyświetl listę zakupów o podanym ID";
	}

	@Override
	public int execute() {

		Long id = readSrv.readLong("ID");
		if(id != null) {
			beginTransaction();
			QuantityOfItemDAO dao = getQuantityOfItemDAO();
			ShoppingListDAO sldao = getShoppingListDAO();
			ShoppingList one = sldao.findById(id);
			List<QuantityOfItem> list = dao.findAllByShoppingList(one);
			
			
			if(list != null) {
				System.out.println("Lista zakupów o ID = "+ id);
				System.out.format("%-20s | %s \n " , "Nazwa produktu", "Ilość");
				System.out.println("==========================================");
				printSrv.printQuantityOfItems(list);
			}
			else {
				System.out.println("Brak listy zakupów o podanym id.");
			}
			commitTransaction();
		}
		return STATUS_OK;
	}

}
