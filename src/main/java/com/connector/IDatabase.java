package com.connector;

public interface IDatabase {
    String DB_URL = "local";
    String USER = "user";

    enum QTYPE {
        STANDARD,
        PRECISE,
        DATE
    }

    int testConnection();

    int writeQuery(Object[] entry);

    int removeEntryFromDB(Object[] entry);

    int modifyEntryFromDB(Object[] entry);

    Object[][] getQuery(String arg1, String arg2, QTYPE type);
}
