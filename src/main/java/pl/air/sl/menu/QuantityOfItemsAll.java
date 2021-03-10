package pl.air.sl.menu;

import java.util.List;

import pl.air.sl.dao.QuantityOfItemDAO;
import pl.air.sl.model.QuantityOfItem;

public class QuantityOfItemsAll  extends BaseMenuItem{

	@Override
	public String getCaption() {
		return "Wyświetl ilości";
	}

	@Override
	public int execute() {
		
		beginTransaction();
		QuantityOfItemDAO dao = getQuantityOfItemDAO();
		List<QuantityOfItem> list = dao.findAll();
		printSrv.printQuantityOfItems(list);
		commitTransaction();
		return STATUS_OK;
	}

}
