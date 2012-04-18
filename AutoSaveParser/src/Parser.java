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
		
		/* The first two converter variables and both temp variables are for
		 * converting latitude and longitude. The third converter variable is
		 * for converting the timestamp.
		 */
		
		double converter1, converter2, temp1, temp2;
		int converter3;
		
		while(sentence != null){
			if (sentence.contains("$GPGLL")){
				String[] chunks = sentence.split(",");
				chunks[5] = chunks[5].replaceAll(".00", "");
				
				/*Since the latitude and longitude data comes in degrees and 
				 * minutes rather than a simple decimal representation of the
				 * degree value, we have to do some numerical gymnastics below
				 * to convert the GPS coordinates from the degree/minute form,
				 * as they're received, to the decimal representation form that
				 * the Google Maps API requires.
				 */
				
				temp1 = new Double(chunks[1]);
				temp2 = new Double(chunks[3]);
				//temp1 and temp2 are the latitude and longitude values,
				//respectively, in whole degrees
				temp1 = (int) Math.floor(temp1/100);
				temp2 = (int) Math.floor(temp2/100);
				
				converter1 = new Double(chunks[1]);
				converter2 = new Double(chunks[3]);
				
				//Trimming away the degree values, leaving just the minutes
				converter1 %= 100;
				converter2 %= 100;
				
				//100 decimals/60 minutes -> multiplication by 5/3
				//Also shifts values back two decimal places to match desired 
				//output format.
				converter1 *= 5;
				converter2 *= 5;
				converter1 /= 300;
				converter2 /= 300;
				//Recombining degree values
				converter1 += temp1;
				converter2 += temp2;
				converter3 = new Integer(chunks[5]);
				
				//Conversions to proper sign by hemisphere
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
