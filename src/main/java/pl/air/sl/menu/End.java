package pl.air.sl.menu;

public class End implements MenuItem {

	@Override
	public String getCaption() {
		return "Koniec";
	}

	@Override
	public int execute() {
		System.out.println("Do widzenia !");
		return STATUS_EXIT;
	}

}
