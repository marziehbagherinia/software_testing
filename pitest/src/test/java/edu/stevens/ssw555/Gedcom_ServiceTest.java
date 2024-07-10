package edu.stevens.ssw555;

import java.util.HashMap;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class Gedcom_ServiceTest {

	private static ByteArrayOutputStream outContent;
	private static PrintStream originalOut;
	private static HashMap<String, Individual> testIndividuals = new HashMap<String, Individual>();
	private static HashMap<String, Family> testFamilies = new HashMap<String, Family>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	@BeforeAll
    static void setup()
	{
		originalOut = System.out;

		// Birth Before Death
		Individual ind1 = new Individual("I1");
		ind1.setName("User3");
		ind1.setSex("M");
		ind1.setBirth("01/01/1990");
		ind1.setDeath("01/01/1980");

		testIndividuals.put("I1", ind1);

		// 2) Marriage Before Divorce
		Individual husband2 = new Individual("I2_1");
		husband2.setName("John Doe");
		husband2.setSex("M");
		husband2.setBirth("01/01/1970");

		Individual wife2 = new Individual("I2_2");
		wife2.setName("Jane Doe");
		wife2.setSex("F");
		wife2.setBirth("01/01/1972");

		testIndividuals.put("I2_1", husband2);
		testIndividuals.put("I2_2", wife2);

		Family family2 = new Family("F2");
		family2.setHusb("I2_1");
		family2.setWife("I2_2");
		family2.setMarriage("01/01/2000");
		family2.setDivorce("01/01/1999");

		testFamilies.put("F2", family2);

		// 3) Birth Before Marriage Of Parent
		Individual husband3 = new Individual("I3_1");
		husband3.setName("John Doe");
		husband3.setSex("M");
		husband3.setBirth("01/01/1970");

		Individual wife3 = new Individual("I3_2");
		wife3.setName("Jane Doe");
		wife3.setSex("F");
		wife3.setBirth("01/01/1972");

		Individual child3 = new Individual("I3_3");
		child3.setName("Child Doe");
		child3.setSex("M");
		child3.setBirth("01/01/1995"); // Child born before parents' marriage

		testIndividuals.put("I3_1", husband3);
		testIndividuals.put("I3_2", wife3);
		testIndividuals.put("I3_3", child3);

		Family family3 = new Family("F3");
		family3.setHusb("I3_1");
		family3.setWife("I3_2");
		family3.setMarriage("01/01/2000");

		ArrayList<String> children = new ArrayList<>();
		children.add("I3_3");
		family3.setChild(children);

		testFamilies.put("F3", family3);

		// 4) Male last name
		Individual husband4 = new Individual("I4_1");
		husband4.setName("John Doe");
		husband4.setSex("M");
		husband4.setBirth("01/01/1970");

		Individual wife4 = new Individual("I4_2");
		wife4.setName("Jane Doe");
		wife4.setSex("F");
		wife4.setBirth("01/01/1972");

		Individual child4_1 = new Individual("I4_3");
		child4_1.setName("Child Doe");
		child4_1.setSex("M");
		child4_1.setBirth("01/01/2000");

		Individual child4_2 = new Individual("I4_4");
		child4_2.setName("Child Doe2");
		child4_2.setSex("M");
		child4_2.setBirth("01/01/2002");

		testIndividuals.put("I4_1", husband4);
		testIndividuals.put("I4_2", wife4);
		testIndividuals.put("I4_3", child4_1);
		testIndividuals.put("I4_4", child4_2);

		Family family4 = new Family("F4");
		family4.setHusb("I4_1");
		family4.setWife("I4_2");
		family4.setMarriage("01/01/1998");
		ArrayList<String> children4 = new ArrayList<>();
		children4.add("I4_3");
		children4.add("I4_4");
		family4.setChild(children4);

		testFamilies.put("F4", family4);

		// 5) Aunts And Uncles Name
		Individual grandfather5 = new Individual("I5_1");
		grandfather5.setName("Grandfather Doe");
		grandfather5.setSex("M");
		grandfather5.setBirth("01/01/1970");

		Individual grandmother5 = new Individual("I5_2");
		grandmother5.setName("Grandmother Doe");
		grandmother5.setSex("F");
		grandmother5.setBirth("01/01/1975");

		Individual father5 = new Individual("I5_3");
		father5.setName("Father Doe");
		father5.setSex("M");
		father5.setChildOf("F5_1"); // Family with grandparents
		father5.setBirth("01/01/1985");

		Individual aunt5 = new Individual("I5_4");
		aunt5.setName("Aunt Doe");
		aunt5.setSex("F");
		aunt5.setChildOf("F5_1"); // Family with grandparents
		aunt5.setBirth("01/01/1987");

		Individual mother5 = new Individual("I5_5");
		mother5.setName("Mother Doe");
		mother5.setSex("F");
		mother5.setBirth("01/01/1986");

		Individual child5 = new Individual("I5_6");
		child5.setName("Child Doe");
		child5.setSex("M");
		child5.setSpouseOf("F5_3"); // Family with aunt
		child5.setBirth("01/01/1995");

		testIndividuals.put("I5_1", grandfather5);
		testIndividuals.put("I5_2", grandmother5);
		testIndividuals.put("I5_3", father5);
		testIndividuals.put("I5_4", aunt5);
		testIndividuals.put("I5_5", mother5);
		testIndividuals.put("I5_6", child5);

		Family grandparentFamily5 = new Family("F5_1");
		grandparentFamily5.setHusb("I5_1");
		grandparentFamily5.setWife("I5_2");
		grandparentFamily5.setMarriage("01/01/1980");
		ArrayList<String> grandparentChildren5 = new ArrayList<>();
		grandparentChildren5.add("I5_3"); // Father
		grandparentChildren5.add("I5_4"); // Aunt
		grandparentFamily5.setChild(grandparentChildren5);

		Family parentFamily5 = new Family("F5_2");
		parentFamily5.setHusb("I5_3");
		parentFamily5.setWife("I5_5");
		parentFamily5.setMarriage("01/01/1990");
		ArrayList<String> parentChildren5 = new ArrayList<>();
		parentChildren5.add("I5_6"); // Child
		parentFamily5.setChild(parentChildren5);

		Family auntFamily5 = new Family("F5_3");
		auntFamily5.setHusb("I5_6");
		auntFamily5.setWife("I5_4");
		auntFamily5.setMarriage("01/01/2000");

		testFamilies.put("F5_1", grandparentFamily5);
		testFamilies.put("F5_2", parentFamily5);
		testFamilies.put("F5_3", auntFamily5);

		// 6) Unique Family Name By Spouses
		Individual husband = new Individual("I6_1");
		husband.setName("John Doe");
		husband.setSex("M");
		husband.setBirth("01/01/1970");

		Individual wife = new Individual("I6_2");
		wife.setName("Jane Doe");
		wife.setSex("F");
		wife.setBirth("01/01/1972");

		testIndividuals.put("I6_1", husband);
		testIndividuals.put("I6_2", wife);

		Family family6_1 = new Family("F6_1");
		family6_1.setHusb("I6_1");
		family6_1.setWife("I6_2");
		family6_1.setMarriage("01/01/2000");

		Family family6_2 = new Family("F6_2");
		family6_2.setHusb("I6_1");
		family6_2.setWife("I6_2");
		family6_2.setMarriage("01/01/2000");

		testFamilies.put("F6_1", family6_1);
		testFamilies.put("F6_2", family6_2);

		Gedcom_Service.families = testFamilies;
		Gedcom_Service.individuals = testIndividuals;
	}

	@BeforeEach
	void setupStream() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent)); // Redirects System.out to outContent
	}

	@AfterEach
	void teardown() {
		System.setOut(originalOut);
		outContent.reset();
	}

	@Test
	void testBirthBeforeDeath() throws IOException {
		Gedcom_Service.birthBeforeDeath(testIndividuals);

		assertTrue(outContent.toString().contains("ERROR:INDIVIDUAL: User Story US03: Birth Before Death \n" +
				"Individual: I1 - User3 was born after death\n" +
				"DOB: 01/01/1990 DOD: 01/01/1980"));
	}

	@Test
	void testMarriagebeforedivorce() throws IOException {
		Gedcom_Service.Marriagebeforedivorce(testIndividuals, testFamilies);

		assertTrue(outContent.toString().contains("ERROR:FAMILY: User Story US04: Marriage Before Divorce \nFamily: " +
				"F2\nIndividual: I2_1: John DoeI2_2: Jane Doe marriage date is before divorce date.\nMarriage Date: " +
				"01/01/2000 Divorce Date: 01/01/1999\n"));
	}

	@Test
	void testBirthBeforeMarriageOfParent() throws IOException {

		Gedcom_Service.birthbeforemarriageofparent(testIndividuals, testFamilies);

		assertTrue(outContent.toString().contains("ERROR: User Story US08: Birth Before Marriage Date \nFamily ID: " +
				"F3\nIndividual: I3_3: Child Doe Has been born before parents' marriage\nDOB: 01/01/1995 " +
				"Parents Marriage Date: 01/01/2000\n\n"));
	}

	@Test
	void testMaleLastName() throws IOException, ParseException {
		Gedcom_Service.Malelastname(testFamilies);

		assertEquals(outContent.toString(), "ERROR: User Story US16:Male last name \nFamily ID: " +
				"F4   family members don't have same last name \n\n\n");
	}

	@Test
	void testAuntsAndUnclesName() throws IOException, ParseException {
		Gedcom_Service.AuntsandUnclesname(testFamilies);

		assertTrue(outContent.toString().contains("ERROR: User Story US20: Aunts and Uncles\nIndividual: " +
				"I5_6 - Child Doe is married to either their aunt or uncle I5_4 - Aunt Doe\n\n"));
	}

	@Test
	void testUniqueFamilyNameBySpouses() throws IOException {
		Gedcom_Service.uniqueFamilynameBySpouses(testIndividuals, testFamilies);

		assertTrue(outContent.toString().contains("ERROR: User Story US24: Unique Families By Spouse :\n" +
				"F6_1: Husbund Name: John Doe,Wife Name: Jane Doe and F6_2: Husbund Name: John Doe,Wife Name: Jane Doe\n" +
				" have same spouses and marriage dates :01/01/2000\n"));
	}

	@ParameterizedTest
	@CsvSource({
			"JAN, 01",
			"FEB, 02",
			"MAR, 03",
			"APR, 04",
			"MAY, 05",
			"JUN, 06",
			"JUL, 07",
			"AUG, 08",
			"SEP, 09",
			"OCT, 10",
			"NOV, 11",
			"DEC, 12"
	})
	void testGetMonth(String month, String expected) {
		assertEquals(expected, Gedcom_Service.getMonth(month));
	}

	@Test
	void testGetMonthNull()
	{
		assertNull(Gedcom_Service.getMonth("UNK"));
	}
}