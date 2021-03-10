package pl.air.sl;

import java.util.ArrayList;
import java.util.List;

import pl.air.sl.menu.End;
import pl.air.sl.menu.MeasurmentTypeAll;
import pl.air.sl.menu.MeasurmentTypeNew;
import pl.air.sl.menu.MenuItem;

import pl.air.sl.menu.RecipieAll;
import pl.air.sl.menu.RecipieByShoppingList;
import pl.air.sl.menu.RecipieNew;
import pl.air.sl.menu.RecipieOne;
import pl.air.sl.menu.ShoppingItemAll;
import pl.air.sl.menu.ShoppingItemDelete;
import pl.air.sl.menu.ShoppingItemNew;
import pl.air.sl.menu.ShoppingItemOne;
import pl.air.sl.menu.ShoppingListAll;
import pl.air.sl.menu.ShoppingListDelete;
import pl.air.sl.menu.ShoppingListNew;
import pl.air.sl.menu.ShoppingListOne;
import pl.air.sl.menu.ShoppingListsByShoppingItem;
import pl.air.sl.menu.StockAll;
import pl.air.sl.menu.StockByShoppingItem;
import pl.air.sl.menu.StockDelete;
import pl.air.sl.menu.StockNew;
import pl.air.sl.menu.StockUpdate;
import pl.air.sl.service.ReadService;
import pl.air.sl.service.ReadServiceConsole;

public class ShoppingListSystem {

	private List<MenuItem> menu;
	private int menuSize;

	private ReadService readSrv;

	public ShoppingListSystem() {

		this.menu = new ArrayList<>();

		this.menu.add(new RecipieOne());
		this.menu.add(new ShoppingItemOne());
		this.menu.add(new ShoppingListOne());
		this.menu.add(new MeasurmentTypeAll());
		this.menu.add(new ShoppingItemAll());
		this.menu.add(new ShoppingListAll());
		this.menu.add(new StockAll());
		this.menu.add(new RecipieAll());
		this.menu.add(new ShoppingListsByShoppingItem());
		this.menu.add(new RecipieByShoppingList());
		this.menu.add(new StockByShoppingItem());
		this.menu.add(new MeasurmentTypeNew());
		this.menu.add(new ShoppingItemNew());
		this.menu.add(new StockNew());
		this.menu.add(new ShoppingListNew());
		this.menu.add(new RecipieNew());
		this.menu.add(new StockDelete());
		this.menu.add(new ShoppingItemDelete());
		this.menu.add(new ShoppingListDelete());
		this.menu.add(new StockUpdate());
		
		this.menu.add(new End());

		this.menuSize = this.menu.size();

		this.readSrv = new ReadServiceConsole();

	}
	
	private void printMenu() {
		System.out.println("Menu");
		System.out.println("====");
		int i = 1;
		for (MenuItem item : menu) {
			String caption = item.getCaption();
			System.out.println(i + ". " + caption);
			i++;
		}
	}

	public void run() {
		printMenu();

		while (true) {
			Integer itemIdx = readSrv.readInteger("Wybierz opcję (0 - wyświetl menu)");
			
			if(itemIdx == null || itemIdx < 0 || itemIdx > menuSize) {
				continue;
			}
			
			System.out.println();
			if(itemIdx == 0) {
				printMenu();
				continue;
			}
			

			MenuItem item = menu.get(itemIdx - 1);
			int status = item.execute();
			System.out.println();
			
			if(status == MenuItem.STATUS_EXIT) break;
		}

	}
	

}
