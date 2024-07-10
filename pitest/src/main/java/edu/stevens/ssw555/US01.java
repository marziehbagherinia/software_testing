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

public class US01 {
    public SortedMap<Integer, String> birthdays = new TreeMap<>();
    public SortedMap<Integer, String> deathdays = new TreeMap<>();
    public SortedMap<Integer, String> marriage = new TreeMap<>();
    public SortedMap<Integer, String> divorce = new TreeMap<>();

    public void us01(String gedcomInputFile) throws FileNotFoundException, ParseException {


        GedcomService gd = new GedcomService();
        SortedMap<Integer, String> mapIndividual = gd.individualData(gedcomInputFile);
        SortedMap<Integer, String> mapFamily = gd.familyData(gedcomInputFile);

        birthdays = birthday(mapIndividual);
        deathdays = deathday(mapIndividual);
        marriage = marriage(mapFamily);
        divorce = divorce(mapFamily);


    }

    public SortedMap<Integer, String> birthday(SortedMap<Integer, String> mapIndividual) {
        SortedMap<Integer, String> birthdays = new TreeMap<>();
        for (SortedMap.Entry entry : mapIndividual.entrySet()) {
            String[] string = entry.getValue().toString().split(" ");
            birthdays.put((Integer) entry.getKey(), string[3] + " " + string[4] + " " + string[5]);
        }
        return birthdays;
    }

    public SortedMap<Integer, String> deathday(SortedMap<Integer, String> mapIndividual) {
        SortedMap<Integer, String> deathdays = new TreeMap<>();

        for (SortedMap.Entry entry : mapIndividual.entrySet()) {
            String[] string = entry.getValue().toString().split(" ");
            if (string.length > 12) {

                deathdays.put((Integer) entry.getKey(), string[8] + " " + string[9] + " " + string[10]);
            }
        }
        return deathdays;
    }

    public SortedMap<Integer, String> marriage(SortedMap<Integer, String> mapFamily) {
        SortedMap<Integer, String> marriage = new TreeMap<>();

        for (SortedMap.Entry entry : mapFamily.entrySet()) {
            String[] string = entry.getValue().toString().split(" ");
            marriage.put((Integer) entry.getKey(), string[0] + " " + string[1] + " " + string[2]);

        }
        return marriage;
    }


    public SortedMap<Integer, String> divorce(SortedMap<Integer, String> mapFamily) {
        SortedMap<Integer, String> divorce = new TreeMap<>();

        for (SortedMap.Entry entry : mapFamily.entrySet()) {
            String[] string = entry.getValue().toString().split(" ");
            if (!string[3].equals("NA")) {

                divorce.put((Integer) entry.getKey(), string[3] + " " + string[4] + " " + string[5]);
            }
        }
        return divorce;
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

    public void printData(US01 userStory) throws ParseException, IOException {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        int count4 = 0;
        BufferedWriter outFile = new BufferedWriter(new FileWriter("us01_output.txt"));
        outFile.write("User story 1");
        outFile.write("\n");

        for (SortedMap.Entry entry : userStory.birthdays.entrySet()) {
            if (!userStory.checkDate(entry.getValue().toString())) {

                count1++;
            }

        }
        if (count1 > 0) {
            outFile.write("Invalid birthdays");
            outFile.write("\n");


            for (SortedMap.Entry entry : userStory.birthdays.entrySet()) {
                if (!userStory.checkDate(entry.getValue().toString())) {
                    System.out.print("ERROR: INDIVIDUAL: US1: Invalid Birthday: ");
                    System.out.println("I" + entry.getKey() + " " + entry.getValue());
                    outFile.write("I" + entry.getKey() + " " + entry.getValue());
                    outFile.write("\n");

                }

            }

        } else {
            System.out.println("All the birthdays are valid");
            outFile.write("All the birthdays are valid");
            outFile.write("\n");

        }


        for (SortedMap.Entry entry : userStory.deathdays.entrySet()) {
            if (!userStory.checkDate(entry.getValue().toString())) {
                count2++;
            }

        }
        if (count2 > 0) {
            outFile.write("Invalid deathdays");
            for (SortedMap.Entry entry : userStory.deathdays.entrySet()) {
                if (!userStory.checkDate(entry.getValue().toString())) {
                    System.out.print("ERROR: INDIVIDUAL: US1: Invalid Day of Death: ");
                    System.out.println("I" + entry.getKey() + " " + entry.getValue());
                    outFile.write("I" + entry.getKey() + " " + entry.getValue());
                    outFile.write("\n");

                }

            }

        } else {


            System.out.println("All the deathdays are valid");
            outFile.write("All the deathdays are valid");

            outFile.write("\n");

        }


        for (SortedMap.Entry entry : userStory.marriage.entrySet()) {
            if (!userStory.checkDate(entry.getValue().toString())) {
                count3++;
            }

        }
        if (count3 > 0) {
            outFile.write("Invalid marriages");
            for (SortedMap.Entry entry : userStory.marriage.entrySet()) {
                if (!userStory.checkDate(entry.getValue().toString())) {
                    System.out.print("ERROR: INDIVIDUAL: US1: Invalid Marriage Day: ");
                    System.out.println("F" + entry.getKey() + " " + entry.getValue());
                    outFile.write("F" + entry.getKey() + " " + entry.getValue());
                    outFile.write("\n");


                }

            }
        } else {
            System.out.println("All the marriages are valid");
            outFile.write("All the marriages are valid");
            outFile.write("\n");


        }


        for (SortedMap.Entry entry : userStory.divorce.entrySet()) {
            if (!userStory.checkDate(entry.getValue().toString())) {
                count4++;
            }

        }
        if (count4 > 0) {
            outFile.write("Invalid divorces");
            outFile.write("\n");

            for (SortedMap.Entry entry : userStory.divorce.entrySet()) {
                if (!userStory.checkDate(entry.getValue().toString())) {
                    System.out.print("ERROR: INDIVIDUAL: US1: Invalid Divorce Day: ");
                    System.out.println("F" + entry.getKey() + " " + entry.getValue());

                    outFile.write("F" + entry.getKey() + " " + entry.getValue());
                }

            }

        } else {
            System.out.println("All the divorces are valid");
            outFile.write("All the divorces are valid");
            outFile.write("\n");

        }

        outFile.flush();
        outFile.close();
    }

}

