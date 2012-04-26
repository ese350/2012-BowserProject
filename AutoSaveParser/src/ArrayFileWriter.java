import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ArrayFileWriter {
	
	private static String newLineSequence = System.getProperty("line.separator");
	
	public static void writeArrayFile(String newFileName, String readFileName){
		if (newFileName == null || readFileName == null) throw new IllegalArgumentException("Null argument");
		try {
			File file = new File(newFileName);
			FileWriter out = new FileWriter(file);
			Parser p = new Parser(readFileName);
			String[] data = p.getData();
			String arrayName = newFileName.replaceAll(".js", "");
			out.write("//Javascript file by Cam Cogan and David Hallac" + newLineSequence);
			out.write("var " + arrayName + " = new Array();" + newLineSequence);
			for (int i = 0; i < data.length; i++){
				out.write(arrayName + "[" + i + "] = " + data[i] + ";" + newLineSequence);
			}
			out.close();
			file.setReadable(true);
		} catch (IOException e){
			throw new RuntimeException("Cannot write file: " + newFileName);
		}
		
	}

}
