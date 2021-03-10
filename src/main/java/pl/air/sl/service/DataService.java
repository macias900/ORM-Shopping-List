package pl.air.sl.service;

import java.util.ArrayList;
import java.util.List;

import pl.air.sl.dao.MeasurmentTypeDAO;
import pl.air.sl.dao.QuantityOfItemDAO;
import pl.air.sl.dao.RecipieDAO;
import pl.air.sl.dao.ShoppingItemDAO;
import pl.air.sl.dao.ShoppingListDAO;
import pl.air.sl.dao.StockDAO;
import pl.air.sl.dao.hibernate.MeasurmentTypeDAOImpl;
import pl.air.sl.dao.hibernate.QuantityOfItemDAOImpl;
import pl.air.sl.dao.hibernate.RecipieDAOImpl;
import pl.air.sl.dao.hibernate.ShoppingItemDAOImpl;
import pl.air.sl.dao.hibernate.ShoppingListDAOImpl;
import pl.air.sl.dao.hibernate.StockDAOImpl;
import pl.air.sl.model.*;

public class DataService {
	
	public static void insertInitData() {
		
		
		// Przykładowe dane
		
		/* Measurment type */
		
		MeasurmentType gram = createMeasureType("gram");
		MeasurmentType ilosc = createMeasureType("ilosc");
		MeasurmentType opakowanie = createMeasureType("opakowania");
		MeasurmentType litry = createMeasureType("litry");
		MeasurmentType plastry = createMeasureType("plastry");
		
		
		/* Shopping item */
		
		ShoppingItem jabko = createItem("Jabłko", ilosc); 
		ShoppingItem papryka = createItem("Papryka", ilosc);
		ShoppingItem chleb = createItem("chleb", ilosc);
		ShoppingItem ser = createItem("ser", plastry);
		ShoppingItem szynka = createItem("szynka", gram);
		ShoppingItem woda = createItem("woda", litry);
		ShoppingItem jajka = createItem("jajka", opakowanie);
		ShoppingItem kurczak = createItem("Pierś z kurczaka", gram);
		ShoppingItem ryz = createItem("ryż", opakowanie);
		ShoppingItem buraczki = createItem("buraczki", opakowanie);
		ShoppingItem cola = createItem("coca-cola", litry);
		ShoppingItem pieczarki = createItem("pieczarki", ilosc);
		
		
		
		/* Shopping List */

		ShoppingList pierwszaLista = createShoppingList("Śniadanie", jabko, chleb, szynka, ser, woda, jajka);
		ShoppingList drugaLista = createShoppingList("Obiad", kurczak, ryz, buraczki, pieczarki);
		ShoppingList trzeciaLista = createShoppingList("Napoje", woda, cola);
		ShoppingList czwartaLista = createShoppingList("Kolacja", chleb, ser, szynka);
		ShoppingList piataLista = createShoppingList("Nowa", cola, chleb, jajka, szynka);
		
		
		
		QuantityOfItem jblko = createQuantity(5, jabko, pierwszaLista);
		QuantityOfItem chlb = createQuantity(1, chleb, pierwszaLista);
		QuantityOfItem ham = createQuantity(100, szynka, pierwszaLista);
		QuantityOfItem cheese = createQuantity(5, ser, pierwszaLista);
		QuantityOfItem water = createQuantity(10, woda, pierwszaLista);
		QuantityOfItem eggs = createQuantity(1, jajka, pierwszaLista);
		QuantityOfItem chicken = createQuantity(1000, kurczak, drugaLista);
		QuantityOfItem rice = createQuantity(1, ryz, drugaLista);
		QuantityOfItem burki = createQuantity(2, buraczki, drugaLista);
		QuantityOfItem mushrooms = createQuantity(10, pieczarki, drugaLista);
		QuantityOfItem water2 = createQuantity(9, woda, trzeciaLista);
		QuantityOfItem coca = createQuantity(4, cola, trzeciaLista);
		QuantityOfItem chlb2 = createQuantity(2, chleb, czwartaLista);
		QuantityOfItem cheese2 = createQuantity(20, ser, czwartaLista);
		QuantityOfItem ham2 = createQuantity(400, szynka, czwartaLista);
		QuantityOfItem cola2 = createQuantity(10, cola, piataLista);
		QuantityOfItem chlb3 = createQuantity(2, chleb, piataLista);
		QuantityOfItem eggs2 = createQuantity(2, jajka, piataLista);
		QuantityOfItem ham3 = createQuantity(500, szynka, piataLista);
		
		
		
		

		
		/* Stock */
		
		Stock one = createStock(jabko, 0);
		Stock two = createStock(papryka, 3);
		Stock tree = createStock(chleb, 1);
		Stock four = createStock(ser, 10);
		Stock five = createStock(szynka, 200);
		Stock six = createStock(woda, 4);
		Stock seven = createStock(jajka, 0);
		Stock eight = createStock(kurczak, 500);
		
		
		
		/* recipie */
		
		Recipie breakfast = createRecipie("Śniadanie", "Pokrój chleb, zrób kanapki z "
				+ "serem i szynką, usmaż jajka. Zjedz jajecznicę z kanapką , popij wodą a na deser zjedz jabłko", pierwszaLista);
		
		Recipie obiad = createRecipie("Obiad", "Zrób obiad", drugaLista);
		
		Recipie drink = createRecipie("Drinki", "Zrób sobie drinka", trzeciaLista);
		
		Recipie kolacja = createRecipie("Kolacja", "Zrób kolację", czwartaLista);
		
		Recipie cos = createRecipie("Przykład", "Przykładowy opis", piataLista);
		
		/* save data */
		
		HibernateService.beginTransaction();
		
		saveMeasurmentTypes(gram, ilosc, opakowanie, litry, plastry);
		saveShoppingItems(jabko, papryka, chleb, pieczarki, ser, szynka, woda, jajka, kurczak, ryz, buraczki, cola);
		saveShoppingLists(pierwszaLista, drugaLista, trzeciaLista, czwartaLista, piataLista);
		saveQuantityOfItems(jblko, chlb, ham, cheese, water, eggs, chicken, rice, burki,mushrooms, water2, coca, chlb2, cheese2, ham2, cola2, chlb3, eggs2, ham3);
		saveStocks(one, two, tree, four, five, six, seven, eight);
		saveRecipies(breakfast, obiad, drink, kolacja, cos);
		
		HibernateService.commitTransaction();
		
		
		
	}
	
	public static void saveMeasurmentTypes(MeasurmentType...list) {
		MeasurmentTypeDAO dao = new MeasurmentTypeDAOImpl();
		for(MeasurmentType one : list) {
			dao.save(one);
		}
	}
	
	public static void saveShoppingItems(ShoppingItem...list) {
		ShoppingItemDAO dao = new ShoppingItemDAOImpl();
		for(ShoppingItem one : list) {
			dao.save(one);
		}
	}
	
	public static void saveShoppingLists(ShoppingList...list) {
		ShoppingListDAO dao = new ShoppingListDAOImpl();
		for(ShoppingList one : list) {
			dao.save(one);
		}
	}
	
	public static void saveQuantityOfItems(QuantityOfItem...list) {
		QuantityOfItemDAO dao = new QuantityOfItemDAOImpl();
		for(QuantityOfItem one : list) {
			dao.save(one);
		}
	}
	
	public static void saveStocks(Stock...list) {
		StockDAO dao = new StockDAOImpl();
		for(Stock one : list) {
			dao.save(one);
		}
	}
	
	public static void saveRecipies(Recipie...list) {
		RecipieDAO dao = new RecipieDAOImpl();
		for(Recipie one : list) {
			dao.save(one);
		}
	}
	
	
	private static Stock createStock(ShoppingItem shoppingItem, Integer quantity) {
		return Stock.builder()
				.shopItem(shoppingItem)
				.quantity(quantity)
				.build();
	}
	
	private static ShoppingItem createItem(String name, MeasurmentType measurmentType) {
		return ShoppingItem.builder()
				.name(name)
				.mst(measurmentType)
				.build();
	}
	
	private static ShoppingList createShoppingList(String name, ShoppingItem...shoppingItems) {
		 List<ShoppingItem> shoppingList = new ArrayList<>();
		for(ShoppingItem item : shoppingItems) {
			shoppingList.add(item);
		}
		return ShoppingList.builder()
				.name(name)
				.shoppingList(shoppingList)
				.build();
	}
	
	private static Recipie createRecipie(String name, String description, ShoppingList shoppingList) {
		return Recipie.builder()
				.name(name)
				.description(description)
				.shoppingList(shoppingList)
				.build();
	}
	
	private static MeasurmentType createMeasureType(String name) {
		return MeasurmentType.builder()
				.measurmentType(name)
				.build();
	}
	
	private static QuantityOfItem createQuantity(Integer quantity, ShoppingItem shoppingItem, ShoppingList shoppingList) {
		return QuantityOfItem.builder()
				.quantity(quantity)
				.shoppingItem(shoppingItem)
				.shoppingList(shoppingList)
				.build();
	}

}
