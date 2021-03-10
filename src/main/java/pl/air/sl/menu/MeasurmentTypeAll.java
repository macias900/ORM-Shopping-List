package pl.air.sl.menu;

import java.util.List;

import pl.air.sl.dao.MeasurmentTypeDAO;
import pl.air.sl.model.MeasurmentType;

public class MeasurmentTypeAll extends BaseMenuItem {

	@Override
	public String getCaption() {
		
		return "Wyświetl listę dostępnych typów pomiaru";
	}

	@Override
	public int execute() {
		
		beginTransaction();
		MeasurmentTypeDAO dao = getMeasurmentTypeDAO();
		List<MeasurmentType> list = dao.findAll();
		
		System.out.println("Lista dostępnych typów pomiaru");
		printSrv.printMeasurmentTypes(list);
		commitTransaction();
		// TODO Auto-generated method stub
		return STATUS_OK;
	}

}
