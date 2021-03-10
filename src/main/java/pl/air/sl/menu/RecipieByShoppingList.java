package pl.air.sl.menu;

import java.util.List;

import pl.air.sl.dao.RecipieDAO;
import pl.air.sl.dao.ShoppingListDAO;
import pl.air.sl.model.Recipie;
import pl.air.sl.model.ShoppingList;

public class RecipieByShoppingList extends BaseMenuItem {

	@Override
	public String getCaption() {
		
		return "Wyświetl przepisy według ID listy zakupów";
	}

	@Override
	public int execute() {
		
		Long id = readSrv.readLong("Id listy zakupów : ");
		
		if(id != null) {
			
			beginTransaction();
			RecipieDAO dao = getRecipieDAO();
			ShoppingListDAO sldao = getShoppingListDAO();
			
			ShoppingList one = sldao.findById(id);
			if(one != null) {
				List<Recipie> list = dao.findAllByShoppingList(one);
				printSrv.printRecipieList(list);
			} else { 
				System.out.println("Brak listy zakupów o podanym ID");
			}
			commitTransaction();
			
		}
		
		return STATUS_OK;
	}

}
