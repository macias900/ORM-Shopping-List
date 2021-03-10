package pl.air.sl.menu;

public interface MenuItem {
	
	String getCaption();
	int execute();
	
	static final int STATUS_EXIT = 0;
	static final int STATUS_OK = 1;

}
