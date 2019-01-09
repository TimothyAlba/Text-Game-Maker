package com.worldCreator;

import com.worldCreator.util.input.Arguments;

public class Driver {

    static public void main(String [] args) {
        Arguments parameters = Arguments.parseArguments(args);
        System.out.println(parameters.toString());


    }
}
