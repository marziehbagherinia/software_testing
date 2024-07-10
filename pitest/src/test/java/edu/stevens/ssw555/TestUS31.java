package edu.stevens.ssw555;


import java.text.ParseException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

import org.junit.Assert;
public class TestUS31 {




	
		
		
		
		
		
		
		@Test
		public void testMarriedBefore30() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US31 userStory=new US31();
			GedcomService gedcomService=new GedcomService();
			mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1960", "", "", "", "", ""));
			mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1950", "", "", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 2010", "", "", "", "", ""));

			mapFamily.put("I1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "3"));
		Assert.assertEquals(userStory.checkNeverMarried(mapIndividual, mapFamily).size(),0);
			
		}

		@Test
		public void testNeverMarriedOne() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US31 userStory=new US31();
			GedcomService gedcomService=new GedcomService();
			mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1960", "", "", "", "", ""));
			mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1950", "", "", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 1980", "", "", "NA", "", ""));

			mapFamily.put("I1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "3"));
		System.out.println(userStory.checkNeverMarried(mapIndividual, mapFamily));
			Assert.assertTrue(userStory.checkNeverMarried(mapIndividual, mapFamily).size()==1);

		}
		@Test
		public void testNeverMarriedMulti() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US31 userStory=new US31();
			GedcomService gedcomService=new GedcomService();
			mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1960", "", "", "", "", ""));
			mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1950", "", "", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 1980", "", "", "NA", "", ""));
			mapIndividual.put("I4", gedcomService.makeIndividualAttributeMap("Jimmie Smith", "M", "22 APR 1970", "", "", "NA", "", ""));

			mapFamily.put("I1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "3"));
	
			Assert.assertTrue(userStory.checkNeverMarried(mapIndividual, mapFamily).size()>1);

		}


		@Test
		public void testNeverMarriedEmpty() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US31 userStory=new US31();
			
			Assert.assertTrue(userStory.checkNeverMarried(mapIndividual, mapFamily).size()==0);

		}
		@Test
		public void testNeverMarriedEmptyNullInput() throws ParseException{

			US31 userStory=new US31();
			
			Assert.assertTrue(userStory.checkNeverMarried(null, null).size()==0);

		}


		


	
}
