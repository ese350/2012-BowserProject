import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class ParserTest {

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
}
