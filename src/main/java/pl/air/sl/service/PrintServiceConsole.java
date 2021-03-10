package pl.air.sl.service;

import java.util.List;

import pl.air.sl.model.MeasurmentType;
import pl.air.sl.model.QuantityOfItem;
import pl.air.sl.model.Recipie;
import pl.air.sl.model.ShoppingItem;
import pl.air.sl.model.ShoppingList;
import pl.air.sl.model.Stock;

public class PrintServiceConsole implements PrintService {
	
	private String getMeasurmentType(MeasurmentType one) {
		
		
		
		String name = one.getMeasurmentType();
		
		
		return name;
		
	}
	
	private String getItem(ShoppingItem one) {
		
		
		
		Long id = one.getId();
		String name = one.getName();
		MeasurmentType mst = one.getMst();
		String type = getMeasurmentType(mst);
		
		return String.format("(id = %d) | %15s  || typ mierzenia : %s  ",id, name, type );
		
	}
	
	private String getItems(List<ShoppingItem> list, String separator) {
		
		String printout = "";
		for(ShoppingItem one : list) {
			printout += getItem(one) + separator;
		}
		printout = printout.substring(0, printout.lastIndexOf(separator));
		
		return printout;
	}
	
	private String getQuantity(List<QuantityOfItem> one, String separator) {
		
		String printout = "";
		
		for(QuantityOfItem x : one) {
			
			ShoppingList sl = x.getShoppingList();
			
			Integer qnt = x.getQuantity();
			
			List<ShoppingItem> slist = sl.getShoppingList();
			
			for(ShoppingItem two : slist) {
				
				printout += "Nazwa produktu : "+ two.getName() + " Ilość : " + qnt + " " +  getMeasurmentType(two.getMst()) + separator;
				
			}
		}
		return printout;
		
		 
	}
	

	@Override
	public void printItem(ShoppingItem one) {
		
		System.out.println(getItem(one));
		
	}

	@Override
	public void printItemList(List<ShoppingItem> list) {
		
		System.out.println(getItems(list, "\n"));
		
	}

	@Override
	public void printShoppingList(List<QuantityOfItem> list) {
		
		String printout = getQuantity(list, "\n");
		System.out.println(printout);
		
	
		
		
		
	}

	@Override
	public void printShoppingLists(List<ShoppingList> list) {
		
	String printout = "";
		
		for(ShoppingList one : list) {
			Long id = one.getId();
			String name = one.getName();
			printout +=  "ID : " + id + " | nazwa: " + name + "\n";
		}
		
		System.out.println(printout);
		
	}

	@Override
	public void printStockList(List<Stock> list) {
		
		String printout = "";
		
		for(Stock one : list) {
			
			ShoppingItem item = one.getShopItem();
			Integer ilosc = one.getQuantity();
			
			
			
			printout += String.format("%-2d | %-20s | %-5d (%s) \n", one.getId(), item.getName(), ilosc, item.getMst().getMeasurmentType());
			//printout += "(ID : " + one.getId() +  ")  Nazwa produktu : " + item.getName() + "  | Ilość : " + ilosc + "(" + item.getMst().getMeasurmentType()+ ")" + "\n" ;
		}
		
		System.out.println(String.format("%-2s | %-20s | %-5s ", "ID", "Nazwa produktu", "Ilość"));
		System.out.println("=============================================");
		System.out.println("");
		System.out.println(printout);
		
	}

	@Override
	public void printMeasurmentType(MeasurmentType one) {
		
		System.out.println(getMeasurmentType(one));
	}

	@Override
	public void printMeasurmentTypes(List<MeasurmentType> list) {
		
		String printout = "";
		
		for(MeasurmentType one : list) {
			Long id = one.getId();
			printout += "ID : " + id + "  |  " + one.getMeasurmentType() + "\n";
		}
		
		System.out.println(printout);
		
	}

	@Override
	public void printRecipie(Recipie one) {
		
		Long id = one.getId();
		String descr = one.getDescription();
		String name = one.getName();
		ShoppingList sl = one.getShoppingList();
		Long listId = sl.getId();
		String listName = sl.getName();
		
		System.out.println("ID przepisu : " + id + " Nazwa przepisu : " + name + "\n" + "Opis : "+ descr + "\n" +
		"( ID i nazwa listy potrzebnej do wykonania przepisu : " + listId + " " + listName + " )");
		 
	}

	@Override
	public void printRecipieList(List<Recipie> list) {
		
		String printout = "";
		
		for(Recipie one : list) {
			
			printout += "ID : "+ one.getId() +" Nazwa: "+  one.getName()+ "\n";
			
		}
		
		System.out.println(printout);
		
		
	}

	@Override
	public void printQuantityOfItems(List<QuantityOfItem> list) {
		
		String printout = "";
		
		for(QuantityOfItem one: list) {
			Integer qnt = one.getQuantity();
			ShoppingItem item = one.getShoppingItem();
			//ShoppingList lista = one.getShoppingList();
			
			printout += String.format("%-20s | %-4d ( %s ) \n", item.getName(), qnt, item.getMst().getMeasurmentType()  );
			//printout += (item.getName() + " | " +  qnt + " ( " + item.getMst().getMeasurmentType() + " )" + "\n" ) ;
		}
		
		System.out.println(printout);
	
		
	}

	@Override
	public void printStock(Stock one) {
		
		System.out.println("ID : "  + one.getId() + " Nazwa produktu : " + one.getShopItem().getName() + " Ilość : " +  one.getQuantity() + "\n");
		
	}

}
