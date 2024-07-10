package edu.stevens.ssw555;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by trollo on 6/21/17.
 */
public class FamilyValidations {

    public void printNoMoreThanFiveSameBirthDaysinFamilyViolations(Map<String, Map<String, String>> violations) throws IOException {
        for(Map.Entry<String, Map<String, String>> family : violations.entrySet()) {
            for(Map.Entry<String, String> birthdate : family.getValue().entrySet()) {
                System.out.print("ERROR: FAMILY: US14: Family: " + family.getKey().replace("@", ""));
                System.out.print(" has five or more children with the same birthday:");
                System.out.print(" Birthdate: " + birthdate.getKey());
                System.out.println(" : Children: " + birthdate.getValue().replace("/", ""));
            }
        }
        System.out.println();
    }

    public Map<String, Map<String, String>> validateNoMoreThanFiveSameBirthDaysinFamily(Map<String, Map<String, Object>> familyAttributeMap, Map<String, Map<String, String>> individualsAttributeMap) {
        if(individualsAttributeMap != null && familyAttributeMap != null) {
            Map<String, Map<String, String>> invalidBirthDateFamilies = new HashMap<>();
            for(Map.Entry<String, Map<String, Object>> family : familyAttributeMap.entrySet()) {
                Map<String, Map<String, Object>> birthCountMap = new HashMap<>();
                if(!family.getValue().get("children").equals("")){
                for(String childId : (List<String>)family.getValue().get("children")) {
                    if(birthCountMap.containsKey(individualsAttributeMap.get(childId).get("birth"))) {
                        Map<String, Object> birthData = birthCountMap.get(individualsAttributeMap.get(childId).get("birth"));
                        birthData.put("count", (Integer)birthData.get("count") + 1);
                        birthData.put("names", (String)birthData.get("names") + ", " + individualsAttributeMap.get(childId).get("name"));
                        birthCountMap.put(individualsAttributeMap.get(childId).get("birth"), birthData);
                        if((Integer)birthCountMap.get(individualsAttributeMap.get(childId).get("birth")).get("count") >=5 ) {
                            if(invalidBirthDateFamilies.containsKey(family.getKey())) {
                                Map<String, String> birthInfo = invalidBirthDateFamilies.get(family.getKey());
                                birthInfo.put(individualsAttributeMap.get(childId).get("birth"), (String)birthCountMap.get(individualsAttributeMap.get(childId).get("birth")).get("names"));
                                invalidBirthDateFamilies.put(family.getKey(), birthInfo);
                            } else {
                                Map<String, String> birthInfo = new HashMap<>();
                                birthInfo.put(individualsAttributeMap.get(childId).get("birth"), (String)birthCountMap.get(individualsAttributeMap.get(childId).get("birth")).get("names"));
                                invalidBirthDateFamilies.put(family.getKey(), birthInfo);
                            }
                        }
                    }  
                    else {
                        Map<String, Object> birthData = new HashMap<>();
                        birthData.put("count", 1);
                        birthData.put("names", individualsAttributeMap.get(childId).get("name"));
                        birthData.put("date", individualsAttributeMap.get(childId).get("birth"));

                        birthCountMap.put(individualsAttributeMap.get(childId).get("birth"), birthData);
                    }
                }
            }
                }
            return invalidBirthDateFamilies;
        } else {
            return new HashMap<>();
        }
    }
    public List<String> fewerThanFifteenChildren( Map<String, Map<String, Object>> familyAttributes) {
        List<String> results = new ArrayList<>();
        for(Map.Entry<String, Map<String, Object>> family : familyAttributes.entrySet()) {
            if(family.getValue().get("children") instanceof List) {
                if(((List)family.getValue().get("children")).size() > 15) {
                    results.add("ERROR: FAMILY: US15: More than 15 siblings in family=" + family.getKey().replace("@", ""));
                }
            }
        }
        return results;
    }
}
