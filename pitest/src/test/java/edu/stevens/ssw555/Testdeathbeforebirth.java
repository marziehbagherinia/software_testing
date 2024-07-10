package edu.stevens.ssw555;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class Testdeathbeforebirth extends TestCase {

		

	@SuppressWarnings("deprecation")
	public void testBirthdays() throws FileNotFoundException, ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		HashMap<String, Individual> individuals = new HashMap<String, Individual>();
		Map<String, Individual> map = new HashMap<String, Individual>(individuals);
		Iterator<Map.Entry<String, Individual>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Individual> entry = entries.next();
			Individual indi = entry.getValue();
			Map<String, Individual> date_of_birth = new HashMap<String, Individual>();
			for (Iterator<Entry<String, Individual>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
				date_of_birth = (Map<String, Individual>) sdf.parse(indi.getBirth());
				Assert.assertEquals(date_of_birth, indi.getBirth());
			}
			}
		}
	
	
	public void testdeathdays() throws FileNotFoundException, ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		HashMap<String, Individual> individuals = new HashMap<String, Individual>();
		Map<String, Individual> map = new HashMap<String, Individual>(individuals);
		Iterator<Map.Entry<String, Individual>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Individual> entry = entries.next();
			Individual indi = entry.getValue();
			Map<String, Individual> date_of_death = new HashMap<String, Individual>();
			for (Iterator<Entry<String, Individual>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
				date_of_death = (Map<String, Individual>) sdf.parse(indi.getDeath());
				Assert.assertEquals(date_of_death, indi.getDeath());
			}
			}
		}
	
	public void testCheckDate() throws ParseException{
		
		String date = "20 JUN 1990";
		Assert.assertEquals(date,"20 JUN 1990" );
		
	}
	
public void testInvalidBirthdays() throws FileNotFoundException, ParseException{
		
	Boolean flag=true;
		HashMap<String, Individual> individuals = new HashMap<String, Individual>();
		Map<String, Individual> map = new HashMap<String, Individual>(individuals);
		Iterator<Map.Entry<String, Individual>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map<String, Individual> date_of_birth = new HashMap<String, Individual>();
		for (Iterator<Entry<String, Individual>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Individual> entry1 = iterator.next();
			if(!date_of_birth.containsValue(entry1.getValue().toString())){
				flag=false;
			}
		}
		Assert.assertTrue(!flag);

		}
			
}
}




	