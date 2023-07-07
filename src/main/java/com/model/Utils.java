package com.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class with some sizes.
 */
public final class Utils {
    private static final long MILITIME = 1000L;

    /**
     * Private class.
     */
    private Utils() {

    }

    public enum Entries { YEAR, MONTH, TYPE, VALUE, CURRENCY, DESC, ID }

    /**
     * Creates a unique ID for DB entries.
     * @param data Data for the DB.
     * @return Unique ID.
     */
    public static String getUniqueID(Object[] data) {
        try {
            long unixTime = System.currentTimeMillis() / MILITIME;
            String dateString = Long.toString(unixTime);
            String myData = (String) data[Utils.Entries.YEAR.ordinal()]
                + (String) data[Utils.Entries.MONTH.ordinal()]
                + (String) data[Utils.Entries.TYPE.ordinal()]
                + (String) data[Utils.Entries.VALUE.ordinal()]
                + (String) data[Utils.Entries.CURRENCY.ordinal()]
                + (String) data[Utils.Entries.DESC.ordinal()]
                + dateString;

            byte[] bytesOfMessage = myData.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theMD5digest = md.digest(bytesOfMessage);

            return theMD5digest.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
