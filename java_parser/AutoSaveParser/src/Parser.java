import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	
	private String[] exportable;
	
	public Parser(String file) throws IOException{
		if (file == null) throw new IllegalArgumentException();
		
		ArrayList<String> data = new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader(file));
		String sentence = in.readLine();
		
		double converter1, converter2;
		int converter3;
		
		while(sentence != null){
			if (sentence.contains("$GPGLL")){
				String[] chunks = sentence.split(",");
				chunks[5] = chunks[5].replaceAll(".00", "");
				
				converter1 = new Double(chunks[1]);
				converter2 = new Double(chunks[3]);
				converter3 = new Integer(chunks[5]);
				
				converter1 /= 100;
				converter2 /= 100;
				if (chunks[2].equals("S")) converter1 *= -1;
				if (chunks[4].equals("W")) converter2 *= -1;
				converter3 += 200000;	//Changes to Eastern Time Zone (4 hours earlier than UTC-1)
				converter3 %= 240000;	//Returns to military time format

				chunks[1] = Double.toString(converter1);
				chunks[3] = Double.toString(converter2);
				chunks[5] = Integer.toString(converter3);				
				
				data.add(chunks[1]);
				data.add(chunks[3]);
				data.add(chunks[5]);
			}
			sentence = in.readLine();
			
			//TODO: Add protocol for writing OBD data here
			
		}
		
		in.close();
		exportable = data.toArray(new String[0]);
	
	}
	
	public String[] getData(){
		return exportable;
	}

}
