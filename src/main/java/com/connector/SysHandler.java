package com.connector;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Handler for getting name and password.
 */
public class SysHandler {
    private static final Map<String, String> VARIABLES = System.getenv();

    /**
     * Function for searching for username and password.
     * @param label What env are you searching.
     * @return The value of the ENV.
     */
    public static String getVariable(String label) {
        if (VARIABLES.containsKey(label)) {
            return VARIABLES.get(label);
        } else {
            String excMsg = "No environment variable for label \"" + label
                    + "\"";
            throw new NoSuchElementException(excMsg);
        }
    }
}
