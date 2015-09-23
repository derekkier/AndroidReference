package com.derekkier.androidreference;

import android.app.Application;
/*
Created to hold global variables.
 */
public class GlobalState extends Application{
    private String tableSelected;

    public void setTableSelected(String strTable)
    {
        this.tableSelected = strTable;
    }

    public String getTableSelected()
    {
        return this.tableSelected;
    }
}
