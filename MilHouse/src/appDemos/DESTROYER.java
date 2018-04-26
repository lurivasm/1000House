package appDemos;


import java.io.File;




public class DESTROYER {

	public static void main(String[] args) throws Exception{
		File f = new File("Demo.objectData");
		f.delete();
	}

}
