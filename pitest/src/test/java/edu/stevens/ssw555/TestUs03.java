package edu.stevens.ssw555;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Assert;

import junit.framework.*;
import junit.framework.TestCase;

public class TestUs03 extends TestCase {

		

	public void testBirthdays() throws FileNotFoundException, ParseException{
		
		SortedMap<Integer,String> mapIndividual=new TreeMap<>();

		mapIndividual.put(1, "Emily /Williams/ F 22 APR 1985 32 true NA {'F1'} {'F4'}");
		mapIndividual.put(2, "Bob /Williams/ M 19 OCT 1958 58 false 12 MAY 1995 NA {'F1'}");
		mapIndividual.put(3,"Emma /Davis/ F 18 APR 1960  57 true NA NA {'F2'}");
		mapIndividual.put(4, "Robert /Jones/ M 11 JUL 1960 56 true NA NA {'F2'}");
		mapIndividual.put(5, "Emily /Williams/ F 22 APR 1985 27 true NA {'F1'} {'F3'}");
		
		 SortedMap<Integer, String> birthdate = new TreeMap<>();
			 for (SortedMap.Entry entry : mapIndividual.entrySet()) {
				 String[] string = entry.getValue().toString().split(" ");
		         birthdate.put((Integer) entry.getKey(),string[3]+" "+string[4]+" "+string[5]);
		       }
			 US3 userStory=new US3();
				Assert.assertEquals(birthdate, userStory.birthday(mapIndividual));
			 
		}

		
		
	
	
	public void testCheckDate() throws ParseException{
		US3 userStory=new US3();
		Boolean flag=userStory.checkDate("20 JUN 1990");
		Assert.assertTrue(flag);
	}
	
	
	public void testInvalidBirthdays() throws FileNotFoundException, ParseException{
		
		
		SortedMap<Integer,String> mapIndividual=new TreeMap<>();

		mapIndividual.put(1, "Emily /Williams/ F 22 APR 1985 32 true NA {'F1'} {'F4'}");
		mapIndividual.put(2, "Bob /Williams/ M 19 OCT 1958 58 false 12 MAY 1995 NA {'F1'}");
		mapIndividual.put(3,"Emma /Davis/ F 18 APR 1960  57 true NA NA {'F2'}");
		mapIndividual.put(6, "Helen /Jones/ F 7 OCT 1998 18 true NA {'F2'} NA");
		mapIndividual.put(9, "Helen /Jones/ M 7 OCT 1998 19 true NA {'F2'} NA");

		Boolean flag=true;
		US3 userStory=new US3();
		SortedMap<Integer, String> deathdate=userStory.deathday(mapIndividual);
		for (SortedMap.Entry entry : deathdate.entrySet()) {
			if(!userStory.checkDate(entry.getValue().toString())){
				flag=false;
			}
		}
		Assert.assertTrue(flag);

	}
	public void testBirthdayList() throws FileNotFoundException, ParseException {
		SortedMap<Integer,String> mapIndividual=new TreeMap<>();

		mapIndividual.put(1, "Emily /Williams/ F 22 APR 1985 32 true NA {'F1'} {'F4'}");
		mapIndividual.put(2, "Bob /Williams/ M 19 OCT 1958 58 false 12 MAY 1995 NA {'F1'}");
		mapIndividual.put(3,"Emma /Davis/ F 18 APR 1960  57 true NA NA {'F2'}");
		mapIndividual.put(4, "Robert /Jones/ M 11 JUL 1960 56 true NA NA {'F2'}");
		
		//mapIndividual.put(9, "Helen /Jones/ M 7 OCT 1998 19 true NA {'F2'} NA");
		 SortedMap<Integer, String> birthdate = new TreeMap<>();
			 for (SortedMap.Entry entry : mapIndividual.entrySet()) {
				 String[] string = entry.getValue().toString().split(" ");
		         birthdate.put((Integer) entry.getKey(),string[3]+" "+string[4]+" "+string[5]);
		       }
			 US3 userStory=new US3();
				Assert.assertEquals(birthdate, userStory.birthday(mapIndividual));
			 
		 
	}
	public void testInvalidDeathDays() throws FileNotFoundException, ParseException{
		SortedMap<Integer,String> mapIndividual=new TreeMap<>();

		mapIndividual.put(1, "Emily /Williams/ F 22 APR 1985 32 true NA {'F1'} {'F4'}");
		mapIndividual.put(2, "Bob /Williams/ M 19 OCT 1958 58 false 12 MAY 1995 NA {'F1'}");
		mapIndividual.put(3,"Emma /Davis/ F 18 APR 1960  57 true NA NA {'F2'}");
		
		mapIndividual.put(5, "Emily /Williams/ F 22 APR 1985 27 true NA {'F1'} {'F3'}");
		
		mapIndividual.put(9, "Helen /Jones/ M 7 OCT 1998 19 true NA {'F2'} NA");

			Boolean flag=true;
			US3 userStory=new US3();
			SortedMap<Integer, String> deathday=userStory.deathday(mapIndividual);
			for (SortedMap.Entry entry : deathday.entrySet()) {
				if(!userStory.checkDate(entry.getValue().toString())){
					flag=false;
				}
			}
			Assert.assertTrue(flag);


	}
	
	public void testDeathdayList() throws FileNotFoundException, ParseException {
		SortedMap<Integer,String> mapIndividual=new TreeMap<>();

		mapIndividual.put(1, "Emily /Williams/ F 22 APR 1985 32 true NA {'F1'} {'F4'}");
		mapIndividual.put(2, "Bob /Williams/ M 19 OCT 1958 58 false 12 MAY 1995 NA {'F1'}");
		mapIndividual.put(3,"Emma /Davis/ F 18 APR 1960  57 true NA NA {'F2'}");
		mapIndividual.put(4, "Robert /Jones/ M 11 JUL 1960 56 true NA NA {'F2'}");
		mapIndividual.put(5, "Emily /Williams/ F 22 APR 1985 27 true NA {'F1'} {'F3'}");
		mapIndividual.put(6, "Helen /Jones/ F 7 OCT 1998 18 true NA {'F2'} NA");
		mapIndividual.put(9, "Helen /Jones/ M 7 OCT 1998 19 true NA {'F2'} NA");
		 SortedMap<Integer, String> deathdate = new TreeMap<>();
			 for (SortedMap.Entry entry : mapIndividual.entrySet()) {
				 
				 String[] string = entry.getValue().toString().split(" ");
				 if(string.length>12)
		         deathdate.put((Integer) entry.getKey(),string[8]+" "+string[9]+" "+string[10]);
		       }
			 US3 userStory=new US3();
				Assert.assertEquals(deathdate, userStory.deathday(mapIndividual));
			 
	}
}
