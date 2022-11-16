package com.charlesgodoy.a20221114_charlesgodoy_nycschools;

import com.charlesgodoy.a20221114_charlesgodoy_nycschools.model.SatScore;
import com.charlesgodoy.a20221114_charlesgodoy_nycschools.model.School;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SchoolApi {

    @GET("s3k6-pzi2.json")
    Call<List<School>> getAllSchools();

    @GET("f9bf-2cp4.json")
    Call<List<SatScore>> getAllSatScores();

}