package pl.air.sl.menu;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import pl.air.sl.dao.QuantityOfItemDAO;
import pl.air.sl.dao.RecipieDAO;
import pl.air.sl.dao.ShoppingItemDAO;
import pl.air.sl.dao.ShoppingListDAO;
import pl.air.sl.dao.StockDAO;
import pl.air.sl.model.Recipie;
import pl.air.sl.model.ShoppingItem;
import pl.air.sl.model.ShoppingList;
import pl.air.sl.model.Stock;

public class ShoppingItemDelete extends BaseMenuItem {

	@Override
	public String getCaption() {

		return "Usuń produkt";
	}

	@Override
	public int execute() {

		System.out.println(
				"UWAGA ! Usunięcie produktu spowoduje usunięcie list zakupów, na której wybrany produkt występuję, przepisów, w których dany produkt jest użyty"
						+ "oraz produkt zostanie usunięty z listy stanu. ");
		String response = readSrv.readString("Kontynuować  ? (t/n)");

		if (response != null && response.equals("t")) {
			try {

				Long id = readSrv.readLong("Podaj id produktu do usunięcia");
				if (id != null) {
					beginTransaction();
					ShoppingItemDAO sidao = getShoppingItemDAO();
					ShoppingListDAO sldao = getShoppingListDAO();
					RecipieDAO rdao = getRecipieDAO();
					StockDAO stdao = getStockDAO();
					QuantityOfItemDAO qdao = getQuantityOfItemDAO();

					System.out.println("Dane do usunięcia : ");

					ShoppingItem item = sidao.findById(id);
					List<ShoppingList> list = sldao.findAllByShoppingItem(item);
					List<Recipie> rlist = new ArrayList<>();
					Stock stock = stdao.findyByShoppingItem(item);

					for (ShoppingList one : list) {
						List<Recipie> rec = rdao.findAllByShoppingList(one);
						for (Recipie two : rec) {
							rlist.add(two);
						}
					}

					printSrv.printItem(item);
					printSrv.printShoppingLists(list);
					printSrv.printRecipieList(rlist);
					printSrv.printStock(stock);

					String answer = readSrv.readString("Potwierdz chęć usunięcia (t/n)");

					if (answer != null && answer.equals("t")) {

						stdao.delete(stock);

						for (ShoppingList x : list) {

							rdao.deleteByShoppingList(x);
							qdao.deleteByShoppingList(x);
						}

						sldao.deleteByShoppingItem(item);
						qdao.deleteByShoppingItem(item);

						sidao.delete(item);

						System.out.println("Dane zostały usunięte");

					} else {
						System.out.println("Operacja została przerwana");
					}

				} else {
					System.out.println("Brak produktu o podanym ID");
				}
				commitTransaction();
			} catch (NoResultException e) {
				rollbackTransaction();
				System.out.println("Brak produktu o podanym ID");
			}

		}

		return STATUS_OK;
	}

}
