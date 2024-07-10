package edu.stevens.ssw555;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class GedcomService {

    private Map<String, Map<String, String>> individualAttributeMaps = new HashMap<>();
    private Map<String, Map<String, Object>> familyAttributeMaps = new HashMap<>();

    private SortedMap<Integer, String> map;
    SortedMap<Integer, String> mapFamily;

    public GedcomService(String gedcomInputFile) throws Exception {
        map = individualData(gedcomInputFile);
        mapFamily = familyData(gedcomInputFile);
    }

    public GedcomService() {
    }

    public static void main(String[] args) {
        try {
            String gedcomInputFile = promptUserForGedcomFilePath();
            GedcomService gedcomService = new GedcomService(gedcomInputFile);


            gedcomService.runApplication(gedcomInputFile);


            US3 userStory = new US3();
            userStory.birthdate = new TreeMap<>();
            userStory.deathdate = new TreeMap<>();
            userStory.us03(gedcomInputFile);


            US4 userStory_1 = new US4();
            userStory_1.marriagedays = new TreeMap<>();
            userStory_1.divorcedays = new TreeMap<>();

            userStory_1.us04(gedcomInputFile);

            System.out.println();
            userStory.printData(userStory);
            System.out.println();
            userStory_1.printData(userStory_1);

            US01 userStory1 = new US01();
            userStory1.birthdays = new TreeMap<>();
            userStory1.deathdays = new TreeMap<>();
            userStory1.marriage = new TreeMap<>();
            userStory1.divorce = new TreeMap<>();

            userStory1.us01(gedcomInputFile);

            System.out.println();
            userStory1.printData(userStory1);
            System.out.println();
            US25 userStory25 = new US25();
            userStory25.us25(gedcomInputFile);

            US02 userStory02 = new US02();
            userStory02.us02(gedcomInputFile);
            

            US31 userStory31 = new US31();
            userStory31.us31(gedcomInputFile);

            
            
            US10 userStory10 = new US10();
            userStory10.us10(gedcomInputFile);
            
            US21 userStory21 = new US21();
            userStory21.us21(gedcomInputFile);
            

            US12 userStory12 = new US12();
            userStory12.us12(gedcomInputFile);
            
            US13 userStory13 = new US13();
            userStory13.us13(gedcomInputFile);
            

            DeathValidations deathValidations = new DeathValidations();
            deathValidations.printDeceasedFamilyMemberList(
                    deathValidations.listDeceasedFamilyMembers(gedcomService.getIndividualAttributeMaps()),
                    gedcomService.getIndividualAttributeMaps());

            FamilyValidations familyValidations = new FamilyValidations();
            familyValidations.printNoMoreThanFiveSameBirthDaysinFamilyViolations(
                    familyValidations.validateNoMoreThanFiveSameBirthDaysinFamily(gedcomService.getFamilyAttributeMaps(),
                            gedcomService.getIndividualAttributeMaps()));

            MarriageValidations marriageValidations = new MarriageValidations();
            marriageValidations.printLivingAndMarried(
                    marriageValidations.getLivingAndMarried(gedcomService.getIndividualAttributeMaps()));

            marriageValidations.printInvalidDivorces(marriageValidations.validateDivorceBeforeDeath(gedcomService.getFamilyAttributeMaps(),
                    gedcomService.getIndividualAttributeMaps()));
            
            for(String msg : deathValidations.validateMarriagesBeforeDeath(gedcomService.getFamilyAttributeMaps(), gedcomService.getIndividualAttributeMaps())) {
                System.out.println(msg);
            }

            for(String msg : familyValidations.fewerThanFifteenChildren(gedcomService.getFamilyAttributeMaps())) {
                System.out.println(msg);
            }

            System.out.println("");
            System.exit(0);

        } catch (Exception ex) {
            System.err.println("Error running Gedcom Application");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public SortedMap<Integer, String> individualData(String gedcomInputFile) throws FileNotFoundException, ParseException {

        Scanner scan = new Scanner(new FileReader(gedcomInputFile));

        String reader = scan.nextLine();


        SortedMap<Integer, String> map = new TreeMap<>();


        ArrayList<String> gedcomeData = new ArrayList<>();
        while (scan.hasNextLine()) {
            gedcomeData.add(reader);
            reader = scan.nextLine();
        }

        for (int i = 0; i < gedcomeData.size(); i++) {
            String str[] = gedcomeData.get(i).split(" ");
            if (str[1].startsWith("@I") && str[0].equals("0") && str[2].equals("INDI")) {

                String individualFullId = str[1];
                String indvidual = str[1].replace("@", "").replace("I", "");
                String name = "";
                String gender = "";
                String birth = "";
                String age = "";
                String alive = "true";
                String death = "NA";
                String child = "NA";
                String spouse = "NA";
                int j = i + 1;
                while (!gedcomeData.get(j).startsWith("0")) {
                    str = gedcomeData.get(j).split(" ");

                    if (str[0].equals("1") && str[1].equals("NAME")) {
                        name = str[2] + " " + str[3];
                    }

                    if (str[0].equals("1") && str[1].equals("SEX")) {
                        gender = str[2];
                    }
                    if (str[0].equals("1") && str[1].equals("BIRT")) {

                        str = gedcomeData.get(j + 1).split(" ");
                        if (str[0].equals("2") && str[1].equals("DATE")) {
                            birth = str[2] + " " + str[3] + " " + str[4];
                            Date sdf = new SimpleDateFormat("MMM").parse(str[3]);
                            String month = new SimpleDateFormat("MM").format(sdf);

                            LocalDate birthdate = LocalDate.of(Integer.parseInt(str[4]), Integer.parseInt(month), Integer.parseInt(str[2]));
                            LocalDate now = LocalDate.now();
                            Period p = Period.between(birthdate, now);
                            age = String.valueOf(p.getYears());
                        }
                    }
                    if (str[0].equals("1") && str[1].equals("DEAT")) {
                        alive = "false";

                        str = gedcomeData.get(j + 1).split(" ");
                        if (str[0].equals("2") && str[1].equals("DATE")) {
                            death = str[2] + " " + str[3] + " " + str[4];
                        }
                    }
                    if (str[0].equals("1") && str[1].equals("FAMC")) {
                        child = "{'" + str[2].replace("@", "") + "'}";
                    }

                    if (str[0].equals("1") && str[1].equals("FAMS")) {
                        spouse = "{'" + str[2].replace("@", "") + "'}";
                    }

                    j++;
                }

                map.put(Integer.parseInt(indvidual), name + " " + gender + " " + birth + " " + age + " " + alive + " " + death + " " + child + " " + spouse);
                individualAttributeMaps.put(individualFullId.replace("@", ""), makeIndividualAttributeMap(name, gender, birth, age, alive, death, child, spouse));
            }

        }

        return map;


    }

    public SortedMap<Integer, String> familyData(String gedcomInputFile) throws FileNotFoundException, ParseException {

        Scanner scan = new Scanner(new FileReader(gedcomInputFile));

        String reader = scan.nextLine();

        ArrayList<String> gedcomeData = new ArrayList<>();
        while (scan.hasNextLine()) {
            gedcomeData.add(reader);
            reader = scan.nextLine();
        }
        SortedMap<Integer, String> mapFamily = new TreeMap<>();
        SortedMap<Integer, String> map = individualData(gedcomInputFile);

        for (int i = 0; i < gedcomeData.size(); i++) {
            String str[] = gedcomeData.get(i).split(" ");
            if (gedcomeData.get(i).contains("FAM") && gedcomeData.get(i).startsWith("0")) {
                String str1[] = gedcomeData.get(i).split(" ");

                String familyFullId = str[1];
                String family = str1[1].replace("@", "").replace("F", "");
                String marr = "";
                String div = "NA";
                String husb = "";
                String wife = "";
                ArrayList<String> chil = new ArrayList<>();
                int k = i + 1;
                while (!gedcomeData.get(k).startsWith("0") && k < gedcomeData.size() - 1) {
                    str1 = gedcomeData.get(k).split(" ");
                    if (str1[0].equals("1") && str1[1].equals("HUSB")) {
                        husb = str1[2].replace("@", "");
                    }
                    if (str1[0].equals("1") && str1[1].equals("WIFE")) {
                        wife = str1[2].replace("@", "");
                    }
                    if (str1[0].equals("1") && str1[1].equals("CHIL")) {
                        chil.add(str1[2]);
                    }
                    if (str1[0].equals("1") && str1[1].equals("MARR")) {
                        str1 = gedcomeData.get(
                                k + 1).split(" ");
                        if (str1[0].equals("2") && str1[1].equals("DATE")) {
                            marr = str1[2] + " " + str1[3] + " " + str1[4];
                        }
                    }

                    if (str1[0].equals("1") && str1[1].equals("DIV")) {
                        str1 = gedcomeData.get(k + 1).split(" ");
                        if (str1[0].equals("2") && str1[1].equals("DATE")) {
                            div = str1[2] + " " + str1[3] + " " + str1[4];
                        }
                    }
                    k++;

                }
                String children = "";
                String husbName1[] = map.get(Integer.parseInt(husb.substring(1))).split(" ");
                String husbName = husbName1[0] + " " + husbName1[1];
                String wifeName1[] = map.get(Integer.parseInt(wife.substring(1))).split(" ");
                for (int l = 0; l < chil.size(); l++) {

                    if (chil.size() == 1) {
                        children = children.concat("{'" + chil.get(l) + "'}").replace("@", "");

                    } else if (chil.size() == 2) {
                        if (l == 0) {
                            children = children.concat("{'" + chil.get(l) + "',").replace("@", "");
                        } else
                            children = children.concat(chil.get(l) + "'}").replace("@", "");

                    } else if (l == 0) {
                        children = children.concat("{'" + chil.get(l) + "',").replace("@", "");
                    } else if (l == chil.size() - 1) {
                        children = children.concat("'" + chil.get(l) + "'}").replace("@", "");
                    } else {
                        children = children.concat(chil.get(l) + "',").replace("@", "");
                    }
                }

                String wifeName = wifeName1[0] + " " + wifeName1[1];
                mapFamily.put(Integer.parseInt(family), marr + " " + div + " " + husb + " " + husbName + " " + wife + " " + wifeName + " "
                        + children);
                familyAttributeMaps.put(familyFullId, makeFamilyAttributeMap(marr, div, husb, husbName, wife, wifeName, children));
            }
        }
        return mapFamily;
    }

    public void runApplication(String gedcomInputFile) throws Exception {


        SortedMap<Integer, String> map = individualData(gedcomInputFile);
        SortedMap<Integer, String> mapFamily = familyData(gedcomInputFile);


        Object[][] table1 = new Object[map.size()][];
        int count = 0;
        for (SortedMap.Entry entry : map.entrySet()) {
            String[] string = entry.getValue().toString().split(" ");
            if (string.length > 11) {
                table1[count] = new String[]{"I" + entry.getKey(), string[0] + " " + string[1], string[2], string[3] + " " + string[4] + " " + string[5], string[6],
                        string[7], string[8] + " " + string[9] + " " + string[10], string[11], string[12]};
            } else {
                table1[count] = new String[]{"I" + entry.getKey(), string[0] + " " + string[1], string[2], string[3] + " " + string[4] + " " + string[5], string[6], string[7], string[8], string[9], string[10]};
            }
            count++;

        }

        Object[][] table2 = new Object[mapFamily.size()][];
        int count1 = 0;
        for (SortedMap.Entry entry : mapFamily.entrySet()) {
            String[] string = entry.getValue().toString().split(" ");
            
            if(string.length==11){
            table2[count1] = new String[]{"F" + entry.getKey(), string[0] + " " + string[1] + " " + string[2], 
            		string[3], string[4], string[5] + " " + string[6], string[7], string[8] + " " + string[9], string[10]};
            }
            else  if(string.length<11){
                table2[count1] = new String[]{"F" + entry.getKey(), string[0] + " " + string[1] + " " + string[2], 
                		string[3], string[4], string[5] + " " + string[6], string[7], string[8] + " " + string[9],"NA"};
                }
            else{
                table2[count1] = new String[]{"F" + entry.getKey(), string[0] + " " 
            + string[1] + " " + string[2], string[3]+" "+ string[4]+" "+string[5] , string[6], 
            string[7]+" "+ string[8] , string[9],string[10]+" "+string[11],string[12]};

                
            	
            }
            count1++;
        }

        printGedcomOutputConsole(table1, table2);
        printGedcomOutputFile(table1, table2);
    }

    //Print output of people & family from GEDCOM input to console
    private void printGedcomOutputConsole(Object[][] table1, Object[][] table2) {
        System.out.format("%-5s %-20s %-10s %-12s %-10s %-10s %-20s %-10s %-10s \n", "ID", "NAME", "GENDER", "BIRTHDAY", "AGE", "ALIVE", "DEATH", "CHILD", "SPOUSE");
        System.out.format("%-5s %-20s %-10s %-12s %-10s %-10s %-20s %-10s %-10s \n", "==", "====", "======", "========", "===", "=====", "=====", "=====", "======");
        for (Object[] row : table1) {
            System.out.format("%-5s %-20s %-10s %-12s %-10s %-10s %-20s %-10s %-10s \n", row);
        }
        System.out.println();
        System.out.format("%-5s %-20s %-10s %-12s %-20s %-10s %-20s %-10s \n", "ID", "MARRIED", "DIVORCED", "HUSBAND ID",
                "HUSBAND NAME", "WIFE ID", "WIFE NAME", "CHILDREN");
        System.out.format("%-5s %-20s %-10s %-12s %-20s %-10s %-20s %-10s \n", "==", "=======", "========", "==========",
                "============", "=======", "=========", "========");
        for (Object[] row : table2) {
            System.out.format("%-5s %-20s %-10s %-12s %-20s %-10s %-20s %-10s  \n", row);
        }
    }

    //Print txt output of people & family from GEDCOM input
    private void printGedcomOutputFile(Object[][] table1, Object[][] table2) throws Exception {
        BufferedWriter outFile = new BufferedWriter(new FileWriter("gedcom_output.txt"));
        try {
            outFile.write(String.format("%-5s %-20s %-10s %-12s %-10s %-10s %-20s %-10s %-10s \n", "ID", "NAME", "GENDER", "BIRTHDAY", "AGE", "ALIVE", "DEATH", "CHILD", "SPOUSE"));
            outFile.write(String.format("%-5s %-20s %-10s %-12s %-10s %-10s %-20s %-10s %-10s \n", "==", "====", "======", "========", "===", "=====", "=====", "=====", "======"));
            outFile.write("\n");
            for (Object[] row : table1) {
                outFile.write(String.format("%-5s %-20s %-10s %-12s %-10s %-10s %-20s %-10s %-10s \n", row));
            }

            outFile.write(String.format("%-5s %-20s %-10s %-12s %-20s %-10s %-20s %-10s \n", "ID", "MARRIED", "DIVORCED", "HUSBAND ID",
                    "HUSBAND NAME", "WIFE ID", "WIFE NAME", "CHILDREN"));
            outFile.write(String.format("%-5s %-20s %-10s %-12s %-20s %-10s %-20s %-10s \n", "==", "=======", "========", "==========",
                    "============", "=======", "=========", "========"));

            for (Object[] row : table2) {
                outFile.write(String.format("%-5s %-20s %-10s %-12s %-20s %-10s %-20s %-10s  \n", row));
            }

        } catch (Exception ex) {
            System.err.println("Error writing output file from Gedcom");
        } finally {
            outFile.flush();
            outFile.close();
        }
    }

    //Process input from user
    public static String promptUserForGedcomFilePath() {
        String gedcomFilePath;
        boolean invalidFilePath = true;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter location of Gedcom file: ");
            gedcomFilePath = scanner.next();
            if (gedcomFilePath == null && gedcomFilePath.isEmpty() || !Files.exists(Paths.get(gedcomFilePath))) {
                invalidFilePath = true;
            } else {
                invalidFilePath = false;
            }
        } while (invalidFilePath);
        return gedcomFilePath;
    }

    public Map<String, String> makeIndividualAttributeMap(String name, String gender, String birth, String age, String alive, String death, String child, String spouse) {
        Map<String, String> attributeMap = new HashMap<>();
        attributeMap.put("name", name);
        attributeMap.put("gender", gender);
        attributeMap.put("birth", birth);
        attributeMap.put("age", age);
        attributeMap.put("alive", alive);
        attributeMap.put("death", death);
        attributeMap.put("child", child);
        attributeMap.put("spouse", spouse);
        return attributeMap;
    }

    public Map<String, Object> makeFamilyAttributeMap(String married, String divorced, String husband, String husbandName, String wife, String wifeName, String children) {
        Map<String, Object> attributeMap = new HashMap<>();
        attributeMap.put("married", married);
        attributeMap.put("divorced", divorced);
        attributeMap.put("husband", husband);
        attributeMap.put("husbandName", husbandName);
        attributeMap.put("wife", wife);
        attributeMap.put("wifeName", wifeName);
        if(children.equals("")){
            attributeMap.put("children", children);

        }
        else{
            attributeMap.put("children", makeChildrenList(children));

        }
        return attributeMap;
    }

    public List<String> makeChildrenList(String children) {
        List<String> childrenIdList = new ArrayList<>();
        String cleanedChildrenIdString = children.replace("{", "")
                .replace("}", "")
                .replace("'", "");
        for (String id : cleanedChildrenIdString.split(",")) {
            childrenIdList.add(id);
        }
        return childrenIdList;
    }


    public Map<String, Map<String, String>> getIndividualAttributeMaps() {
        return this.individualAttributeMaps;
    }

    public Map<String, Map<String, Object>> getFamilyAttributeMaps() {
        return this.familyAttributeMaps;
    }

}
