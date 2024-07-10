package edu.stevens.ssw555;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

public class US31 {
	public ArrayList<String> checkNeverMarried(Map<String, Map<String, String>> mapIndividual,Map<String, Map<String, Object>> mapFamily) throws ParseException{
	ArrayList<String> individuals=new ArrayList<>();
	
	if(mapFamily==null||mapIndividual==null){
		return individuals;
	}
	
	Integer count[]=new Integer[mapIndividual.size()];
	int index=0;
	for(int i=0;i<mapIndividual.size();i++){
		count[i]=0;
	}
	for(Entry<String, Map<String, String>>  indEntry : mapIndividual.entrySet()){
		for(Entry<String, Map<String, Object>> famEntry : mapFamily.entrySet()){
	if(famEntry.getValue().get("husband").equals(indEntry.getKey())||famEntry.getValue().get("wife").equals(indEntry.getKey())){
		count[index]=count[index]+1;
	}
		
		
		}
		index++;
	}
	
	int index1=0;
	for(Entry<String, Map<String, String>> indEntry : mapIndividual.entrySet()){
		String[] indBday=indEntry.getValue().get("birth").split(" ");
	if(count[index1]==0){
		
		 Date sdf1 = new SimpleDateFormat("MMM").parse(indBday[1]);
	     String month1 = new SimpleDateFormat("MM").format(sdf1);
	     LocalDate bday = LocalDate.of(Integer.parseInt(indBday[2]), Integer.parseInt(month1), Integer.parseInt(indBday[0]));
	     LocalDate now=LocalDate.now();
	     Period p = Period.between(bday, now);
	     if(p.getYears()>30 && indEntry.getValue().get("death").equals("NA")){
	    	 individuals.add(indEntry.getKey());
	    	 
	     }
		
	}
	index1++;
	}
	
	return individuals;
	
	
	
	}
	
	public void printResult(ArrayList<String> individuals, BufferedWriter outFile) throws IOException{


		for(int j=0;j<individuals.size();j++){
			
		System.out.print("ERROR US31 Individual "+individuals.get(j)+" is over 30 and never been married");
		outFile.write("ERROR US31 Individual "+individuals.get(j)+" is over 30 and never been married");
		
			System.out.println();
		outFile.write("\n");
		}
	}
	public void us31(String gedcomInputFile)  throws ParseException, IOException{
		
		GedcomService gd=new GedcomService();
		gd.individualData(gedcomInputFile);
		gd.familyData(gedcomInputFile);
		Map<String, Map<String, String>> mapIndividual=gd.getIndividualAttributeMaps();
		Map<String, Map<String, Object>> mapFamily=gd.getFamilyAttributeMaps();
	ArrayList<String> result=checkNeverMarried(mapIndividual, mapFamily);
	BufferedWriter outFile = new BufferedWriter(new FileWriter("us31_output.txt"));

	if(result.size()>0){
	printResult(result,outFile);

		 }else{
				
				System.out.println("No individuals are over 30 and never been married");
				outFile.write("No individuals are over 30 and never been married");
			}
		outFile.flush();
		outFile.close();
		
	}

	
	
}
