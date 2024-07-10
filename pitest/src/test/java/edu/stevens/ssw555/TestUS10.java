package edu.stevens.ssw555;


import java.text.ParseException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

import org.junit.Assert;
public class TestUS10 {




	
		
		
		
		
		
		
		@Test
		public void testMarriedAfter14() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US10 userStory=new US10();
			GedcomService gedcomService=new GedcomService();
			mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1960", "", "", "", "", ""));
			mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1950", "", "", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 2010", "", "", "", "", ""));

			mapFamily.put("I1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "3"));
		Assert.assertEquals(userStory.checkMarriedAfter14(mapIndividual, mapFamily).size(),0);
			
		}

		@Test
		public void testNotMarriedBefore14One() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US10 userStory=new US10();
			GedcomService gedcomService=new GedcomService();
			mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1986", "", "", "", "", ""));
			mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1950", "", "", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 1980", "", "", "NA", "", ""));

			mapFamily.put("I1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "3"));
			Assert.assertTrue(userStory.checkMarriedAfter14(mapIndividual, mapFamily).size()==1);

		}
		@Test
		public void testNotMarriedBefore14Multi() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US10 userStory=new US10();
			GedcomService gedcomService=new GedcomService();
			mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1986", "", "", "", "", ""));
			mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1989", "", "", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 1980", "", "", "NA", "", ""));
			mapIndividual.put("I4", gedcomService.makeIndividualAttributeMap("Jimmie Smith", "M", "22 APR 1970", "", "", "NA", "", ""));

			mapFamily.put("I1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "3"));
	
			Assert.assertTrue(userStory.checkMarriedAfter14(mapIndividual, mapFamily).size()>1);

		}


		@Test
		public void testNotMarriedBefore14Empty() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US10 userStory=new US10();
			
			Assert.assertTrue(userStory.checkMarriedAfter14(mapIndividual, mapFamily).size()==0);

		}
		@Test
		public void testNotMarriedBefore14NullInput() throws ParseException{

			US10 userStory=new US10();
			
			Assert.assertTrue(userStory.checkMarriedAfter14(null, null).size()==0);

		}


		


	
}
