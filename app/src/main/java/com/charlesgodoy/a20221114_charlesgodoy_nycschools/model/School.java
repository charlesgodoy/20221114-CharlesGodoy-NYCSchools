package com.charlesgodoy.a20221114_charlesgodoy_nycschools.model;

public class School {

    private final String dbn;
    private final String school_name;

    public School(String dbn, String school_name) {
        this.dbn = dbn;
        this.school_name = school_name;
    }

    public String getDbn() {
        return dbn;
    }

    public String getSchool_name() {
        return school_name;
    }
}
