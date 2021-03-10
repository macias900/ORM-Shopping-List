package pl.air.sl.menu;


import pl.air.sl.dao.RecipieDAO;

import pl.air.sl.dao.ShoppingListDAO;


import pl.air.sl.model.Recipie;

import pl.air.sl.model.ShoppingList;


public class RecipieNew  extends BaseMenuItem{

	@Override
	public String getCaption() {
		
		return "Dodaj nowy przepis";
	}

	@Override
	public int execute() {
		
		String name = readSrv.readString("Nazwa przepisu");
		String description = readSrv.readString("Opis:");
		Long id = readSrv.readLong("ID Listy zakupów potrzebnej do wykonania");
	
		beginTransaction();
		ShoppingListDAO dao = getShoppingListDAO();
		ShoppingList list = dao.findById(id);
		
		if(list == null) {
			
			System.out.println("Nie ma listy o podanym ID");
			rollbackTransaction();
			return STATUS_OK;
		}
		
		
		
		Recipie one = new Recipie(name, description, list);

		
		
		
		boolean isValid = validate(one);
		if(isValid) {
			//beginTransaction();
			
			RecipieDAO rdao = getRecipieDAO();
			Long ida = rdao.save(one);
			one = rdao.findById(ida);
			if ( one != null) {
				
				System.out.println("Dodano przepis : " + one.getName());
			}
			else {
				System.out.println("Przepis nie został dodany");
			}
			commitTransaction();
			
		} else {
			System.out.println("Przepis nie został dodany");
		}
		
		return STATUS_OK;
	}

}
