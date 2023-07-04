package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantsTest {
    /**
     * Constants Test.
     */
    @Test
    void testSearching() {
        assertEquals(0, Constants.getColumnPosition("Year"));
        assertEquals(-1, Constants.getColumnPosition("MickyMouse"));
        assertEquals(2, Constants.getColumnPosition("Type"));
    }

    /**
     * Utils test.
     */
    @Test
    void testUtils() {
        assertEquals(2, Utils.Entries.TYPE.ordinal());
        assertEquals(0, Utils.Entries.YEAR.ordinal());
    }
}

