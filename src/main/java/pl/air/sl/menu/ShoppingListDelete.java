package pl.air.sl.menu;

import java.util.List;

import pl.air.sl.dao.QuantityOfItemDAO;
import pl.air.sl.dao.RecipieDAO;
import pl.air.sl.dao.ShoppingListDAO;
import pl.air.sl.model.Recipie;
import pl.air.sl.model.ShoppingList;

public class ShoppingListDelete extends BaseMenuItem {

	@Override
	public String getCaption() {
		
		return "Usuń listę zakupów";
	}

	@Override
	public int execute() {
		
		System.out.println(
				"UWAGA ! Usunięcie listy zakupów może spowodować usunięcie przepisu powiązanego z tą listą ");
		String response = readSrv.readString("Kontynuować  ? (t/n)");
		
		if(response != null && response.equals("t")) {
			
			Long id = readSrv.readLong("ID listy zakupów do usunięcia");
			if(id != null) {
				beginTransaction();
				ShoppingListDAO sldao = getShoppingListDAO();
				RecipieDAO rdao = getRecipieDAO();
				QuantityOfItemDAO qdao = getQuantityOfItemDAO();
				
				ShoppingList list = sldao.findById(id);
				if(list != null) {
					
					System.out.println("Dane do usunięcia : ");
					
					List<Recipie> rlist = rdao.findAllByShoppingList(list);
					
					printSrv.printRecipieList(rlist);
					System.out.println("Lista zakupów o ID = " + id + " i nazwie : "+ list.getName());
					
					String answer = readSrv.readString("Potwierdz chęć usunięcia (t/n)");
					
					if(answer != null && answer.equals("t")) {
						
						rdao.deleteByShoppingList(list);
						qdao.deleteByShoppingList(list);
						sldao.delete(list);
						
						System.out.println("Dane zostały usunięte");
						
						
					} else {
						System.out.println("Operacja została przerwana");
					}
				} else {
					System.out.println("Brak listy o podanym id");
				}
			} else {
				System.out.println("Brak listy zakupów o podanym id");
			}
			
			
			commitTransaction();
			
			
		}

		
		return STATUS_OK;
	}

}
