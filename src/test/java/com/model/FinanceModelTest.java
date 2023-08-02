package com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.connector.IDatabase;

/**
 * Test for model.
 */
public class FinanceModelTest {
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
                    if ("T_Col".equals(arg1) && "T_Term".equals(arg2)) {
                        Object[][] testObj = new Object[1][sizeOfEntry];
                        testObj[0][0] = "Passed";
                        return testObj;
                    }
                    return new Object[0][sizeOfEntry];
                case PRECISE:
                    return new Object[0][sizeOfEntry];
                case DATE:
                    if ("T_2020".equals(arg1) && "T_January".equals(arg2)) {
                        Object[][] testObj = new Object[1][sizeOfEntry];
                        testObj[0][0] = "Passed";
                        return testObj;
                    }
                    return new Object[0][sizeOfEntry];
                default:
                    return new Object[0][sizeOfEntry];
            }
        }
    }

    /**
     * Model construction test.
     */
    @Test
    void modelTest() {
        MockDB mockDB = new MockDB();
        FinanceModel testModel = new FinanceModel(mockDB);
        assertNotNull(testModel.getDBInstance());
        assertEquals(1, mockDB.testConnection());
    }

    @Test
    void nullDBModelTest() {
        FinanceModel testModel = new FinanceModel(null);
        assertNull(testModel.getDBInstance());
    }

    @Test
    void getDataFromDBTest() {
        MockDB mockDB = new MockDB();
        FinanceModel testModel = new FinanceModel(mockDB);
        Object[][] testObj1 = testModel.getDataFromDB("T_Col", "T_Term");
        Object[][] testObj2 = testModel.getDataForSpecificDate("T_2020", "T_January");

        assertEquals("Passed", testObj1[0][0]);
        assertEquals("Passed", testObj2[0][0]);

        Object[][] testObj3 = testModel.getDataFromDB("F_Col", "T_Term");
        Object[][] testObj4 = testModel.getDataForSpecificDate("F_2020", "T_January");

        assertEquals(0, testObj3.length);
        assertEquals(0, testObj4.length);
    }

    @Test
    void dataHandling() {
        MockDB mockDB = new MockDB();
        FinanceModel testModel = new FinanceModel(mockDB);

        final float e1 = 54.5f;
        final float e2 = 80.50f;
        final float e3 = 50.50f;
        final float e4 = 66.5f;
        final float i1 = 2500f;

        final Object[][] data = {
            {"2021", "January", "CAR", e1, "EUR", " "},
            {"2021", "January", "FUN", e2, "CZK", "Kino"},
            {"2022", "January", "FUN", e3, "CZK", "Netflix"},
            {"2021", "January", "Income", i1, "EUR", "Triko"},
            {"2021", "January", "GROCERY", e4, "EUR", "Albert"}};

        testModel.setNewDataValues(data);

        assertEquals(i1, testModel.getIncome());
        assertEquals(e1 + e4 + (e2 + e3) / com.model.Utils.CHANGE_RATE, testModel.getExpenses());
        assertEquals(i1 - (e1 + e4 + (e2 + e3) / com.model.Utils.CHANGE_RATE), testModel.getDifference());
        assertEquals(i1 - (e1 + e4 + (e2 + e3) / com.model.Utils.CHANGE_RATE), testModel.getDifferenceForChart());

        Map<String, Float> objectReturned = testModel.getChartValues();
        assertEquals(objectReturned.get("CAR"), e1);
        assertEquals(objectReturned.get("FUN"), e2 / com.model.Utils.CHANGE_RATE + e3 / com.model.Utils.CHANGE_RATE);
        assertEquals(objectReturned.get("Income"), null);
        assertEquals(objectReturned.get("GROCERY"), e4);
    }

    @Test
    void dataHandlingHighExpenses() {
        MockDB mockDB = new MockDB();
        FinanceModel testModel = new FinanceModel(mockDB);

        final float e1 = 54.5f;
        final float e2 = 80.50f;
        final float e3 = 50.50f;
        final float e4 = 66.5f;
        final float i2 = 12.0f;

        final Object[][] data = {
            {"2021", "January", "CAR", e1, "EUR", " "},
            {"2021", "January", "FUN", e2, "CZK", "Kino"},
            {"2022", "January", "FUN", e3, "CZK", "Netflix"},
            {"2021", "January", "Income", i2, "EUR", "Triko"},
            {"2021", "January", "GROCERY", e4, "EUR", "Albert"}};

        testModel.setNewDataValues(data);

        assertEquals(i2, testModel.getIncome());
        assertEquals(e1 + e4 + (e2 + e3) / com.model.Utils.CHANGE_RATE, testModel.getExpenses());
        assertEquals(0, testModel.getDifferenceForChart());
        assertEquals(i2 - (e1 + e4 + (e2 + e3) / com.model.Utils.CHANGE_RATE), testModel.getDifference());

        assertEquals(1, testModel.writeQuery(new Object[0]));
        assertEquals(1, testModel.removeEntryFromDB(new Object[0]));
        assertEquals(1, testModel.modifyEntryFromDB(new Object[0]));
    }
}
