package edu.stevens.ssw555;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

import org.junit.Assert;
import edu.stevens.ssw555.US25;



public class TestUS02 {
	
	
	
	
	
	
	
	@Test
	public void testBdayBeforeMarriage() throws ParseException{
		Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
		Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
		US02 userStory=new US02();
		GedcomService gedcomService=new GedcomService();
		mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1960", "", "", "", "", ""));
		mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1970", "", "", "", "", ""));
		mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 1997", "", "", "", "", ""));

		mapFamily.put("I1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "3"));

		
		
			Assert.assertEquals(userStory.checkBirthBeforeMarr(mapIndividual, mapFamily).size(),0);
		
	}

	@Test
	public void testBdayNotBeforeMarriageOne() throws ParseException{
		Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
		Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
		US02 userStory=new US02();
		GedcomService gedcomService=new GedcomService();
		mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1998", "", "", "", "", ""));
		mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 1970", "", "", "", "", ""));
		mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 1997", "", "", "", "", ""));

		mapFamily.put("I1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "3"));
Assert.assertTrue(userStory.checkBirthBeforeMarr(mapIndividual, mapFamily).size()==1);

	}
	@Test
	public void testBdayNotBeforeMarriageMulti() throws ParseException{
		Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
		Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
		US02 userStory=new US02();
		GedcomService gedcomService=new GedcomService();
		mapIndividual.put("I1", gedcomService.makeIndividualAttributeMap("Helen Smith", "F", "28 APR 1998", "", "", "", "", ""));
		mapIndividual.put("I2", gedcomService.makeIndividualAttributeMap("Joe Smith", "M", "29 APR 2000", "", "", "", "", ""));
		mapIndividual.put("I3", gedcomService.makeIndividualAttributeMap("Harry Smith", "M", "22 APR 2010", "", "", "", "", ""));

		mapFamily.put("I1", gedcomService.makeFamilyAttributeMap("22 APR 1994", "NA", "I2", "Joe Smith", "I1", "Helen Smith", "3"));

		Assert.assertTrue(userStory.checkBirthBeforeMarr(mapIndividual, mapFamily).size()>1);

	}


	@Test
	public void testBdayBeforeMarriageEmpty() throws ParseException{
		Map<String, Map<String, Object>> mapFamily=new TreeMap<>();
		Map<String, Map<String, String>> mapIndividual=new TreeMap<>();
			US02 userStory=new US02();
		
		Assert.assertTrue(userStory.checkBirthBeforeMarr(mapIndividual, mapFamily).size()==0);

	}
	@Test
	public void testBdayBeforeMarriageNullInput() throws ParseException{

		US02 userStory=new US02();
		
		Assert.assertTrue(userStory.checkBirthBeforeMarr(null, null).size()==0);

	}


	


}
