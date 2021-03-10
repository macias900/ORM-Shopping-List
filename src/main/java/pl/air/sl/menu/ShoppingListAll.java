package pl.air.sl.menu;

import java.util.List;


import pl.air.sl.dao.*;

import pl.air.sl.model.*;

public class ShoppingListAll extends BaseMenuItem {

	@Override
	public String getCaption() {
		
		return "Wyświetl dostępne listy zakupów";
	}

	@Override
	public int execute() {
		
		beginTransaction();
		ShoppingListDAO dao = getShoppingListDAO();
		
		List<ShoppingList> list = dao.findAll();
		
		System.out.println("Dostępne listy zakupów");
		printSrv.printShoppingLists(list);
		commitTransaction();
		// TODO Auto-generated method stub
		return STATUS_OK;
	}

}
