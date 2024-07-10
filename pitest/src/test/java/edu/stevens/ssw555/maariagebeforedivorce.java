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
public class maariagebeforedivorce extends TestCase {
	
	
	@SuppressWarnings("unchecked")
	public void marriagedayslist() throws FileNotFoundException, ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		HashMap<String, Family> families = new HashMap<String, Family>();
		Map<String, Family> famMap = new HashMap<String, Family>(families);
		//Map<String, Individual> indMap = new HashMap<String, Individual>(individuals);
		Iterator<Map.Entry<String, Family>> famEntries = famMap.entrySet().iterator();
		while (famEntries.hasNext()) {
			Map.Entry<String, Family> famEntry = famEntries.next();
			Family fam = famEntry.getValue();
			Map<String, Family> Marriagedate = new HashMap<String, Family>();
		 
			for (Iterator<Entry<String, Family>> iterator = famMap.entrySet().iterator(); iterator.hasNext();) {
				Marriagedate = (Map<String, Family>) sdf.parse(fam.getMarriage());
				Assert.assertEquals(Marriagedate, fam.getMarriage());
			}
			}
		}

}
