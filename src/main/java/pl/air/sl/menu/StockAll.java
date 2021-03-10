package pl.air.sl.menu;

import java.util.List;


import pl.air.sl.dao.*;

import pl.air.sl.model.*;

public class StockAll extends BaseMenuItem {

	@Override
	public String getCaption() {
		
		return "Wyświetl posiadane produkty";
	}

	@Override
	public int execute() {
		
		beginTransaction();
		StockDAO dao = getStockDAO();
		
		List<Stock> list = dao.findAll();
		
		System.out.println("Posiadane produkty:");
		printSrv.printStockList(list);
		commitTransaction();
		// TODO Auto-generated method stub
		return STATUS_OK;
	}

}
