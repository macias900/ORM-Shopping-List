package pl.air.sl.menu;

import pl.air.sl.dao.MeasurmentTypeDAO;
import pl.air.sl.dao.ShoppingItemDAO;
import pl.air.sl.model.MeasurmentType;
import pl.air.sl.model.ShoppingItem;

public class ShoppingItemNew  extends BaseMenuItem{

	@Override
	public String getCaption() {
		
		return "Nowy produkt";
	}

	@Override
	public int execute() {
		
		String name = readSrv.readString("Nazwa");
		Long id = readSrv.readLong("ID typu mierzenia");
		beginTransaction();
		MeasurmentTypeDAO mdao = getMeasurmentTypeDAO();
		MeasurmentType mst = mdao.findById(id);
		if(mst == null){
			
			System.out.println("Nie ma typu mierzenia o podanym ID");
			commitTransaction();
			return STATUS_OK;
			
		}
		
		ShoppingItem one = new ShoppingItem(name, mst);
		
		boolean isValid = validate(one);
		if(isValid) {
			
			ShoppingItemDAO dao = getShoppingItemDAO();
			Long ida = dao.save(one);
			one = dao.findById(ida);
			if ( one != null) {
				
				printSrv.printItem(one);
			}
			else {
				System.out.println("Nowy produkt nie został utworzony");
			}
			commitTransaction();
			
		} else {
			System.out.println("Nowy produkt nie został utworzony");
		}
		
		return STATUS_OK;
	}

}
