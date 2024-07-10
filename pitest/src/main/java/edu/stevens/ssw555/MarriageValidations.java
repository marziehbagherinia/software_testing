package edu.stevens.ssw555;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by trollo on 7/6/17.
 */
public class MarriageValidations {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("D MMM YYYY");

    public void printLivingAndMarried(List<String> marriedAndLiving) throws IOException {
        for(String name : marriedAndLiving) {
            System.out.print("INFO: INDIVIDUAL: US30: Individual is married and living: ");
            System.out.println(name.replace("/", ""));
        }
        System.out.println();
    }

    public List<String> getLivingAndMarried(Map<String, Map<String, String>> individualsAttributeMap) {
        List<String> results = new ArrayList<>();
        for(Map.Entry<String, Map<String, String>> individual : individualsAttributeMap.entrySet()) {
            if(!"NA".equals(individual.getValue().get("spouse")) && "true".equalsIgnoreCase(individual.getValue().get("alive"))) {
                results.add(individual.getValue().get("name"));
            }
        }
        return results;
    }

    public void printInvalidDivorces(List<String> invalidDivorces) throws IOException {
        for(String message : invalidDivorces) {
            System.out.println(message);
        }
        System.out.println();
    }

    public List<String> validateDivorceBeforeDeath(Map<String, Map<String, Object>> familyMap, Map<String, Map<String, String>> individualsAttributeMap) throws ParseException {
        List<String> results = new ArrayList<>();
        for(Map.Entry<String, Map<String, Object>> family : familyMap.entrySet()) {
            if(!"NA".equals(family.getValue().get("divorced"))) {
                boolean invalid = false;
                Date divorceDate = dateFormat.parse((String)family.getValue().get("divorced"));
                String husbandId = (String)family.getValue().get("husband");
                String wifeId = (String)family.getValue().get("wife");
                String husbandDeath = individualsAttributeMap.get(husbandId).get("death");
                String wifeDeath = individualsAttributeMap.get(wifeId).get("death");
                if(!"NA".equals(husbandDeath)) {
                    Date death = dateFormat.parse(husbandDeath);
                    if(death.before(divorceDate)) {
                        invalid = true;
                    }
                }
                if(!"NA".equals(wifeDeath)) {
                    Date death = dateFormat.parse(husbandDeath);
                    if(death.before(divorceDate)) {
                        invalid = true;
                    }
                }
                if(invalid) {
                    results.add("ERROR: FAMILY: US06: Divorce before death: family id: " + family.getKey().replace("@", "") +
                    " Husband: " + family.getValue().get("husbandName") + " Wife: " + family.getValue().get("wifeName"));
                }
            }
        }
        return results;
    }
}
