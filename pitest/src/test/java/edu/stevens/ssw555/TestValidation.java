package edu.stevens.ssw555;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by trollo on 6/14/17.
 */
public class TestValidation {
    @Test
    public void listDeceasedFamilyMembersEmpty() throws Exception {
        DeathValidations deathValidations = new DeathValidations();
        Map<String, Map<String, String>> familyDetails = new HashMap<>();

        List<String> results = deathValidations.listDeceasedFamilyMembers(familyDetails);
        Assert.assertTrue(results.size() == 0);
    }

    @Test
    public void listDeceasedFamilyMembersNoDeceased() throws Exception {
        GedcomService gedcomService = new GedcomService();
        DeathValidations deathValidations = new DeathValidations();
        Map<String, Map<String, String>> familyDetails = new HashMap<>();
        familyDetails.put("1", gedcomService.makeIndividualAttributeMap("John Smith", "Male", "May 20, 1975", "45", "true", "", "", ""));
        familyDetails.put("2", gedcomService.makeIndividualAttributeMap("Bob Smith", "Male", "June 16, 1980", "40", "true", "", "", ""));

        List<String> results = deathValidations.listDeceasedFamilyMembers(familyDetails);
        Assert.assertTrue(results.size() == 0);
    }

    @Test
    public void listDeceasedFamilyMembersOneDeceased() throws Exception {
        GedcomService gedcomService = new GedcomService();
        DeathValidations deathValidations = new DeathValidations();

        Map<String, Map<String, String>> familyDetails = new HashMap<>();
        familyDetails.put("1", gedcomService.makeIndividualAttributeMap("John Smith", "Male", "May 20, 1975", "45", "true", "", "", ""));
        familyDetails.put("2", gedcomService.makeIndividualAttributeMap("Bob Smith", "Male", "June 16, 1980", "40", "false", "June 20, 2000", "", ""));

        List<String> results = deathValidations.listDeceasedFamilyMembers(familyDetails);
        Assert.assertTrue(results.size() == 1);
    }

    @Test
    public void listDeceasedFamilyMembersMultipleDeceased() throws Exception {
        GedcomService gedcomService = new GedcomService();
        DeathValidations deathValidations = new DeathValidations();

        Map<String, Map<String, String>> familyDetails = new HashMap<>();
        familyDetails.put("1", gedcomService.makeIndividualAttributeMap("John Smith", "Male", "May 20, 1975", "45", "true", "", "", ""));
        familyDetails.put("2", gedcomService.makeIndividualAttributeMap("Bob Smith", "Male", "June 16, 1980", "40", "false", "June 20, 2000", "", ""));
        familyDetails.put("3", gedcomService.makeIndividualAttributeMap("Helen Smith", "Female", "September 20, 1970", "50", "false", "June 10, 2010", "", ""));
        familyDetails.put("4", gedcomService.makeIndividualAttributeMap("Joe Smith", "Male", "September 8, 200", "17", "true", "", "", ""));

        List<String> results = deathValidations.listDeceasedFamilyMembers(familyDetails);
        Assert.assertTrue(results.size() == 2);
    }

    @Test
    public void listDeceasedFamilyMembersNullInput() throws Exception {
        DeathValidations deathValidations = new DeathValidations();

        List<String> results = deathValidations.listDeceasedFamilyMembers(null);
        Assert.assertTrue(results.size() == 0);
    }
    
    @Test
    public void deathBeforeMarriageTest() throws ParseException {
        DeathValidations deathValidations = new DeathValidations();
        GedcomService gedcomService = new GedcomService();
        Map<String, Map<String, String>> familyDetails = new HashMap<>();
        familyDetails.put("1", gedcomService.makeIndividualAttributeMap("John Smith", "Male", "22 JUL 1970", "45", "true", "22 JUL 1995", "", ""));
        familyDetails.put("2", gedcomService.makeIndividualAttributeMap("Helen Smith", "Female", "22 JUL 1970", "50", "false", "NA", "", ""));

        Map<String, Map<String, Object>> marriageInfo = new HashMap<>();
        marriageInfo.put("1", gedcomService.makeFamilyAttributeMap("22 JUL 1996", "NA", "1", "", "2", "", ""));

        List<String> results = deathValidations.validateMarriagesBeforeDeath(marriageInfo, familyDetails);
        Assert.assertTrue(results.size() == 1);
    }
}
