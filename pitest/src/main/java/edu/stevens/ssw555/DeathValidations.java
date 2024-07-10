package edu.stevens.ssw555;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by trollo on 6/21/17.
 */
public class DeathValidations {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("D MMM YYYY");

    public void printDeceasedFamilyMemberList(List<String> deceasedList, Map<String, Map<String, String>> individualAttributes) throws IOException {
        for(String deceasedId : deceasedList) {
            System.out.print("INFO: INDIVIDUAL: US29: Individual is deceased: ");
            System.out.print(individualAttributes.get(deceasedId).get("name"));
            System.out.println(": Individual died on: " + individualAttributes.get(deceasedId).get("death"));
        }
        System.out.println();
    }

    //Takes complete listing of family members from GEDCOM file and produces a list of deceased family members
    public List<String> listDeceasedFamilyMembers(Map<String, Map<String, String>> individualsAttributeMap) {
        if(individualsAttributeMap != null) {
            List<String> deceasedFamilyMemebers = individualsAttributeMap.entrySet().stream()
                    .filter(individaulAttribute -> "false".equalsIgnoreCase(individaulAttribute.getValue().get("alive")))
                    .map(individaulAttribute -> individaulAttribute.getKey())
                    .collect(Collectors.toList());
            return deceasedFamilyMemebers;
        } else {
            return new ArrayList<>();
        }
    }
    
    public List<String> validateMarriagesBeforeDeath(Map<String, Map<String, Object>> familyAttributeMap, Map<String, Map<String, String>> individualsAttributeMap) throws ParseException {
        if(individualsAttributeMap != null && familyAttributeMap != null) {
            List<String> results = new ArrayList<>();
            for(Map.Entry<String, Map<String, Object>> family : familyAttributeMap.entrySet()) {
                String husbandId = (String)family.getValue().get("husband");
                String wifeId = (String)family.getValue().get("wife");
                Date marriageDate = dateFormat.parse((String)family.getValue().get("married"));
                if(!Boolean.parseBoolean(individualsAttributeMap.get(husbandId).get("alive")) || !Boolean.parseBoolean(individualsAttributeMap.get(wifeId).get("alive"))) {
                    boolean invalid = false;
                    if(!"NA".equals(individualsAttributeMap.get(wifeId).get("death"))) {
                        Date death = dateFormat.parse(individualsAttributeMap.get(wifeId).get("death"));
                        invalid = death.before(marriageDate);
                    }
                    if(!"NA".equals(individualsAttributeMap.get(husbandId).get("death")) && !invalid) {
                        Date death = dateFormat.parse(individualsAttributeMap.get(husbandId).get("death"));
                        invalid = death.before(marriageDate);
                    }
                    if(invalid) {
                        results.add("ERROR: FAMILY: US05: Marriage Occurred After Death: Family=" + family.getKey() + " Husband=" + ((String)family.getValue().get("husbandName")).replace("/", "") + " Wife=" + ((String)family.getValue().get("wifeName")).replace("/", ""));
                    }
                }
            }
            return results;
        } else {
            return new ArrayList<>();
        }
    }
}
