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

public class US13 {
	public ArrayList<String> checkSiblingSpacing(Map<String, Map<String, String>> mapIndividual,Map<String, Map<String, Object>> mapFamily) throws ParseException{
	ArrayList<String> individuals=new ArrayList<>();
	
	if(mapFamily==null||mapIndividual==null){
		return individuals;
	}
	
	
		for(Entry<String, Map<String, Object>> famEntry : mapFamily.entrySet()){
			

		String children[]=famEntry.getValue().get("children").toString().replace("[", "").replace("]", "").replace(" ", "").split(",");
		
		ArrayList<String> children1=new ArrayList<String>();
		for(Entry<String, Map<String, String>> indEntry:mapIndividual.entrySet()){
			
			for(int i=0;i<children.length;i++){
				if(indEntry.getKey().equals(children[i])){
					
					String bday=indEntry.getValue().get("birth");
						children1.add(indEntry.getKey()+":"+bday);
					}
					
			
		}
		
		
			}
		
		
		for(int i=0;i<children1.size();i++){
			
		for(int j=0;j<children1.size();j++){
			
			
		
			if(i==j||children1.get(i).equals(children1.get(j))){
				
			}
			else{
				
				String child1[]=children1.get(i).split(":");
				String child2[]=children1.get(j).split(":");
				Date sdf = new SimpleDateFormat("MMM").parse(child1[1].split(" ")[1]);
		         String month = new SimpleDateFormat("MM").format(sdf);

		         LocalDate bday1 = LocalDate.of(Integer.parseInt(child1[1].split(" ")[2]), Integer.parseInt(month), Integer.parseInt(child1[1].split(" ")[0]));
		         Date sdf1 = new SimpleDateFormat("MMM").parse(child2[1].split(" ")[1]);
		         String month1 = new SimpleDateFormat("MM").format(sdf1);

		         LocalDate bday2 = LocalDate.of(Integer.parseInt(child2[1].split(" ")[2]), Integer.parseInt(month1), Integer.parseInt(child2[1].split(" ")[0]));
		         Period p = Period.between(bday1,bday2);
		         if(p.getYears()==0){
                    if(p.getMonths()>8)	{
                    	
                    	//System.out.println(p);

                    }
                    else{
                    	if(p.getMonths()==0 && p.getDays()<2){
                    		
                    	}
                    	else{
                    	if(individuals.contains(famEntry.getKey()+" "+child1[0]+" "+child2[0])||individuals.contains(famEntry.getKey()+" "+child2[0]+" "+child1[0]))
                    	{
                    		
                    	}
                    	else{

                    	individuals.add(famEntry.getKey()+" "+child1[0]+" "+child2[0]);}
                    }
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
				data[0]=data[0].replace("@", "");

		System.out.print("ERROR US13 Sibilings "+data[1]+" and "+data[2]+" of family "+data[0]+" do not have their birth dates more than 8 months apart or less than 2 days apart");
		outFile.write("ERROR US13 Sibilings "+data[1]+" and "+data[2]+" of family "+data[0]+" do not have their birth dates more than 8 months apart or less than 2 days apart");
		
		
		
		
			
			
					
								
			
		
			System.out.println();
		outFile.write("\n");
	}
	}
	public void us13(String gedcomInputFile)  throws ParseException, IOException{
		
		GedcomService gd=new GedcomService();
		gd.individualData(gedcomInputFile);
		gd.familyData(gedcomInputFile);
		Map<String, Map<String, String>> mapIndividual=gd.getIndividualAttributeMaps();
		Map<String, Map<String, Object>> mapFamily=gd.getFamilyAttributeMaps();
	ArrayList<String> result=checkSiblingSpacing(mapIndividual, mapFamily);
	BufferedWriter outFile = new BufferedWriter(new FileWriter("us13_output.txt"));

	if(result.size()>0){
	printResult(result,outFile);

		 }else{
				
				System.out.println("US 13 No two siblings were born before 9 months and more than 1 day apart");
				outFile.write("US 13 No two siblings were born before 9 months and more than 1 day apart");
			}
		outFile.flush();
		outFile.close();
		
	}

	
	
}
