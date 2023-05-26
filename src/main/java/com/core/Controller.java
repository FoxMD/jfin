package com.core;

import com.gui.TestView;
import com.model.FinanceModel;

public class Controller {
    private FinanceModel model;
    private TestView view;

    public Controller(FinanceModel model, TestView view)
    {
        this.model = model;
        this.view = view;
    }

    public void setMonth(String name)
    {
        model.setMonth(name);      
    }

    public String getMonth()
    {
        return model.getMonth();       
    }

    public String getType()
    {
        return model.getType();     
    }

    public String getValue()
    {
        return model.getValue();       
    }
     
    public void updateView()
    {                
        view.printDetails(model.getMonth(), model.getType(), model.getValue());
    }  

}
