package pl.air.sl.menu;

import java.util.ArrayList;
import java.util.List;

import pl.air.sl.dao.QuantityOfItemDAO;
import pl.air.sl.dao.ShoppingItemDAO;
import pl.air.sl.dao.ShoppingListDAO;

import pl.air.sl.model.QuantityOfItem;
import pl.air.sl.model.ShoppingItem;
import pl.air.sl.model.ShoppingList;


public class ShoppingListNew extends BaseMenuItem {

	@Override
	public String getCaption() {

		return "Dodaj listę zakupów";
	}

	@Override
	public int execute() {

		beginTransaction();
		ShoppingItemDAO dao = getShoppingItemDAO();
		QuantityOfItemDAO qdao = getQuantityOfItemDAO();

		String name = readSrv.readString("Nazwa listy");

		List<ShoppingItem> list = new ArrayList<>();
		List<Integer> qlist = new ArrayList<>();

		while (true) {

			Long id = readSrv.readLong("Id produktu: ");
			if (id == null && list.size() >= 0) {
				
				break;
			}
			ShoppingItem one = dao.findById(id);
			if (one != null) {
				Integer quantity = readSrv.readInteger("Ilość: ");
				if (quantity != null) {
					list.add(one);
					qlist.add(quantity);
				} else {
					break;
				
				}
			}

		}

		ShoppingList sone = new ShoppingList(name, list);

		boolean isValid = validate(sone);
		if (isValid) {
			// beginTransaction();

			ShoppingListDAO sldao = getShoppingListDAO();
			Long ida = sldao.save(sone);
			sone = sldao.findById(ida);
			if (sone != null) {

				int i = 0 ;
				
				for (ShoppingItem xitem : list) {
					

					QuantityOfItem qone = new QuantityOfItem(qlist.get(i), xitem, sone);
					boolean isValidq = validate(qone);
					if (isValidq) {
						System.out.println(qone.getQuantity() + " " + qone.getShoppingItem().getName());
						qdao.save(qone);
						i++;

					} else {
						System.out.println("Lista nie została dodana ");
						rollbackTransaction();
					}

				}

				System.out.println("Dodano listę zakupów : " + sone.getName());
			} else {
				System.out.println("Lista nie została dodana");
			}
			commitTransaction();

		} else {
			System.out.println("Lista nie została dodana ");
			rollbackTransaction();
		}

		return STATUS_OK;
	}

}
