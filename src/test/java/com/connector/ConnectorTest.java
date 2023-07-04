package com.connector;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * onnector test.
 */
public class ConnectorTest {
    private final float testValueQ = 12.5f;

    /**
     * SysHandler Test.
     */
    @Test
    public void sysHandlerTest() {
        assertEquals("None", SysHandler.getVariable("abcdef"));
    }

    /**
     * DB Test.
     *//*
    @Test
    public void dbTest() {
        DBConnector db = new DBConnector();
        if (db.testConnection() == 0) {
            assertEquals(0, db.writeQuery("a", "b", "c", this.testValueQ, "d", "e"));
        } else {
            assertEquals(-1, db.writeQuery("a", "b", "c", this.testValueQ, "d", "e"));
        }
    }*/
}
