package edu.stevens.ssw555;


import java.text.ParseException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

import org.junit.Assert;
public class TestUS21 {




	
		
		
		
		
		
		
		@Test
		public void testHusIsMaleWifeIsFemale() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US21 userStory=new US21();
			GedcomService gedcomService=new GedcomService();
			mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1960", "", "", "", "", ""));
			mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1950", "", "", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 2010", "", "", "", "", ""));

			mapFamily.put("I1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "3"));
		Assert.assertEquals(userStory.checkMaleFemale(mapIndividual, mapFamily).size(),0);
			
		}

		@Test
		public void testNotMaleFemaleOne() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US21 userStory=new US21();
			GedcomService gedcomService=new GedcomService();
			mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "M", "28 APR 1986", "", "", "", "", ""));
			mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1950", "", "", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 1980", "", "", "NA", "", ""));

			mapFamily.put("I1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "3"));
			Assert.assertTrue(userStory.checkMaleFemale(mapIndividual, mapFamily).size()==1);

		}
		@Test
		public void testNotMaleFemaleMulti() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US21 userStory=new US21();
			GedcomService gedcomService=new GedcomService();
			mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "M", "28 APR 1986", "", "", "", "", ""));
			mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "F", "29 APR 1989", "", "", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 1980", "", "", "NA", "", ""));
			mapIndividual.put("I4", gedcomService.makeIndividualAttributeMap("Jimmie Smith", "M", "22 APR 1970", "", "", "NA", "", ""));

			mapFamily.put("I1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "3"));
	
			Assert.assertTrue(userStory.checkMaleFemale(mapIndividual, mapFamily).size()>1);

		}


		@Test
		public void testNotMaleFemaleEmpty() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US21 userStory=new US21();
			
			Assert.assertTrue(userStory.checkMaleFemale(mapIndividual, mapFamily).size()==0);

		}
		@Test
		public void testNotMaleFemaleNullInput() throws ParseException{

			US21 userStory=new US21();
			
			Assert.assertTrue(userStory.checkMaleFemale(null, null).size()==0);

		}


		


	
}
