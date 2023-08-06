package b_application_business_rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AdapterConvertString {
    /**
     *
     * @param inputString
     * @return
     */
    public static List<String> toStringIDsList(String inputString){
        // Split the inputString using delimiter ", "
        String[] stringArray = inputString.split(", ");

        // Convert array to a list using
        List<String> stringList = Arrays.asList(stringArray);
        return stringList;
    }

    /**
     *
     * @param inputString
     * @return
     */
    public static List<UUID> toIDsList(String inputString){
        // Split the inputString using delimiter ", "
        String[] stringArray = inputString.split(", ");
        List<UUID> idList = new ArrayList<>();
        for (String s : stringArray){
            idList.add(UUID.fromString(s));
        }
        return idList;
    }
}

