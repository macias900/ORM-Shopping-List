package pl.air.sl.menu;

import pl.air.sl.dao.RecipieDAO;
import pl.air.sl.model.Recipie;

public class RecipieOne extends BaseMenuItem {

	@Override
	public String getCaption() {
		return "Wyświetl przepis o podanym ID";
	}

	@Override
	public int execute() {

		Long id = readSrv.readLong("id");
		if(id != null) {
			beginTransaction();
			RecipieDAO dao = getRecipieDAO();
			Recipie one = dao.findById(id);
			if(one != null) {
				
				printSrv.printRecipie(one);
			}
			else {
				System.out.println("Brak przepisu o podanym id.");
			}
			commitTransaction();
		}
		return STATUS_OK;
	}

}
