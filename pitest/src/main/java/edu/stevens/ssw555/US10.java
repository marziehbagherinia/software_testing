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

public class US10 {
	public ArrayList<String> checkMarriedAfter14(Map<String, Map<String, String>> mapIndividual,Map<String, Map<String, Object>> mapFamily) throws ParseException{
	ArrayList<String> individuals=new ArrayList<>();
	
	if(mapFamily==null||mapIndividual==null){
		return individuals;
	}
	
	
	for(Entry<String, Map<String, String>>  indEntry : mapIndividual.entrySet()){
		for(Entry<String, Map<String, Object>> famEntry : mapFamily.entrySet()){
	if(famEntry.getValue().get("husband").equals(indEntry.getKey())||famEntry.getValue().get("wife").equals(indEntry.getKey())){
		String[] marrDate=famEntry.getValue().get("married").toString().split(" ");
		String[] birthDate=indEntry.getValue().get("birth").split(" ");
		Date sdf = new SimpleDateFormat("MMM").parse(marrDate[1]);
         String month = new SimpleDateFormat("MM").format(sdf);

         LocalDate marriage = LocalDate.of(Integer.parseInt(marrDate[2]), Integer.parseInt(month), Integer.parseInt(marrDate[0]));
         Date sdf1 = new SimpleDateFormat("MMM").parse(birthDate[1]);
         String month1 = new SimpleDateFormat("MM").format(sdf);

         LocalDate birth = LocalDate.of(Integer.parseInt(birthDate[2]), Integer.parseInt(month), Integer.parseInt(birthDate[0]));
       
         Period p = Period.between(birth,marriage);
         String age = String.valueOf(p.getYears());
         
  if(Integer.parseInt(age)<14){
	  individuals.add(indEntry.getKey()+" "+famEntry.getKey().replace("@", "")+" "+age);
  }
		
	
	}
		
		
		}
	}
	
	
	
	return individuals;
	
	
	
	}
	
	public void printResult(ArrayList<String> individuals, BufferedWriter outFile) throws IOException{


		for(int j=0;j<individuals.size();j++){
			String data[]=individuals.get(j).split(" ");
		System.out.print("ERROR US10 Individual "+data[0]+" of family "+data[1]+" was "+data[2]+" years old when married");
		outFile.write("ERROR US10 Individual "+data[0]+" of family "+data[1]+" was "+data[2]+" years old when married");
		
			System.out.println();
		outFile.write("\n");
		}
	}
	public void us10(String gedcomInputFile)  throws ParseException, IOException{
		
		GedcomService gd=new GedcomService();
		gd.individualData(gedcomInputFile);
		gd.familyData(gedcomInputFile);
		Map<String, Map<String, String>> mapIndividual=gd.getIndividualAttributeMaps();
		Map<String, Map<String, Object>> mapFamily=gd.getFamilyAttributeMaps();
	ArrayList<String> result=checkMarriedAfter14(mapIndividual, mapFamily);
	BufferedWriter outFile = new BufferedWriter(new FileWriter("us10_output.txt"));

	if(result.size()>0){
	printResult(result,outFile);

		 }else{
				
				System.out.println("No individuals were under 14 when they got married");
				outFile.write("No individuals were under 14 when they got married");
			}
		outFile.flush();
		outFile.close();
		
	}

	
	
}
