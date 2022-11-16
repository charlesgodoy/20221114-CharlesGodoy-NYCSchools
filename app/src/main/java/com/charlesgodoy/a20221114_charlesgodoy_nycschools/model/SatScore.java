package com.charlesgodoy.a20221114_charlesgodoy_nycschools.model;

public class SatScore {

    private final String dbn;
    private final String school_name;
    private final String num_of_sat_test_takers;
    private final String sat_critical_reading_avg_score;
    private final String sat_math_avg_score;
    private final String sat_writing_avg_score;

    public SatScore(String dbn, String school_name, String num_of_sat_test_takers, String sat_critical_reading_avg_score, String sat_math_avg_score, String sat_writing_avg_score) {
        this.dbn = dbn;
        this.school_name = school_name;
        this.num_of_sat_test_takers = num_of_sat_test_takers;
        this.sat_critical_reading_avg_score = sat_critical_reading_avg_score;
        this.sat_math_avg_score = sat_math_avg_score;
        this.sat_writing_avg_score = sat_writing_avg_score;
    }

    public String getDbn() {
        return dbn;
    }

    public String getSchool_name() {
        return school_name;
    }

    public String getNum_of_sat_test_takers() {
        return num_of_sat_test_takers;
    }

    public String getSat_critical_reading_avg_score() {
        return sat_critical_reading_avg_score;
    }

    public String getSat_math_avg_score() {
        return sat_math_avg_score;
    }

    public String getSat_writing_avg_score() {
        return sat_writing_avg_score;
    }
}
