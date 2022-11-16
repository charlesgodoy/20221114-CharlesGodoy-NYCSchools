package com.charlesgodoy.a20221114_charlesgodoy_nycschools;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class NetworkService {

    public static final String BASEURL = "https://data.cityofnewyork.us/resource/";
    private static Retrofit retrofit = null;

    // Build Retrofit object with all arguments it needs
    static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        return retrofit;
    }

}
