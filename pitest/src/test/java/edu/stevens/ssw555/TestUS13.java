package edu.stevens.ssw555;


import java.text.ParseException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

import org.junit.Assert;
public class TestUS13 {




	
		
		
		
		
		
		
		@Test
		public void testSiblingfSpacingCorrect() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US13 userStory=new US13();
			GedcomService gedcomService=new GedcomService();
			mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1960", "57", "", "", "", ""));
			mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1960", "57", "", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 2010", "7", "", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 2010", "7", "", "", "", ""));

			mapFamily.put("F1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "{I3, I4}"));
		Assert.assertEquals(userStory.checkSiblingSpacing(mapIndividual, mapFamily).size(),0);
			
		}

		@Test
		public void testSiblingfSpacingInCorrectOne() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US13 userStory=new US13();
			GedcomService gedcomService=new GedcomService();
			mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1946", "71", "", "", "", ""));
			mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1950", "", "67", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 2017", "0", "", "NA", "", ""));
			mapIndividual.put("I4", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 MAY 2017", "0", "", "NA", "", ""));

			mapFamily.put("F1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "{I3, I4}"));

			Assert.assertTrue(userStory.checkSiblingSpacing(mapIndividual, mapFamily).size()==1);

		}
		@Test
		public void testSiblingfSpacingInCorrectMulti() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US13 userStory=new US13();
			GedcomService gedcomService=new GedcomService();
			mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1869", "148", "", "", "", ""));
			mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1870", "147", "", "", "", ""));
			mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 1940", "77", "", "NA", "", ""));
			mapIndividual.put("I6", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "25 MAY 1940", "77", "", "NA", "", ""));
			mapIndividual.put("I7", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "28 JUN 1940", "77", "", "NA", "", ""));

			mapIndividual.put("I4", gedcomService.makeIndividualAttributeMap("Jimmie Smith", "F", "22 APR 1950", "67", "", "NA", "", ""));

			mapIndividual.put("I5", gedcomService.makeIndividualAttributeMap("Emma Smith", "F", "22 APR 2017", "0", "", "NA", "", ""));

			mapFamily.put("F1", gedcomService.makeFamilyAttributeMap("22 APR 1934", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "{I3, I6, I7}"));
			mapFamily.put("F2", gedcomService.makeFamilyAttributeMap("22 APR 2015", "NA", "I3", "Harry Smith", "I4", "Jimmie Smith", "I5"));
			Assert.assertTrue(userStory.checkSiblingSpacing(mapIndividual, mapFamily).size()>1);

		}


		@Test
		public void testParentsTooOldEmpty() throws ParseException{
			Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
			Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US13 userStory=new US13();
			
			Assert.assertTrue(userStory.checkSiblingSpacing(mapIndividual, mapFamily).size()==0);

		}
		@Test
		public void testParentsTooOldNullInput() throws ParseException{

			US13 userStory=new US13();
			
			Assert.assertTrue(userStory.checkSiblingSpacing(null, null).size()==0);

		}


		


	
}
