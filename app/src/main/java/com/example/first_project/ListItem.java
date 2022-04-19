package com.example.first_project;

public class ListItem {
    private String formula;
    private String result;

    public ListItem(String formula, String result) {
        this.formula = formula;
        this.result = result;
    }

    public String getFormula() {
        return formula;
    }

    public String getResult() {
        return result;
    }
}
