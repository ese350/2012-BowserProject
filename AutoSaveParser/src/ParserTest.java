import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

@SuppressWarnings("unused")
public class ParserTest {
	/*
	@Test public void floatTest(){
		String s = "5";
		assertTrue(new Double(s) == 5.00);
	}
	
	@Test public void nullTest(){
		try{
			new Parser(null);
			fail("Expected an IllegalArgumentException for null argument");
		} catch (Exception e){
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	@Test public void smallGPSDataTest() throws IOException{
		Parser p = new Parser("minidump.txt");
		assertTrue(p.getData().length == 3);
		assertEquals("193436.00",p.getData()[2]);
		assertEquals("39.5709775",p.getData()[0]);
		assertEquals("-75.1139923",p.getData()[1]);
	}

	@Test public void goodGPSDataTest() throws IOException{
		Parser p = new Parser("datadumpex.txt");
		assertTrue(p.getData().length == 9);
		assertEquals("193436.00",p.getData()[2]);
		assertEquals("39.5709775",p.getData()[0]);
		assertEquals("-75.1139923",p.getData()[1]);
		assertEquals("193437.00",p.getData()[5]);
		assertEquals("39.5709808",p.getData()[3]);
		assertEquals("-75.1139939",p.getData()[4]);
		assertEquals("193438.00",p.getData()[8]);
		assertEquals("39.5709812",p.getData()[6]);
		assertEquals("75.1139937",p.getData()[7]);
	}
	
	@Test public void obdDataTest() throws IOException{
		Parser p = new Parser("withoutHelp.txt");
	}
	*/
	@Test public void wheelAlgorithmTest(){
		String[] chunks = new String[]{"0x0E","0x8B"};
		chunks[0] = chunks[0].substring(2);
		chunks[1] = chunks[1].substring(2);
		System.out.println(chunks[0].contains("E"));
		if(chunks[0].contains("E") || chunks[0].contains("F")) chunks[0] = chunks[0].replaceFirst("0", "F");
		chunks[0] = chunks[0] + chunks[1];
		System.out.println(chunks[0]);
		int wheelAngle = Integer.valueOf(chunks[0],16).shortValue();
		System.out.println(wheelAngle);
		wheelAngle *= 1000;
		System.out.println(wheelAngle);
	}
}
