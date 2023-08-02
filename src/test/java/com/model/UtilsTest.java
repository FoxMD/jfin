package com.model;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;

public class UtilsTest {
    private final Object[] obj = {"2021", "January", "TEST", "1254.5f", "CZK", ""};
    private final Object[] wobj = {"2021", "January", "TEST", 1254.5f, "CZK", ""};

    @Test
    void testGetUniqueID() {
        String id1 = new String(Utils.getUniqueID(obj));
        String id2 = new String(Utils.getUniqueID(obj));

        assertNotEquals(id1, id2);

        try {
            String id3 = new String(Utils.getUniqueID(wobj));
            assertNotEquals("-1", id3);
            fail("Should have thrown an exception");
        } catch (final RuntimeException e) {
            assertTrue(true);
        }

        try {
            new String(Utils.getUniqueID(obj));
            throw new UnsupportedEncodingException();
        } catch (final UnsupportedEncodingException e) {
            assertTrue(true);
        }
    }

}
