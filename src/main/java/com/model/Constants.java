package com.model;

/**
 * Header file for database scheme.
 */
public class Constants {
    /**
     * Header
     */
    public static final Object[] TABLE_HEADER = {"Year", "Month", "Type",
    "Price", "Currency", "Description"};

    /**
     * Mock data
     */
    public static final Object[][] DATA = {
        {"2021", "January", "CAR", 15.98, "EUR", " "},
        {"2021", "March", "FUN", 126.57, "CZK", "Kino"},
        {"2022", "June", "FUN", 57.84, "CZK", "Netflix"},
        {"2021", "June", "CLOTHES", 11.74, "EUR", "Triko"},
        {"2023", "July", "GROCERY", 6.55, "EUR", "Albert"},
        {"2022", "December", "GROCERY", 81.53, "EUR", "Globus"},
        {"2023", "August", "GROCERY", 6.05, "EUR", "Albert"},
        {"2022", "September", "GROCERY", 8.06, "EUR", "DM"},
        {"2022", "April", "CAR", 254.67, "CZK", "Sterace"},
        {"2023", "June", "OTHER", 7.87, "EUR", "Vyber"}};

    /**
     * Real data
     */
    public static Object[][] DB_DATA;

    /**
     * Returns the position of the wanted column.
     * @param what Wanted column
     * @return Position of wanted column
     */
    public static int getColumnPosition(String what) {
        int len = TABLE_HEADER.length;
        int i = 0;

        while (i < len) {
            if (TABLE_HEADER[i] == what) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }
}
