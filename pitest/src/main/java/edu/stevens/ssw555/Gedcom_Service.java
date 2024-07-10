
// Refactored code.

package edu.stevens.ssw555;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Gedcom_Service {

	public static HashMap<String, Family> families = new HashMap<String, Family>();
	public static HashMap<String, Individual> individuals = new HashMap<String, Individual>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	private static String fileName = null;
	private static ArrayList<Individual> dupInd = new ArrayList<Individual>();
	private static ArrayList<Family> dupFam = new ArrayList<Family>();

	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("Please Enter the Input File Path with filename: ");
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			String fileName = bufferRead.readLine();
			createOutputFile();
			readAndParseFile(fileName);
			//printMaps();
			//UuserStory 3
			birthBeforeDeath(individuals);
			//UserStory 4
			Marriagebeforedivorce(individuals, families);
			//UserStory 8
			birthbeforemarriageofparent(individuals, families);
			//UserStory 16
			Malelastname(families);
			//UserStory 20
			AuntsandUnclesname(families);
			//UserStory 24
			uniqueFamilynameBySpouses(individuals, families);

		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found. Please reenter path");
			main(null);
		}
	}

	static void readAndParseFile(String fileName) throws IOException {

		String[] validTags = { "INDI", "NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "FAM", "MARR", "HUSB", "WIFE",
				"CHIL", "DIV", "DATE", "HEAD", "TRLR", "NOTE" };
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();


		while (line != null) {

			String[] parts = line.split(" ");

			if (Arrays.asList(validTags).contains(parts[1])) {
				line = br.readLine();
			} else {
				if (parts[0].equals("0") && Arrays.asList(validTags).contains(parts[2])) {

					if (parts[2].equals("INDI")) {
						Individual indi = new Individual(parts[1]);
						String individualParts = br.readLine();
						do {
							String[] indParts = individualParts.split(" ");
							if (indParts[1].equals("NAME"))
								indi.setName(indParts[2] + " " + indParts[3].substring(1, indParts[3].length() - 1));
							if (indParts[1].equals("SEX"))
								indi.setSex(indParts[2]);
							if (indParts[1].equals("FAMS"))
								indi.setSpouseOf(indParts[2]);
							if (indParts[1].equals("FAMC"))
								indi.setChildOf(indParts[2]);
							if (indParts[1].equals("BIRT")) {
								individualParts = br.readLine();
								indParts = individualParts.split(" ");
								String month = getMonth(indParts[3]);
								indi.setBirth(month + "/" + indParts[2] + "/" + indParts[4]);
							}
							if (indParts[1].equals("DEAT") && indParts[2].equals("Y")) {
								individualParts = br.readLine();
								indParts = individualParts.split(" ");
								String month = getMonth(indParts[3]);
								indi.setDeath(month + "/" + indParts[2] + "/" + indParts[4]);
							}
							individualParts = br.readLine();
						} while (!individualParts.startsWith("0"));
						line = individualParts;
						if (!individuals.containsKey(indi.getId())) {
							individuals.put(indi.getId(), indi);
						} else {
							dupInd.add(indi);
						}
					} else if (parts[2].equals("FAM")) {
						ArrayList<String> children = new ArrayList<String>();
						Family fam = new Family(parts[1]);
						String familyParts = br.readLine();
						do {
							String[] indFamParts = familyParts.split(" ");
							if (indFamParts[1].equals("HUSB"))
								fam.setHusb(indFamParts[2]);
							if (indFamParts[1].equals("WIFE"))
								fam.setWife(indFamParts[2]);
							if (indFamParts[1].equals("CHIL")) {
								children.add(indFamParts[2]);
								fam.setChild(children);
							}
							if (indFamParts[1].equals("MARR")) {
								familyParts = br.readLine();
								indFamParts = familyParts.split(" ");
								String month = getMonth(indFamParts[3]);
								fam.setMarriage(month + "/" + indFamParts[2] + "/" + indFamParts[4]);
							}
							if (indFamParts[1].equals("DIV")) {
								familyParts = br.readLine();
								indFamParts = familyParts.split(" ");
								String month = getMonth(indFamParts[3]);
								fam.setDivorce(month + "/" + indFamParts[2] + "/" + indFamParts[4]);
							}
							familyParts = br.readLine();
						} while (!familyParts.startsWith("0"));
						if (!families.containsKey(fam.getId())) {
							families.put(fam.getId(), fam);
						} else {
							dupFam.add(fam);
						}
						line = familyParts;
					}
				}
				else {

					line = br.readLine();
				}
			}
		}

		br.close();
	}

	/*public static void printMaps() throws FileNotFoundException, IOException {

		Map<String, Individual> indMap = new TreeMap<String, Individual>(individuals);
		Iterator<Map.Entry<String, Individual>> indEntries = indMap.entrySet().iterator();
		writeToFile(
				"INDIVIDUALS");
		System.out.println("\n");
		while (indEntries.hasNext()) {
			Map.Entry<String, Individual> indEntry = indEntries.next();
			Individual ind = indEntry.getValue();

			writeToFile(indEntry.getKey() + " Name: " + ind.getName() + ", Sex: " + ind.getSex() + ", DOB: "
					+ ind.getBirth() + ", DOD: " + ind.getDeath() + ", Spouse of: " + ind.getSpouseOf() + ", Child of: "
					+ ind.getChildOf());

		}


		Map<String, Family> famMap = new TreeMap<String, Family>(families);
		Iterator<Map.Entry<String, Family>> famEntries = famMap.entrySet().iterator();
		writeToFile(
				"FAMILIES");
		System.out.println("\n");
		while (famEntries.hasNext()) {
			Map.Entry<String, Family> famEntry = famEntries.next();
			Family fam = famEntry.getValue();
			writeToFile(famEntry.getKey() + " - Husband: " + fam.getHusb() + ", Wife: " + fam.getWife() + ", Children: "
					+ fam.getChild() + ", Marriage Date: " + fam.getMarriage() + ", Divorce Date: " + fam.getDivorce());

		}
		System.out.println("\n");

	}*/

	public static void createOutputFile() throws IOException {
		System.out.println("Please Enter Output File Path: ");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String fp = bufferRead.readLine();
		Path filePath = Paths.get(fp);
		if (Files.exists(filePath)) {
			fileName = "GedcomService_output.txt";
			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, false)))) {
			} catch (IOException e) {

			}
		} else {
			System.out.println("The Path You Entered Does Not Exist.Reenter path");
			createOutputFile();
		}
	}

	public static void writeToFile(String output) throws FileNotFoundException, IOException {
		fileName = "GedcomService_output.txt";

		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
			out.println(output);
			System.out.println(output);
		} catch (IOException e) {

		}

	}


	//UserStory 03 implementation
	//Refactored code for method

	static void birthBeforeDeath(HashMap<String, Individual> individuals) throws FileNotFoundException, IOException {

		Map<String, Individual> map = new HashMap<String, Individual>(individuals);
		Iterator<Map.Entry<String, Individual>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Individual> entry = entries.next();
			Individual indi = entry.getValue();

			Date date_of_birth = null;
			Date date_of_death = null;
			try {
				date_of_birth = sdf.parse(indi.getBirth());
				if (indi.getDeath() != null)
					date_of_death = sdf.parse(indi.getDeath());
				else
					date_of_death = null;
			} catch (ParseException e) {

				e.printStackTrace();
			}
			// Compare two dates
			if (date_of_death != null)
				if (date_of_birth.compareTo(date_of_death) > 0) {
					System.out.println("\n");
					writeToFile(
							"ERROR:INDIVIDUAL: User Story US03: Birth Before Death \nIndividual: "
									+ indi.getId() + " - " + indi.getName() + " was born after death\nDOB: "
									+ indi.getBirth() + " DOD: " + indi.getDeath()
									+ "\n");
				}
		}
	}
	//UserStory 04 implementation
	//Refactored code for method
	static void Marriagebeforedivorce(HashMap<String, Individual> individuals, HashMap<String, Family> families)
			throws FileNotFoundException, IOException {

		Date marriageDate = null;
		Date divorceDate = null;
		Map<String, Family> famMap = new HashMap<String, Family>(families);
		Map<String, Individual> indMap = new HashMap<String, Individual>(individuals);
		Iterator<Map.Entry<String, Family>> famEntries = famMap.entrySet().iterator();
		while (famEntries.hasNext()) {
			Map.Entry<String, Family> famEntry = famEntries.next();
			Family fam = famEntry.getValue();
			try {

				if (fam.getDivorce() != null) {
					divorceDate = sdf.parse(fam.getDivorce());
					if (fam.getMarriage() == null)
						System.out.println("marriage date is null");
					marriageDate = sdf.parse(fam.getMarriage());
					if (divorceDate.before(marriageDate)) {
						writeToFile(
								"ERROR:FAMILY: User Story US04: Marriage Before Divorce \nFamily: "
										+ fam.getId() + "\nIndividual: " + fam.getHusb() + ": "
										+ indMap.get(fam.getHusb()).getName() + fam.getWife() + ": "
										+ indMap.get(fam.getWife()).getName()
										+ " marriage date is before divorce date.\nMarriage Date: " + fam.getMarriage()
										+ " Divorce Date: " + fam.getDivorce()
										+ "\n");
					}
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	//UserStory 08 implementation
	static void birthbeforemarriageofparent(HashMap<String, Individual> individuals, HashMap<String, Family> families)
			throws FileNotFoundException, IOException {
		Date marriageDate = null;
		Date divorceDate = null;
		Date birthDate = null;
		Map<String, Individual> indMap = new HashMap<String, Individual>(individuals);
		Map<String, Family> famMap = new HashMap<String, Family>(families);
		Iterator<Map.Entry<String, Family>> famEntries = famMap.entrySet().iterator();
		while (famEntries.hasNext()) {
			Map.Entry<String, Family> famEntry = famEntries.next();
			Family fam = famEntry.getValue();
			try {
				marriageDate = sdf.parse(fam.getMarriage());
				if (fam.getChild() != null) {
					for (int i = 0; i < fam.getChild().size(); i++) {
						Individual indi = indMap.get(fam.getChild().get(i));
						birthDate = sdf.parse(indi.getBirth());
						if (birthDate.before(marriageDate)) {
							writeToFile(
									"ERROR: User Story US08: Birth Before Marriage Date \nFamily ID: "
											+ fam.getId() + "\nIndividual: " + indi.getId() + ": " + indi.getName()
											+ " Has been born before parents' marriage\nDOB: " + indi.getBirth()
											+ " Parents Marriage Date: " + fam.getMarriage()
											+ "\n\n");
						}
						if (fam.getDivorce() != null) {
							divorceDate = sdf.parse(fam.getDivorce());
							if (birthDate.after(divorceDate)) {
								writeToFile(
										"ERROR: User Story US08: Birth After Divorce Date\nFamily ID: "
												+ fam.getId() + "\nIndividual: " + indi.getId() + ": " + indi.getName()
												+ " Has been born after parents' divorce\nDOB: " + indi.getBirth()
												+ " Parents Divorce Date: " + fam.getDivorce()
												+ "\n\n");
							}
						}
					}
				}
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}
	}

	//UserStory 16 Implementation
	static void Malelastname(HashMap<String, Family> families) throws IOException {

		Map<String, Individual> map = new HashMap<String, Individual>(individuals);
		Map<String, Family> famMap = new HashMap<String, Family>(families);

		for (Map.Entry<String, Family> famEntry : famMap.entrySet()) {
			Family fam = famEntry.getValue();
			if (fam.getChild() != null && fam.getChild().size() > 1) {
				ArrayList<String> children = fam.getChild();
				String familyLastName = null;
				for (String childId : children) {
					Individual ind = individuals.get(childId);
					if ("M".equalsIgnoreCase(ind.getSex())) {
						String[] nameParts = ind.getName().split(" ");
						String lastName = nameParts.length > 1 ? nameParts[1] : "";
						if (familyLastName == null) {
							familyLastName = lastName;
						} else if (!familyLastName.equals(lastName)) {
							writeToFile("ERROR: User Story US16:Male last name \nFamily ID: " + fam.getId() +
									"   family members don't have same last name \n\n");

							break;
						}
					}
				}
			}
		}
	}

	//UserStory 20 Implementation
	static void AuntsandUnclesname(HashMap<String, Family> families)
			throws ParseException, FileNotFoundException, IOException {

		Map<String, Family> famMap = new HashMap<String, Family>(families);
		Iterator<Map.Entry<String, Family>> famEntries = famMap.entrySet().iterator();
		String fID = null;
		String mID = null;
		String motherFamilyID = null;
		String fatherFamilyID = null;
		ArrayList<String> auntsUncles = new ArrayList<String>();
		while (famEntries.hasNext()) {
			Map.Entry<String, Family> famEntry = famEntries.next();
			Family fam = famEntry.getValue();
			fID = fam.getHusb();
			mID = fam.getWife();
			Individual father = individuals.get(fID);
			Individual mother = individuals.get(mID);
			motherFamilyID = mother.getChildOf();
			fatherFamilyID = father.getChildOf();
			if (motherFamilyID != null) {
				Family motherFamily = families.get(motherFamilyID);
				if (motherFamily.getChild() != null && motherFamily.getChild().size() > 1) {
					auntsUncles.addAll(motherFamily.getChild());
				}
			}
			if (fatherFamilyID != null) {
				Family fatherFamily = families.get(fatherFamilyID);
				if (fatherFamily.getChild() != null && fatherFamily.getChild().size() > 1) {
					auntsUncles.addAll(fatherFamily.getChild());
				}
			}
			if (fam.getChild() != null) {
				ArrayList<String> children = fam.getChild();
				for (int i = 0; i < children.size(); i++) {
					Individual ind = individuals.get(children.get(i));
					String spouseOf = ind.getSpouseOf();
					if (spouseOf != null) {
						Family childFam = families.get(spouseOf);
						String spouse = childFam.getHusb();
						if (spouse.equals(ind.getId())) {
							spouse = childFam.getWife();
						}
						if (auntsUncles.contains(spouse)) {
							Individual incest = individuals.get(spouse);
							writeToFile("ERROR: User Story US20: Aunts and Uncles\nIndividual: "
									+ ind.getId() + " - " + ind.getName()
									+ " is married to either their aunt or uncle " + incest.getId() + " - "
									+ incest.getName()
									+ "\n\n");
						}
					}
				}

			}
		}

	}

	//UserStory 24 Implementation
	static void uniqueFamilynameBySpouses(HashMap<String, Individual> individuals, HashMap<String, Family> families)
			throws FileNotFoundException, IOException {

		Map<String, Individual> indMap = new HashMap<String, Individual>(individuals);
		Map<String, Family> famMap = new HashMap<String, Family>(families);
		Iterator<Map.Entry<String, Family>> famEntries = famMap.entrySet().iterator();
		while (famEntries.hasNext()) {
			Map.Entry<String, Family> famEntry = famEntries.next();
			Family fam = famEntry.getValue();
			Map<String, Family> famMap2 = new HashMap<String, Family>(families);
			Iterator<Map.Entry<String, Family>> famEntries2 = famMap2.entrySet().iterator();
			while (famEntries2.hasNext()) {
				Map.Entry<String, Family> famEntry2 = famEntries2.next();
				Family fam2 = famEntry2.getValue();
				if (fam.getHusb() != null && fam2.getHusb() != null && fam.getWife() != null
						&& fam2.getWife() != null) {
					if (fam.getHusb().equals(fam2.getHusb()) && fam.getWife().equals(fam2.getWife())
							&& fam.getMarriage().equals(fam2.getMarriage()) && fam.getId() != fam2.getId()) {
						writeToFile("ERROR: User Story US24: Unique Families By Spouse :\n"
								+ fam.getId()+ ": Husbund Name: " +indMap.get(fam.getHusb()).getName() + ",Wife Name: "+ indMap.get(fam.getWife()).getName() +" and " + fam2.getId()+ ": Husbund Name: " +indMap.get(fam.getHusb()).getName() + ",Wife Name: "+ indMap.get(fam.getWife()).getName() +"\n"
								+ " have same spouses and marriage dates :" +fam.getMarriage()
								+ "\n");
					}
				}
			}
		}
	}

	public static String getMonth(String month) {
		String numMonth = null;
		switch (month) {
			case "JAN":
				numMonth = "01";
				break;
			case "FEB":
				numMonth = "02";
				break;
			case "MAR":
				numMonth = "03";
				break;
			case "APR":
				numMonth = "04";
				break;
			case "MAY":
				numMonth = "05";
				break;
			case "JUN":
				numMonth = "06";
				break;
			case "JUL":
				numMonth = "07";
				break;
			case "AUG":
				numMonth = "08";
				break;
			case "SEP":
				numMonth = "09";
				break;
			case "OCT":
				numMonth = "10";
				break;
			case "NOV":
				numMonth = "11";
				break;
			case "DEC":
				numMonth = "12";
				break;
		}
		return numMonth;
	}
}