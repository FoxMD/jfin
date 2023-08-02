package com.connector;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * onnector test.
 */
public class ConnectorTest {
    /**
     * SysHandler Test.
     */
    @Test
    public void sysHandlerTest() {
        assertEquals("None", SysHandler.getVariable("abcdef"));
    }
}
