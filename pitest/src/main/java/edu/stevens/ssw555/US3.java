package edu.stevens.ssw555;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class US3 {
    public SortedMap<Integer, String> birthdate = new TreeMap<>();
    public SortedMap<Integer, String> deathdate = new TreeMap<>();

    public void us03(String gedcomInputFile) throws FileNotFoundException, ParseException {


        GedcomService gd = new GedcomService();
        SortedMap<Integer, String> mapIndividual = gd.individualData(gedcomInputFile);


        birthdate = birthday(mapIndividual);
        deathdate = deathday(mapIndividual);


    }

    public SortedMap<Integer, String> birthday(SortedMap<Integer, String> mapIndividual) {
        SortedMap<Integer, String> birthdate = new TreeMap<>();
        for (SortedMap.Entry entry : mapIndividual.entrySet()) {
            String[] string = entry.getValue().toString().split(" ");
            birthdate.put((Integer) entry.getKey(), string[3] + " " + string[4] + " " + string[5]);
        }
        return birthdate;
    }

    public SortedMap<Integer, String> deathday(SortedMap<Integer, String> mapIndividual) {
        SortedMap<Integer, String> deathdate = new TreeMap<>();

        for (SortedMap.Entry entry : mapIndividual.entrySet()) {
            String[] string = entry.getValue().toString().split(" ");
            if (string.length > 12) {

                deathdate.put((Integer) entry.getKey(), string[8] + " " + string[9] + " " + string[10]);
            }
        }
        return deathdate;
    }


    public boolean checkDate(String date) throws ParseException {
        boolean flag = false;
        String str[] = date.split(" ");
        Date sdf = new SimpleDateFormat("MMM").parse(str[1]);
        String month = new SimpleDateFormat("MM").format(sdf);

        LocalDate date1 = LocalDate.of(Integer.parseInt(str[2]), Integer.parseInt(month), Integer.parseInt(str[0]));
        LocalDate now = LocalDate.now();
        if (now.isAfter(date1)) {
            flag = true;
        }
        return flag;
    }

    public void printData(US3 userStory) throws ParseException, IOException {
        int count1 = 0;
        int count2 = 0;
        //int count3=0;

        //int count4=0;
        BufferedWriter outFile = new BufferedWriter(new FileWriter("US03_output.txt"));
        outFile.write("output of User story 3:");
        outFile.write("\n");

        for (SortedMap.Entry entry : userStory.birthdate.entrySet()) {
            if (!userStory.checkDate(entry.getValue().toString())) {

                count1++;
            }

        }
        if (count1 > 0) {
            System.out.print("ERROR: INDIVIDUAL: US3: Invalid birthdate: ");
            outFile.write("Invalid birthdate");
            outFile.write("\n");


            for (SortedMap.Entry entry : userStory.birthdate.entrySet()) {
                if (!userStory.checkDate(entry.getValue().toString())) {
                    System.out.println("I" + entry.getKey() + " " + entry.getValue());
                    outFile.write("I" + entry.getKey() + " " + entry.getValue());
                    outFile.write("\n");

                }

            }

        } else {
            System.out.println("All the birthdate are valid");
            outFile.write("All the birthdate are valid");
            outFile.write("\n");

        }


        for (SortedMap.Entry entry : userStory.deathdate.entrySet()) {
            if (!userStory.checkDate(entry.getValue().toString())) {
                count2++;
            }

        }
        if (count2 > 0) {
            System.out.print("ERROR: INDIVIDUAL: US3: Invalid death date: ");
            outFile.write("Invalid deathdate");
            for (SortedMap.Entry entry : userStory.deathdate.entrySet()) {
                if (!userStory.checkDate(entry.getValue().toString())) {
                    System.out.println("I" + entry.getKey() + " " + entry.getValue());
                    outFile.write("I" + entry.getKey() + " " + entry.getValue());
                    outFile.write("\n");

                }

            }

        } else {


            System.out.println("All the deathdate are valid");
            outFile.write("All the deathdate are valid");

            outFile.write("\n");

        }


        outFile.flush();
        outFile.close();
    }

}

