package edu.stevens.ssw555;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by trollo on 6/21/17.
 */
public class TestFamilyValidation {
    @Test
    public void emptyMapsTest() {
        FamilyValidations familyValidations = new FamilyValidations();
        Map<String, Map<String, String>> results = familyValidations.validateNoMoreThanFiveSameBirthDaysinFamily(new HashMap<>(), new HashMap<>());
        Assert.assertEquals(0, results.size());
    }

    @Test
    public void validFamilyTest() {
        FamilyValidations familyValidations = new FamilyValidations();
        GedcomService gedcomService = new GedcomService();
        Map<String, Map<String, String>> individualDetails = new HashMap<>();
        individualDetails.put("1", gedcomService.makeIndividualAttributeMap("John Smith", "Male", "May 20, 1975", "", "", "", "", ""));
        individualDetails.put("2", gedcomService.makeIndividualAttributeMap("Joe Smith", "Male", "June 16, 1982", "", "", "", "", ""));
        individualDetails.put("3", gedcomService.makeIndividualAttributeMap("Ryan Smith", "Male", "June 16, 1984", "", "", "", "", ""));
        individualDetails.put("4", gedcomService.makeIndividualAttributeMap("Bob Smith", "Male", "June 16, 1985", "", "", "", "", ""));
        individualDetails.put("5", gedcomService.makeIndividualAttributeMap("Jerry Smith", "Male", "June 16, 1988", "", "", "", "", ""));

        Map<String, Map<String, Object>> familyDetails = new HashMap<>();
        familyDetails.put("1", gedcomService.makeFamilyAttributeMap("John Smith", "N", "", "", "", "", "1,2,3,4,5"));


        Map<String, Map<String, String>> results = familyValidations.validateNoMoreThanFiveSameBirthDaysinFamily(familyDetails, individualDetails);
        Assert.assertEquals(0, results.size());
    }

    @Test
    public void invalidFamilyTest() {
        FamilyValidations familyValidations = new FamilyValidations();
        GedcomService gedcomService = new GedcomService();
        Map<String, Map<String, String>> individualDetails = new HashMap<>();
        individualDetails.put("1", gedcomService.makeIndividualAttributeMap("John Smith", "Male", "May 20, 1975", "", "", "", "", ""));
        individualDetails.put("2", gedcomService.makeIndividualAttributeMap("Joe Smith", "Male", "May 20, 1975", "", "", "", "", ""));
        individualDetails.put("3", gedcomService.makeIndividualAttributeMap("Ryan Smith", "Male", "May 20, 1975", "", "", "", "", ""));
        individualDetails.put("4", gedcomService.makeIndividualAttributeMap("Bob Smith", "Male", "May 20, 1975", "", "", "", "", ""));
        individualDetails.put("5", gedcomService.makeIndividualAttributeMap("Jerry Smith", "Male", "May 20, 1975", "", "", "", "", ""));

        Map<String, Map<String, Object>> familyDetails = new HashMap<>();
        familyDetails.put("1", gedcomService.makeFamilyAttributeMap("John Smith", "N", "", "", "", "", "1,2,3,4,5"));


        Map<String, Map<String, String>> results = familyValidations.validateNoMoreThanFiveSameBirthDaysinFamily(familyDetails, individualDetails);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void nullFamilyTest() {
        FamilyValidations familyValidations = new FamilyValidations();
        Map<String, Map<String, String>> results = familyValidations.validateNoMoreThanFiveSameBirthDaysinFamily(null, null);
        Assert.assertEquals(0, results.size());
    }

    @Test
    public void noMarriedandLivingTest() {
        MarriageValidations marriageValidations = new MarriageValidations();
        GedcomService gedcomService = new GedcomService();
        Map<String, Map<String, String>> individualDetails = new HashMap<>();
        individualDetails.put("1", gedcomService.makeIndividualAttributeMap("John Smith", "Male", "May 20, 1975", "", "true", "", "", "NA"));
        individualDetails.put("2", gedcomService.makeIndividualAttributeMap("Joe Smith", "Male", "May 20, 1975", "", "true", "", "", "NA"));
        individualDetails.put("3", gedcomService.makeIndividualAttributeMap("Ryan Smith", "Male", "May 20, 1975", "", "true", "", "", "NA"));
        individualDetails.put("4", gedcomService.makeIndividualAttributeMap("Bob Smith", "Male", "May 20, 1975", "", "true", "", "", "NA"));
        individualDetails.put("5", gedcomService.makeIndividualAttributeMap("Jerry Smith", "Male", "May 20, 1975", "", "true", "", "", "NA"));
        Assert.assertEquals("Number of married and alive should be 0", 0, marriageValidations.getLivingAndMarried(individualDetails).size());
    }

    @Test
    public void marriedandLivingTest() {
        MarriageValidations marriageValidations = new MarriageValidations();
        GedcomService gedcomService = new GedcomService();
        Map<String, Map<String, String>> individualDetails = new HashMap<>();
        individualDetails.put("1", gedcomService.makeIndividualAttributeMap("John Smith", "Male", "May 20, 1975", "", "true", "", "", "{F1}"));
        individualDetails.put("2", gedcomService.makeIndividualAttributeMap("Joe Smith", "Male", "May 20, 1975", "", "true", "", "", "{F1}"));
        individualDetails.put("3", gedcomService.makeIndividualAttributeMap("Ryan Smith", "Male", "May 20, 1975", "", "true", "", "", "{F2}"));
        individualDetails.put("4", gedcomService.makeIndividualAttributeMap("Bob Smith", "Male", "May 20, 1975", "", "true", "", "", "NA"));
        individualDetails.put("5", gedcomService.makeIndividualAttributeMap("Jerry Smith", "Male", "May 20, 1975", "", "true", "", "", "NA"));
        Assert.assertEquals("Number of married and alive should be 3", 3, marriageValidations.getLivingAndMarried(individualDetails).size());
    }

    @Test
    public void allValidDivorcesTest() throws Exception {
        MarriageValidations marriageValidations = new MarriageValidations();
        GedcomService gedcomService = new GedcomService();
        Map<String, Map<String, String>> individualDetails = new HashMap<>();
        individualDetails.put("1", gedcomService.makeIndividualAttributeMap("John Smith", "Male", "May 20, 1975", "", "true", "NA", "", "{F1}"));
        individualDetails.put("2", gedcomService.makeIndividualAttributeMap("Joe Smith", "Male", "May 20, 1975", "", "true", "NA", "", "{F1}"));
        individualDetails.put("3", gedcomService.makeIndividualAttributeMap("Ryan Smith", "Male", "May 20, 1975", "", "true", "NA", "", "{F2}"));
        individualDetails.put("4", gedcomService.makeIndividualAttributeMap("Bob Smith", "Male", "May 20, 1975", "", "true", "NA", "", "NA"));
        individualDetails.put("5", gedcomService.makeIndividualAttributeMap("Mary Smith", "Male", "May 20, 1975", "", "true", "NA", "", "NA"));

        Map<String, Map<String, Object>> familyDetails = new HashMap<>();
        familyDetails.put("1", gedcomService.makeFamilyAttributeMap("", "1 JAN 2015", "1", "John Smith", "5", "Mary Smith", ""));

        Assert.assertEquals("Number of invalid divorces should be 0", 0, marriageValidations.validateDivorceBeforeDeath(familyDetails, individualDetails).size());
    }

    @Test
    public void invalidDivorcesTest() throws Exception {
        MarriageValidations marriageValidations = new MarriageValidations();
        GedcomService gedcomService = new GedcomService();
        Map<String, Map<String, String>> individualDetails = new HashMap<>();
        individualDetails.put("1", gedcomService.makeIndividualAttributeMap("John Smith", "Male", "May 20, 1975", "", "false", "1 JAN 2014", "", "{F1}"));
        individualDetails.put("2", gedcomService.makeIndividualAttributeMap("Joe Smith", "Male", "May 20, 1975", "", "true", "NA", "", "{F1}"));
        individualDetails.put("3", gedcomService.makeIndividualAttributeMap("Ryan Smith", "Male", "May 20, 1975", "", "true", "NA", "", "{F2}"));
        individualDetails.put("4", gedcomService.makeIndividualAttributeMap("Bob Smith", "Male", "May 20, 1975", "", "true", "NA", "", "NA"));
        individualDetails.put("5", gedcomService.makeIndividualAttributeMap("Mary Smith", "Male", "May 20, 1975", "", "true", "NA", "", "NA"));

        Map<String, Map<String, Object>> familyDetails = new HashMap<>();
        familyDetails.put("1", gedcomService.makeFamilyAttributeMap("", "1 JAN 2015", "1", "John Smith", "5", "Mary Smith", ""));

        Assert.assertEquals("Number of invalid divorces should be 1", 1, marriageValidations.validateDivorceBeforeDeath(familyDetails, individualDetails).size());
    }
    
    @Test
    public void fewerThanFifteenChildren() {
        FamilyValidations familyValidations = new FamilyValidations();
        GedcomService gedcomService = new GedcomService();
        Map<String, Map<String, Object>> familyDetails = new HashMap<>();
        familyDetails.put("1", gedcomService.makeFamilyAttributeMap("", "1 JAN 2015", "1", "John Smith", "5", "Mary Smith", "{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}"));
        List<String> results = familyValidations.fewerThanFifteenChildren(familyDetails);
        Assert.assertTrue(results.size() == 1);
    }
}
