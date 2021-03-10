package pl.air.sl.menu;

import java.util.List;


import pl.air.sl.dao.*;

import pl.air.sl.model.*;

public class ShoppingItemAll extends BaseMenuItem {

	@Override
	public String getCaption() {
		
		return "Wyświetl listę dostępnych produktów";
	}

	@Override
	public int execute() {
		
		beginTransaction();
		ShoppingItemDAO dao = getShoppingItemDAO();
		
		List<ShoppingItem> list = dao.findAll();
		
		System.out.println("Lista dostępnych produktów");
		printSrv.printItemList(list);
		commitTransaction();
		// TODO Auto-generated method stub
		return STATUS_OK;
	}

}
