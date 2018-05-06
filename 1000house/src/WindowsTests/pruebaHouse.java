package WindowsTests;



import app.*;
import controllers.*
;
import windows.HouseWindow;public class pruebaHouse {

	public static void main(String[] args) throws Exception{
		User host;
		Application app;
		app = new Application("Test");
		host = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		host.setState(UserStates.CONNECTED_HOST);
		app.setLog(host);
		HouseWindow h = new HouseWindow();
		
		HouseController cont = new HouseController(h, app);
		h.setHouseController(cont);
		
		MenuController menu = new MenuController(h, app);
		h.setMenuController(menu);
	}

}