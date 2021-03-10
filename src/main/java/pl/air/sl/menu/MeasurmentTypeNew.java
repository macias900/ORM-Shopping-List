package pl.air.sl.menu;

import pl.air.sl.dao.MeasurmentTypeDAO;
import pl.air.sl.model.MeasurmentType;

public class MeasurmentTypeNew  extends BaseMenuItem{

	@Override
	public String getCaption() {
		
		return "Nowy typ mierzenia";
	}

	@Override
	public int execute() {
		
		String name = readSrv.readString("nazwa");
		
		MeasurmentType one = new MeasurmentType(name);
		
		boolean isValid = validate(one);
		if(isValid) {
			
			beginTransaction();
			MeasurmentTypeDAO dao = getMeasurmentTypeDAO();
			Long id = dao.save(one);
			one = dao.findById(id);
			if ( one != null) {
				
				printSrv.printMeasurmentType(one);
			}
			else {
				System.out.println("Nowy typ nie został utworzony");
			}
			commitTransaction();
			
		} else {
			System.out.println("Nowy typ nie został utworzony");
		}
		
		return STATUS_OK;
	}

}
