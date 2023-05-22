package com.pluralsight.calcengine;

public enum IterateAClassValues {

    First("first"),
    Second("second"),
    Third("third"),
    Fourth("fourth"),
    Fifth("fifth");

    private final String iterateACClassValues;


    IterateAClassValues(String iterateACClassValues) {
        this.iterateACClassValues = iterateACClassValues;
    }

    public String getIterateACClassValues() {
        return iterateACClassValues;
    }
}
