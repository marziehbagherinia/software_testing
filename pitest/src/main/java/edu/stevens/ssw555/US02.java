package edu.stevens.ssw555;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

public class US02 {
public ArrayList<String> checkBirthBeforeMarr(Map<String, Map<String, String>> mapIndividual,Map<String, Map<String, Object>> mapFamily) throws ParseException{
	
	ArrayList<String> individuals=new ArrayList<>();
	
	
	
	if(mapFamily==null||mapIndividual==null)
	{
		return individuals;
	}
	
	for(Entry<String, Map<String, String>> indEntry : mapIndividual.entrySet()){
		for(Entry<String, Map<String, Object>> famEntry : mapFamily.entrySet()){
			 String[] bdayString=indEntry.getValue().get("birth").toString().split(" ");
			 String[] marrString=famEntry.getValue().get("married").toString().split(" ");
			 Date sdf1 = new SimpleDateFormat("MMM").parse(bdayString[1]);
		     String month1 = new SimpleDateFormat("MM").format(sdf1);
		     Date sdf2 = new SimpleDateFormat("MMM").parse(marrString[1]);
		     String month2 = new SimpleDateFormat("MM").format(sdf2);
		     LocalDate bday = LocalDate.of(Integer.parseInt(bdayString[2]), Integer.parseInt(month1), Integer.parseInt(bdayString[0]));
		     LocalDate marr = LocalDate.of(Integer.parseInt(marrString[2]), Integer.parseInt(month2), Integer.parseInt(marrString[0]));

		    
			

if((indEntry.getKey()).equals(famEntry.getValue().get("husband"))||(indEntry.getKey()).equals(famEntry.getValue().get("wife"))){
	
	if(bday.isAfter(marr)){
		if(!individuals.contains(indEntry.getKey()+" "+indEntry.getValue().get("birth")+" "+famEntry.getValue().get("married"))){
		individuals.add((indEntry.getKey()+" "+indEntry.getValue().get("birth")+" "+famEntry.getValue().get("married")));}
	}
	
}



		




	

		}
	}
return individuals;	
}



public void printResult(ArrayList<String> individuals, BufferedWriter outFile) throws IOException{
	//System.out.println(individuals);


	for(int j=0;j<individuals.size();j++){
		String[] ind=individuals.get(j).split(" ");
	System.out.print("ERROR US02 Individual "+ind[0]+" was married on "+ind[4]+" "+ind[5]+" "+ind[6]+" and born on "+
			ind[1]+" "+ind[2]+" "+ind[3]+" which is after marriage");
	outFile.write("ERROR US02 Individual "+ind[0]+" was married on "+ind[4]+" "+ind[5]+" "+ind[6]+" and born on "+
		ind[1]+" "+ind[2]+" "+ind[3]+" which is after marriage");
	System.out.println();
	outFile.write("\n");
	}
}

public void us02(String gedcomInputFile)  throws ParseException, IOException{
	
	GedcomService gd=new GedcomService();
	gd.individualData(gedcomInputFile);
	gd.familyData(gedcomInputFile);
	Map<String, Map<String, String>> mapIndividual=	gd.getIndividualAttributeMaps()
;
	Map<String, Map<String, Object>> mapFamily=gd.getFamilyAttributeMaps();
	
ArrayList<String> result=checkBirthBeforeMarr(mapIndividual, mapFamily);
BufferedWriter outFile = new BufferedWriter(new FileWriter("us02_output.txt"));

if(result.size()>0){
printResult(result,outFile);

	 }else{
			
			System.out.println("All individuals wre born before there marriage");
			outFile.write("All individuals wre born before there marriage");
		}
	outFile.flush();
	outFile.close();
	
}





}
