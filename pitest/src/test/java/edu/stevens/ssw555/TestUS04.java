 package edu.stevens.ssw555;


import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Assert;

import junit.framework.*;
import junit.framework.TestCase;

public class TestUS04 extends TestCase {

		

	public void testmarriagedays() throws FileNotFoundException, ParseException{
		
		
		SortedMap<Integer,String> mapFamily=new TreeMap<>();
		

		mapFamily.put(1, "22 JUL 1980 NA I2 Bob /Williams/ I3 Emma /Davis/ {'I1',I5'}");
		mapFamily.put(2, "2 JAN 1996 NA I4 Robert /Jones/ I3 Emma /Davis/ {'I6',I9'}");	

			Boolean flag=true;
			US4 userstory_1_1=new US4();
			SortedMap<Integer, String> marriagedays=userstory_1_1.marriagedays(mapFamily);
			for (SortedMap.Entry entry : marriagedays.entrySet()) {
				if(!userstory_1_1.checkDate(entry.getValue().toString())){
					flag=false;
				}
			}
			Assert.assertTrue(flag);

		
		
	}
	
	public void testCheckDate() throws ParseException{
		US4 userstory_1_1=new US4();
		Boolean flag=userstory_1_1.checkDate("20 JUN 1990");
		Assert.assertTrue(flag);
	}
	
	
	public void testInvalidmarriagedays() throws FileNotFoundException, ParseException{
		SortedMap<Integer,String> mapFamily=new TreeMap<>();
		

		mapFamily.put(1, "22 JUL 1980 NA I2 Bob /Williams/ I3 Emma /Davis/ {'I1',I5'}");
		mapFamily.put(2, "2 JAN 1996 NA I4 Robert /Jones/ I3 Emma /Davis/ {'I6',I9'}");	
		Boolean flag=true;
			US4 userstory_1=new US4();
			SortedMap<Integer, String> marriagedays=userstory_1.marriagedays(mapFamily);
			for (SortedMap.Entry entry : marriagedays.entrySet()) {
				
				if(!userstory_1.checkDate(entry.getValue().toString())){
					flag=false;
				}
			}
			Assert.assertTrue(flag);

	

	}
	public void testmarriagedaysList() throws FileNotFoundException, ParseException {
		
		SortedMap<Integer,String> mapFamily=new TreeMap<>();
		
		mapFamily.put(1, "22 JUL 1980 NA I2 Bob /Williams/ I3 Emma /Davis/ {'I1',I5'}");
		mapFamily.put(2, "2 JAN 1996 NA I4 Robert /Jones/ I3 Emma /Davis/ {'I6',I9'}");	
		 SortedMap<Integer, String> marriagedays = new TreeMap<>();
			 for (SortedMap.Entry entry : mapFamily.entrySet()) {
				 String[] string = entry.getValue().toString().split(" ");
		         marriagedays.put((Integer) entry.getKey(),string[0]+" "+string[1]+" "+string[2]);
		       }
			 US4 userstory_1_1=new US4();
				Assert.assertEquals(marriagedays, userstory_1_1.marriagedays(mapFamily));
			
		 
	}
	public void testInvaliddivorcedays() throws FileNotFoundException, ParseException{
		SortedMap<Integer,String> mapFamily=new TreeMap<>();
		

		mapFamily.put(1, "22 JUL 1980 NA I2 Bob /Williams/ I3 Emma /Davis/ {'I1',I5'}");
		mapFamily.put(2, "2 JAN 1996 NA I4 Robert /Jones/ I3 Emma /Davis/ {'I6',I9'}");	

			Boolean flag=true;
			US4 userstory_1=new US4();
			SortedMap<Integer, String> divorcedays=userstory_1.divorcedays(mapFamily);
			for (SortedMap.Entry entry : divorcedays.entrySet()) {
				if(!userstory_1.checkDate(entry.getValue().toString())){
					flag=false;
				}
			}
			Assert.assertTrue(flag);

	}
	
	
}
