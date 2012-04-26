import java.io.IOException;


public class Runner {
	
	public static void main (String[] args0){
		//ArrayFileWriter.writeArrayFile("C:\\Users\\Cam\\Desktop\\points1.js","datadumpex2.txt");
	    System.out.println(System.getProperty("user.dir"));
	    try {
			Parser p = new Parser("C:\\Users\\Cam\\Desktop\\ESE 350\\Final Project\\BowserProject\\PCAN Tests\\10_sec_crazy_brights.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
