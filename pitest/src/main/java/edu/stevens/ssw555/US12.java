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

public class US12 {
	public ArrayList<String> checkParentsNotOld(Map<String, Map<String, String>> mapIndividual,Map<String, Map<String, Object>> mapFamily) throws ParseException{
	ArrayList<String> individuals=new ArrayList<>();
	
	if(mapFamily==null||mapIndividual==null){
		return individuals;
	}
	
	
		for(Entry<String, Map<String, Object>> famEntry : mapFamily.entrySet()){
			
			String husband=famEntry.getValue().get("husband").toString();
			String wife=famEntry.getValue().get("wife").toString();
			String husbAge="";
			String wifeAge="";

			if(!famEntry.getValue().get("children").equals("")){
		String children[]=famEntry.getValue().get("children").toString().replace("[", "").replace("]", "").replace(" ", "").split(",");
		for(Entry<String, Map<String, String>> indEntry:mapIndividual.entrySet()){
			if(indEntry.getKey().equals(husband)){
				husbAge=indEntry.getValue().get("age");
			}
			if(indEntry.getKey().equals(wife)){
				wifeAge=indEntry.getValue().get("age");
			}
		}
		
		for(Entry<String, Map<String, String>> indEntry:mapIndividual.entrySet()){
			
			for(int i=0;i<children.length;i++){
				if(indEntry.getKey().equals(children[i])){
					
					String age=indEntry.getValue().get("age");
					if(Integer.parseInt(wifeAge)-Integer.parseInt(age)<60 && Integer.parseInt(husbAge)-Integer.parseInt(age)<80){
						
					}
					else{
						individuals.add(famEntry.getKey()+" "+indEntry.getKey()+" "+age+" "+wifeAge+" "+husbAge);
					}
			
		}
		
		
			}
		
		}
		}
		}
	
	return individuals;
	
	
	
	}
	
	public void printResult(ArrayList<String> individuals, BufferedWriter outFile) throws IOException{


		for(int j=0;j<individuals.size();j++){
			String data[]=individuals.get(j).split(" ");
			int difMother=Integer.parseInt(data[3])-Integer.parseInt(data[2]);
			int difFather=Integer.parseInt(data[4])-Integer.parseInt(data[2]);
			data[0]=data[0].replace("@", "");
			if(difMother>=60&&difFather>=80){
		System.out.print("ERROR US12 Individual "+data[1]+" of family "+data[0]+" has age difference with mother of "+difMother+" years and age difference with father of "+difFather+" years");
		outFile.write("ERROR US12 Individual "+data[1]+" of family "+data[0]+" has age difference with mother of "+difMother+" years and age difference with father of "+difFather+" years");}
			
			if(difMother>=60&&difFather<80){
				
			
				System.out.print("ERROR US12 Individual "+data[1]+" of family "+data[0]+" has age difference with mother of "+difMother+" years");
				outFile.write("ERROR US12 Individual "+data[1]+" of family "+data[0]+" has age difference with mother of "+difMother+" years");
				
		
			}
					
			if(difMother<60&&difFather>=80){
				
				System.out.print("ERROR US12 Individual "+data[1]+" of family "+data[0]+" has age difference with father of "+difFather+" years");
				outFile.write("ERROR US12 Individual "+data[1]+" of family "+data[0]+" has age difference with father of "+difFather+" years");
				
			}
					
								
			
		
			System.out.println();
		outFile.write("\n");
		}
	}
	public void us12(String gedcomInputFile)  throws ParseException, IOException{
		
		GedcomService gd=new GedcomService();
		gd.individualData(gedcomInputFile);
		gd.familyData(gedcomInputFile);
		Map<String, Map<String, String>> mapIndividual=gd.getIndividualAttributeMaps();
		Map<String, Map<String, Object>> mapFamily=gd.getFamilyAttributeMaps();
	ArrayList<String> result=checkParentsNotOld(mapIndividual, mapFamily);
	BufferedWriter outFile = new BufferedWriter(new FileWriter("us12_output.txt"));

	if(result.size()>0){
	printResult(result,outFile);

		 }else{
				
				System.out.println("US 12 No individuals have age difference with their mother as 60 or more and age difference with father as 80 or more");
				outFile.write("US 12 No individuals have age difference with their mother as 60 or more and age difference with father as 80 or more");
			}
		outFile.flush();
		outFile.close();
		
	}

	
	
}
