package edu.stevens.ssw555;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class US4 {
    public SortedMap<Integer, String> marriagedays=new TreeMap<>();
    public static SortedMap<Integer, String> divorcedays=new TreeMap<>();


    public  void us04(String gedcomInputFile) throws FileNotFoundException, ParseException{



        GedcomService gd=new GedcomService();

        SortedMap<Integer, String> mapFamily=gd.familyData(gedcomInputFile);

        marriagedays=marriagedays(mapFamily);
        divorcedays=divorcedays(mapFamily);



    }

    public SortedMap<Integer, String> marriagedays(SortedMap<Integer, String> mapFamily){
        SortedMap<Integer, String> marriagedays = new TreeMap<>();
        for (SortedMap.Entry entry : mapFamily.entrySet()) {
            String[] string = entry.getValue().toString().split(" ");
            marriagedays.put((Integer) entry.getKey(),string[0]+" "+string[1]+" "+string[2]);
        }
        return marriagedays;
    }

    public SortedMap<Integer, String> divorcedays(SortedMap<Integer, String> mapFamily){
        SortedMap<Integer, String> divorcedays = new TreeMap<>();

        for (SortedMap.Entry entry : mapFamily.entrySet()) {
            String[] string = entry.getValue().toString().split(" ");
            if(string.length>12){

                divorcedays.put((Integer) entry.getKey(), string[3]+" "+string[4]+" "+string[5]);
            }
        }
        return divorcedays;
    }



    public boolean checkDate(String date) throws ParseException{
        boolean flag=false;
        String str[]=date.split(" ");
        Date sdf = new SimpleDateFormat("MMM").parse(str[1]);
        String month = new SimpleDateFormat("MM").format(sdf);

        LocalDate marriagedate = LocalDate.of(Integer.parseInt(str[2]), Integer.parseInt(month), Integer.parseInt(str[0]));
        LocalDate divorcedate = LocalDate.now();
        if(divorcedate.isAfter(marriagedate)){
            flag=true;
        }
        return flag;
    }
    public void printData(US4 userStory_1) throws ParseException, IOException{
        int count1=0;
        int count2=0;

        BufferedWriter outFile = new BufferedWriter(new FileWriter("US04_output.txt"));
        outFile.write("Output of user story 4:");
        outFile.write("\n");

        for (SortedMap.Entry entry : userStory_1.marriagedays.entrySet()) {
            if(!userStory_1.checkDate(entry.getValue().toString())){

                count1++;
            }

        }if(count1>0){
            outFile.write("Invalid marriagedates");
            outFile.write("\n");




            for (SortedMap.Entry entry : userStory_1.marriagedays.entrySet()) {
                if(!userStory_1.checkDate(entry.getValue().toString())){
                    System.out.print("ERROR: FAMILY: US4: Invalid Marriage Date: ");
                    System.out.println("F"+entry.getKey()+" "+entry.getValue());
                    outFile.write("F"+entry.getKey()+" "+entry.getValue());
                    outFile.write("\n");

                }

            }

        }
        else{
            System.out.println("All the marriagedays are valid");
            outFile.write("All the marriagedays are valid");
            outFile.write("\n");

        }



        for (SortedMap.Entry entry : userStory_1.divorcedays.entrySet()) {
            if(!userStory_1.checkDate(entry.getValue().toString())){
                count2++;
            }

        }
        if(count2>0){
            outFile.write("Invalid divorcedates");
            for (SortedMap.Entry entry : userStory_1.divorcedays.entrySet()) {
                if(!userStory_1.checkDate(entry.getValue().toString())){
                    System.out.print("ERROR: FAMILY: US4: Invalid Divorce Date: ");
                    System.out.println("F"+entry.getKey()+" "+entry.getValue());
                    outFile.write("F"+entry.getKey()+" "+entry.getValue());
                    outFile.write("\n");

                }

            }

        }
        else{


            System.out.println("All divorcedates are valid");
            outFile.write("All divorcedates are valid");

            outFile.write("\n");

        }



        outFile.flush();
        outFile.close();
    }

}