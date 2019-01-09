package com.tgm.input;

import java.util.*;

public class Arguments {

    static public final String INPUT = "input";
    static public final String OUTPUT = "output";
    static private final String [] ARGS = {INPUT,OUTPUT};

    static public Arguments parseArguments(String [] args){
        HashMap<String,String> values = new HashMap<String,String>();
        for (int i = 0; i < ARGS.length; i++) {
            values.put(ARGS[i],"");
        }

        for (int i = 0; i < args.length; i++) {
            if(values.containsKey(args[i].toLowerCase())){
                values.put(args[i], args[i + 1]);
                i++;
            }
        }

        return new Arguments(values);
    }

    private String inputPath;
    private String outputPath;

    protected Arguments(){

    }

    protected Arguments(HashMap<String,String> values){
        inputPath = values.get(INPUT);
        outputPath = values.get(OUTPUT);
    }

    @Override
    public String toString() {
        return String.format("Input path: %s" +
                "%nOutput path: %s",inputPath, outputPath);
    }
}
