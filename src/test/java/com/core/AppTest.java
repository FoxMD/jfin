package com.core;

import org.junit.jupiter.api.Test;

import com.connector.IDatabase;
import com.connector.IDatabase.QTYPE;

/*
import com.gui.GraphPanel;
import com.model.FinanceModel;
*/
import static org.junit.jupiter.api.Assertions.assertEquals;
/*
import javax.swing.JFrame;
import javax.swing.JTextField;
*/

/**
 * Unit test for simple App.
 */
class AppTest {
    private final int size = 6;

    private class MockDB implements IDatabase {
        final int sizeOfEntry = 6;

        MockDB() {

        }

        public int testConnection() {
            return 1;
        }

        public int writeQuery(Object[] entry) {
            return 1;
        }

        public int removeEntryFromDB(Object[] entry) {
            return 1;
        }

        public int modifyEntryFromDB(Object[] entry) {
            return 1;
        }

        public Object[][] getQuery(String arg1, String arg2, QTYPE type) {
            switch (type) {
                case STANDARD:
                    return new Object[0][sizeOfEntry];
                case PRECISE:
                    return new Object[0][sizeOfEntry];
                case DATE:
                    return new Object[0][sizeOfEntry];
                default:
                    return new Object[0][sizeOfEntry];
            }
        }
    }

    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {
        Start.main(null);
        assertEquals(1, 1);
    }

    @Test
    void nullDBTest() {
        try {
            Start.createDependenciesAndShowGUI(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void notNullDB() {
        MockDB mockDB = new MockDB();
        try {
            Start.createDependenciesAndShowGUI(mockDB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
