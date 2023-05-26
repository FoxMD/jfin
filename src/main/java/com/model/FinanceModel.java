package com.model;

public class FinanceModel {
    private String Month;
    private String Type;
    private String Value;
    private String Currency;

    public String getMonth()
    {
        return Month;
    }

    public String getType()
    {
        return Type;
    }

    public String getValue()
    {
        return Value;
    }

    public String getCurrency()
    {
        return Currency;
    }

    public void setMonth(String Month)
    {
        this.Month = Month;
    }

}
