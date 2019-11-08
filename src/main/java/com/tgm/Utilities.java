package com.tgm;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Utilities {

    public static ArrayList<String> mapPairValues(List<Pair<String,String>> listPair) {
        ArrayList<String> listString = new ArrayList<>();
        listPair.forEach(pair -> listString.add(pair.getKey()));
        return listString;
    }

    public static String trimWhiteSpace(String string) {
        if (string.charAt(0) == ' ') {
            string = string.replaceFirst("\\s+","");
            if (string.charAt(string.length()-1) == ' ')
                string = string.trim();
        }
        return string;
    }
}
