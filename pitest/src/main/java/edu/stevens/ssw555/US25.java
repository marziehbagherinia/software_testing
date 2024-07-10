package edu.stevens.ssw555;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.SortedMap;

public class US25 {


	
//this method is slpit into two methods checkUniqueNameNBday and printResult
public ArrayList<String> checkUniqueNameNBday(SortedMap<Integer, String> mapIndividual,SortedMap<Integer, String> mapFamily){
	
	boolean flag=true;
	ArrayList<String> individuals=new ArrayList<>();
	if(mapFamily==null||mapIndividual==null){
		return individuals;
	}
	 for (SortedMap.Entry entry : mapFamily.entrySet()) {//for each family
		 String[] family=entry.getValue().toString().split(" ");
		
		 
	 
		
		 int index=0;
			
		 if(family.length==11){//if no divorce date is specified
		index=10;	 
			 }
		 if(family.length==13){//if  divorce date is specified
			 index=12;
		 }
	
		//duplicate code is replaced with one single code
		if(family[index].length()>4){//if there are more than one children in family
			 String children[]=family[index].split(",");
		
			 for(int i=0;i<children.length;i++){
				 for(int j=0;j<children.length;j++){
				 String childString=mapIndividual.get(Integer.parseInt(children[i].replace("I", "").
						 replace("{", "").replace("'", "").replace("}", ""))).toString();
				 String[] child=childString.split(" ");
				 String bday=child[3]+" "+child[4]+" "+child[5];
				 String stringChild1=mapIndividual.get(Integer.parseInt(children[j].replace("I", "").
						 replace("{", "").replace("'", "").replace("}", ""))).toString();
				 String[] child1=stringChild1.split(" ");
				 String bday1=child1[3]+" "+child1[4]+" "+child1[5];
					 if(child[0].equals(child1[0])&&bday.equals(bday1) &&i!=j){//compare the name birthday of children in the family
					if(individuals.contains(("F"+entry.getKey()+" "+"I"+children[i].replace("I", "").
							 replace("{", "").replace("'", "").replace("}", "")+" "+"I"+children[j].replace("I", "").
							 replace("{", "").replace("'", "").replace("}", "")))||individuals.contains("F"+entry.getKey()+" "+"I"+children[j].replace("I", "").
							 replace("{", "").replace("'", "").replace("}", "")+" "+"I"+children[i].replace("I", "").
							 replace("{", "").replace("'", "").replace("}", ""))){}
					
					
					else{
individuals.add("F"+entry.getKey()+" "+"I"+children[i].replace("I", "").
		 replace("{", "").replace("'", "").replace("}", "")+" "+"I"+children[j].replace("I", "").
		 replace("{", "").replace("'", "").replace("}", ""));

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
		String[] ind=individuals.get(j).split(" ");
	System.out.print("ERROR US25 Family "+ind[0]+" has Individuals ");
	outFile.write("ERROR US25 Family "+ind[0]+" has Individuals ");
	for(int i=1;i<ind.length;i++)
	{
		System.out.print(ind[i]);
		outFile.write(ind[i]);
		System.out.print(", ");
		outFile.write(", ");
	}
	System.out.print("with same name and birthday");
	outFile.write("with same name and birthday");
	System.out.println();
	outFile.write("\n");
	}
}
public void us25(String gedcomInputFile)  throws ParseException, IOException{
	
	GedcomService gd=new GedcomService();
	SortedMap<Integer, String> mapIndividual=gd.individualData(gedcomInputFile);
	SortedMap<Integer, String> mapFamily=gd.familyData(gedcomInputFile);
ArrayList<String> result=checkUniqueNameNBday(mapIndividual, mapFamily);
BufferedWriter outFile = new BufferedWriter(new FileWriter("us25_output.txt"));

if(result.size()>0){
printResult(result,outFile);

	 }else{
			
			System.out.println("Name and birthday of children are unique");
			outFile.write("Name and birthday of children are unique");
		}
	outFile.flush();
	outFile.close();
	
}

}
