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

public class US21 {
	public ArrayList<String> checkMaleFemale(Map<String, Map<String, String>> mapIndividual,Map<String, Map<String, Object>> mapFamily) throws ParseException{
	ArrayList<String> individuals=new ArrayList<>();
	
	if(mapFamily==null||mapIndividual==null){
		return individuals;
	}
	
	
	for(Entry<String, Map<String, String>>  indEntry : mapIndividual.entrySet()){
		for(Entry<String, Map<String, Object>> famEntry : mapFamily.entrySet()){
	if(famEntry.getValue().get("husband").equals(indEntry.getKey())){
		
		if(!indEntry.getValue().get("gender").equals("M")){
			individuals.add(indEntry.getKey()+" "+famEntry.getKey().replace("@", "")+" M");
		}
	}
         
 if(famEntry.getValue().get("wife").equals(indEntry.getKey()))	
	 if(!indEntry.getValue().get("gender").equals("F")){
			individuals.add(indEntry.getKey()+" "+famEntry.getKey().replace("@", "")+" F");
		}
	
 {
	 
 }
		
		}
	}
	
	
	
	return individuals;
	
	
	
	}
	
	public void printResult(ArrayList<String> individuals, BufferedWriter outFile) throws IOException{


		for(int j=0;j<individuals.size();j++){
			String data[]=individuals.get(j).split(" ");
			if(data[2].equals("M")){
		System.out.print("ERROR US21 Individual "+data[0]+" is husband in family "+data[1]+" but is not male");
		
		outFile.write("ERROR US21 Individual "+data[0]+" is husband in family "+data[1]+" but is not male");
		}
			else{
				System.out.print("ERROR US10 Individual "+data[0]+" is wife in family "+data[1]+" but is not female");
			outFile.write("ERROR US10 Individual "+data[0]+" is wife in family "+data[1]+" but is not female");
			}
			
	
			System.out.println();
		outFile.write("\n");
		}
	}
	public void us21(String gedcomInputFile)  throws ParseException, IOException{
		
		GedcomService gd=new GedcomService();
		gd.individualData(gedcomInputFile);
		gd.familyData(gedcomInputFile);
		Map<String, Map<String, String>> mapIndividual=gd.getIndividualAttributeMaps();
		Map<String, Map<String, Object>> mapFamily=gd.getFamilyAttributeMaps();
	ArrayList<String> result=checkMaleFemale(mapIndividual, mapFamily);
	BufferedWriter outFile = new BufferedWriter(new FileWriter("us21_output.txt"));

	if(result.size()>0){
	printResult(result,outFile);

		 }else{
				
				System.out.println("All family have husbands ad male and wife as female");
				outFile.write("All family have husbands ad male and wife as female");
			}
		outFile.flush();
		outFile.close();
		
	}

	
	
}
