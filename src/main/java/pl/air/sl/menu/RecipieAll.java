package pl.air.sl.menu;

import java.util.List;


import pl.air.sl.dao.*;

import pl.air.sl.model.*;

public class RecipieAll extends BaseMenuItem {

	@Override
	public String getCaption() {
		
		return "Wyświetl listę przepisów";
	}

	@Override
	public int execute() {
		
		beginTransaction();
		RecipieDAO dao = getRecipieDAO();
		
		List<Recipie> list = dao.findAll();
		
		System.out.println("Lista dostępnych przepisów : ");
		printSrv.printRecipieList(list);
		commitTransaction();
		// TODO Auto-generated method stub
		return STATUS_OK;
	}

}
