package com.tgm;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Utilities {

    public static ArrayList<String> mapPairValues(List<Pair<String,String>> listPair) {
        ArrayList<String> listString = new ArrayList<>();
        listPair.forEach(pair -> listString.add(pair.getValue()));
        return listString;
    }
}
