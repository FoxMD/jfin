package com.connector;

import java.util.Map;
//import java.util.NoSuchElementException;

/**
 * Handler for getting name and password.
 */
public final class SysHandler {
    private static final Map<String, String> VARIABLES = System.getenv();

    /**
     * Private constructor.
     */
    private SysHandler() {

    }

    /**
     * Function for searching for username and password.
     * @param label What env are you searching.
     * @return The value of the ENV.
     */
    public static String getVariable(String label) {
        if (VARIABLES.containsKey(label)) {
            return VARIABLES.get(label);
        } else {
            //String excMsg = "No environment variable for label \"" + label
            //        + "\"";
            //throw new NoSuchElementException(excMsg);
            return "None";
        }
    }
}
