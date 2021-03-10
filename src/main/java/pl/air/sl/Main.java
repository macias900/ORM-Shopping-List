package pl.air.sl;

import pl.air.sl.service.DataService;
import pl.air.sl.service.HibernateService;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("START");
		HibernateService.startup();
		
		if(HibernateService.isCreate()) {
			
			DataService.insertInitData();
		}
		
		ShoppingListSystem sls = new ShoppingListSystem();
		
		sls.run();
		
		HibernateService.shutdown();
		System.out.println("END");
	}

}
