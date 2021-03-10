package pl.air.sl.menu;

import java.util.List;

import pl.air.sl.dao.ShoppingItemDAO;
import pl.air.sl.dao.ShoppingListDAO;
import pl.air.sl.model.ShoppingItem;
import pl.air.sl.model.ShoppingList;

public class ShoppingListsByShoppingItem extends BaseMenuItem {

	@Override
	public String getCaption() {

		return "Pokaż listy zakupów, w których jest produkt o podanym ID";
		
	}

	@Override
	public int execute() {
		
		Long id = readSrv.readLong("ID produktu");
		
		if(id != null) {
			beginTransaction();
			ShoppingListDAO dao = getShoppingListDAO();
			ShoppingItemDAO sidao = getShoppingItemDAO();
			ShoppingItem item = sidao.findById(id);
			
			if(item != null) {
				List<ShoppingList> list = dao.findAllByShoppingItem(item);
				printSrv.printShoppingLists(list);
			} else {
				System.out.println("Nie ma produktu o podanym id");
			}
			commitTransaction();
		}
		
		return STATUS_OK;
	}

}
