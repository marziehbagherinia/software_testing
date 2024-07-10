package edu.stevens.ssw555;

import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

import org.junit.Assert;
import edu.stevens.ssw555.US25;

public class TestUS25 {
@Test
public void testUniqueNameNBday(){
	SortedMap<Integer,String> mapFamily=new TreeMap<>();
	SortedMap<Integer,String> mapIndividual=new TreeMap<>();
	US25 userStory=new US25();
	mapFamily.put(1, "22 JUL 1980 NA I2 Bob /Williams/ I3 Emma /Davis/ {'I1',I5'}");
	//mapFamily.put(2, "2 JAN 1996 NA I4 Robert /Jones/ I3 Emma /Davis/ {'I6',I9'}");
	mapIndividual.put(1, "Emily /Williams/ F 22 APR 1985 32 true NA {'F1'} {'F4'}");
	mapIndividual.put(2, "Bob /Williams/ M 19 OCT 1958 58 false 12 MAY 1995 NA {'F1'}");
	mapIndividual.put(3,"Emma /Davis/ F 18 APR 1960  57 true NA NA {'F2'}");
	mapIndividual.put(4, "Robert /Jones/ M 11 JUL 1960 56 true NA NA {'F2'}");
	mapIndividual.put(5, "Emmet /Williams/ M 19 MAY 1990 27 true NA {'F1'} {'F3'}");
	Assert.assertEquals(userStory.checkUniqueNameNBday(mapIndividual, mapFamily).size(),0);
	
}

@Test
public void testNotUniqueNameNBdayOne(){
	SortedMap<Integer,String> mapFamily=new TreeMap<>();
	SortedMap<Integer,String> mapIndividual=new TreeMap<>();
	US25 userStory=new US25();
	mapFamily.put(1, "22 JUL 1980 NA I2 Bob /Williams/ I3 Emma /Davis/ {'I1',I5'}");
	//mapFamily.put(2, "2 JAN 1996 NA I4 Robert /Jones/ I3 Emma /Davis/ {'I6',I9'}");
	mapIndividual.put(1, "Emily /Williams/ F 22 APR 1985 32 true NA {'F1'} {'F4'}");
	mapIndividual.put(2, "Bob /Williams/ M 19 OCT 1958 58 false 12 MAY 1995 NA {'F1'}");
	mapIndividual.put(3,"Emma /Davis/ F 18 APR 1960  57 true NA NA {'F2'}");
	mapIndividual.put(4, "Robert /Jones/ M 11 JUL 1960 56 true NA NA {'F2'}");
	mapIndividual.put(5, "Emily /Williams/ F 22 APR 1985 27 true NA {'F1'} {'F3'}");
	Assert.assertTrue(userStory.checkUniqueNameNBday(mapIndividual, mapFamily).size()==1);

}
@Test
public void testNotUniqueNameNBdayMulti(){
	SortedMap<Integer,String> mapFamily=new TreeMap<>();
	SortedMap<Integer,String> mapIndividual=new TreeMap<>();
	US25 userStory=new US25();
	mapFamily.put(1, "22 JUL 1980 NA I2 Bob /Williams/ I3 Emma /Davis/ {'I1',I5'}");
	mapFamily.put(2, "2 JAN 1996 NA I4 Robert /Jones/ I3 Emma /Davis/ {'I6',I9'}");
	mapIndividual.put(1, "Emily /Williams/ F 22 APR 1985 32 true NA {'F1'} {'F4'}");
	mapIndividual.put(2, "Bob /Williams/ M 19 OCT 1958 58 false 12 MAY 1995 NA {'F1'}");
	mapIndividual.put(3,"Emma /Davis/ F 18 APR 1960  57 true NA NA {'F2'}");
	mapIndividual.put(4, "Robert /Jones/ M 11 JUL 1960 56 true NA NA {'F2'}");
	mapIndividual.put(5, "Emily /Williams/ F 22 APR 1985 27 true NA {'F1'} {'F3'}");
	mapIndividual.put(6, "Helen /Jones/ F 7 OCT 1998 18 true NA {'F2'} NA");
	mapIndividual.put(9, "Helen /Jones/ M 7 OCT 1998 19 true NA {'F2'} NA");
	Assert.assertTrue(userStory.checkUniqueNameNBday(mapIndividual, mapFamily).size()>1);

}


@Test
public void testUniqueNameNBdayEmpty(){
	SortedMap<Integer,String> mapFamily=new TreeMap<>();
	SortedMap<Integer,String> mapIndividual=new TreeMap<>();
	US25 userStory=new US25();
	
	Assert.assertTrue(userStory.checkUniqueNameNBday(mapIndividual, mapFamily).size()==0);

}
@Test
public void testUniqueNameNBdayNullInput(){

	US25 userStory=new US25();
	
	Assert.assertTrue(userStory.checkUniqueNameNBday(null, null).size()==0);

}


}
