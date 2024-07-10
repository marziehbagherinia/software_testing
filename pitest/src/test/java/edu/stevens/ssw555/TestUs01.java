package edu.stevens.ssw555;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.Assert;












public class TestUs01  {
@Test
	public void testCheckDate() throws ParseException{
		US01 userStory=new US01();
		Boolean flag=userStory.checkDate("22 JUl 2016");
		Assert.assertTrue(flag);
	}
@Test
	public void testInvalidBirthdays() throws FileNotFoundException, ParseException{
	SortedMap<Integer,String> mapIndividual=new TreeMap<>();

	mapIndividual.put(1, "Emily /Williams/ F 22 APR 1985 32 true NA {'F1'} {'F4'}");
	mapIndividual.put(2, "Bob /Williams/ M 19 OCT 1958 58 false 12 MAY 1995 NA {'F1'}");
	mapIndividual.put(3,"Emma /Davis/ F 18 APR 1960  57 true NA NA {'F2'}");
	mapIndividual.put(4, "Robert /Jones/ M 11 JUL 1960 56 true NA NA {'F2'}");
	mapIndividual.put(5, "Emily /Williams/ F 22 APR 1985 27 true NA {'F1'} {'F3'}");
	mapIndividual.put(6, "Helen /Jones/ F 7 OCT 1998 18 true NA {'F2'} NA");
	mapIndividual.put(9, "Helen /Jones/ M 7 OCT 1998 19 true NA {'F2'} NA");

	
		Boolean flag=true;
		US01 userStory=new US01();
		SortedMap<Integer, String> birthday=userStory.birthday(mapIndividual);
		for (SortedMap.Entry entry : birthday.entrySet()) {
			if(!userStory.checkDate(entry.getValue().toString())){
				flag=false;
			}
		}
		Assert.assertTrue(flag);

	}
@Test
	public void testBirthdayList() throws FileNotFoundException, ParseException {
	SortedMap<Integer,String> mapIndividual=new TreeMap<>();

	mapIndividual.put(1, "Emily /Williams/ F 22 APR 1985 32 true NA {'F1'} {'F4'}");
	mapIndividual.put(2, "Bob /Williams/ M 19 OCT 1958 58 false 12 MAY 1995 NA {'F1'}");
	mapIndividual.put(3,"Emma /Davis/ F 18 APR 1960  57 true NA NA {'F2'}");
	mapIndividual.put(4, "Robert /Jones/ M 11 JUL 1960 56 true NA NA {'F2'}");
	mapIndividual.put(5, "Emily /Williams/ F 22 APR 1985 27 true NA {'F1'} {'F3'}");
	mapIndividual.put(6, "Helen /Jones/ F 7 OCT 1998 18 true NA {'F2'} NA");
	mapIndividual.put(9, "Helen /Jones/ M 7 OCT 1998 19 true NA {'F2'} NA");
	 SortedMap<Integer, String> birthdays = new TreeMap<>();
		 for (SortedMap.Entry entry : mapIndividual.entrySet()) {
			 String[] string = entry.getValue().toString().split(" ");
	         birthdays.put((Integer) entry.getKey(),string[3]+" "+string[4]+" "+string[5]);
	       }
		 US01 userStory=new US01();
			Assert.assertEquals(birthdays, userStory.birthday(mapIndividual));
		 
	}
@Test

	public void testInvalidDeathDays() throws FileNotFoundException, ParseException{
	SortedMap<Integer,String> mapIndividual=new TreeMap<>();

	mapIndividual.put(1, "Emily /Williams/ F 22 APR 1985 32 true NA {'F1'} {'F4'}");
	mapIndividual.put(2, "Bob /Williams/ M 19 OCT 1958 58 false 12 MAY 1995 NA {'F1'}");
	mapIndividual.put(3,"Emma /Davis/ F 18 APR 1960  57 true NA NA {'F2'}");
	mapIndividual.put(4, "Robert /Jones/ M 11 JUL 1960 56 true NA NA {'F2'}");
	mapIndividual.put(5, "Emily /Williams/ F 22 APR 1985 27 true NA {'F1'} {'F3'}");
	mapIndividual.put(6, "Helen /Jones/ F 7 OCT 1998 18 true NA {'F2'} NA");
	mapIndividual.put(9, "Helen /Jones/ M 7 OCT 1998 19 true NA {'F2'} NA");

		Boolean flag=true;
		US01 userStory=new US01();
		SortedMap<Integer, String> deathday=userStory.deathday(mapIndividual);
		for (SortedMap.Entry entry : deathday.entrySet()) {
			if(!userStory.checkDate(entry.getValue().toString())){
				flag=false;
			}
		}
		Assert.assertTrue(flag);

	}
@Test

	public void testDeathdayList() throws FileNotFoundException, ParseException {
	SortedMap<Integer,String> mapIndividual=new TreeMap<>();

	mapIndividual.put(1, "Emily /Williams/ F 22 APR 1985 32 true NA {'F1'} {'F4'}");
	mapIndividual.put(2, "Bob /Williams/ M 19 OCT 1958 58 false 12 MAY 1995 NA {'F1'}");
	mapIndividual.put(3,"Emma /Davis/ F 18 APR 1960  57 true NA NA {'F2'}");
	mapIndividual.put(4, "Robert /Jones/ M 11 JUL 1960 56 true NA NA {'F2'}");
	mapIndividual.put(5, "Emily /Williams/ F 22 APR 1985 27 true NA {'F1'} {'F3'}");
	mapIndividual.put(6, "Helen /Jones/ F 7 OCT 1998 18 true NA {'F2'} NA");
	mapIndividual.put(9, "Helen /Jones/ M 7 OCT 1998 19 true NA {'F2'} NA");
	 SortedMap<Integer, String> deathdays = new TreeMap<>();
		 for (SortedMap.Entry entry : mapIndividual.entrySet()) {
			 
			 String[] string = entry.getValue().toString().split(" ");
			 if(string.length>12)
	         deathdays.put((Integer) entry.getKey(),string[8]+" "+string[9]+" "+string[10]);
	       }
		 US01 userStory=new US01();
			Assert.assertEquals(deathdays, userStory.deathday(mapIndividual));
		 
	}
@Test

	public void testInvalidMarriage() throws FileNotFoundException, ParseException{
	SortedMap<Integer,String> mapFamily=new TreeMap<>();
	

	mapFamily.put(1, "22 JUL 1980 NA I2 Bob /Williams/ I3 Emma /Davis/ {'I1',I5'}");
	mapFamily.put(2, "2 JAN 1996 NA I4 Robert /Jones/ I3 Emma /Davis/ {'I6',I9'}");	
	Boolean flag=true;
		US01 userStory=new US01();
		SortedMap<Integer, String> marriage=userStory.marriage(mapFamily);
		for (SortedMap.Entry entry : marriage.entrySet()) {
			
			if(!userStory.checkDate(entry.getValue().toString())){
				flag=false;
			}
		}
		Assert.assertTrue(flag);

	}
	
@Test

	public void testMarriageList() throws FileNotFoundException, ParseException {
SortedMap<Integer,String> mapFamily=new TreeMap<>();
	

	mapFamily.put(1, "22 JUL 1980 NA I2 Bob /Williams/ I3 Emma /Davis/ {'I1',I5'}");
	mapFamily.put(2, "2 JAN 1996 NA I4 Robert /Jones/ I3 Emma /Davis/ {'I6',I9'}");	
	 SortedMap<Integer, String> marriage = new TreeMap<>();
		 for (SortedMap.Entry entry : mapFamily.entrySet()) {
			 String[] string = entry.getValue().toString().split(" ");
	         marriage.put((Integer) entry.getKey(),string[0]+" "+string[1]+" "+string[2]);
	       }
		 US01 userStory=new US01();
			Assert.assertEquals(marriage, userStory.marriage(mapFamily));
		
		 
	}
@Test

	public void testInvalidDivorce() throws FileNotFoundException, ParseException{
SortedMap<Integer,String> mapFamily=new TreeMap<>();
	

	mapFamily.put(1, "22 JUL 1980 NA I2 Bob /Williams/ I3 Emma /Davis/ {'I1',I5'}");
	mapFamily.put(2, "2 JAN 1996 NA I4 Robert /Jones/ I3 Emma /Davis/ {'I6',I9'}");	

		Boolean flag=true;
		US01 userStory=new US01();
		SortedMap<Integer, String> divorce=userStory.divorce(mapFamily);
		for (SortedMap.Entry entry : divorce.entrySet()) {
			if(!userStory.checkDate(entry.getValue().toString())){
				flag=false;
			}
		}
		Assert.assertTrue(flag);

	}
	
	
@Test

	public void testDivorceList() throws FileNotFoundException, ParseException {
SortedMap<Integer,String> mapFamily=new TreeMap<>();
	

	mapFamily.put(1, "22 JUL 1980 NA I2 Bob /Williams/ I3 Emma /Davis/ {'I1',I5'}");
	mapFamily.put(2, "2 JAN 1996 NA I4 Robert /Jones/ I3 Emma /Davis/ {'I6',I9'}");	
	 SortedMap<Integer, String> divorce = new TreeMap<>();
		 for (SortedMap.Entry entry : mapFamily.entrySet()) {
			 String[] string = entry.getValue().toString().split(" ");
			 if(!string[3].equals("NA"))
	         divorce.put((Integer) entry.getKey(),string[3]+" "+string[4]+" "+string[5]);
	       }
		 US01 userStory=new US01();
			Assert.assertEquals(divorce, userStory.divorce(mapFamily));
		 
	}
}
