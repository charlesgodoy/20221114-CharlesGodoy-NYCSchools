package com.charlesgodoy.a20221114_charlesgodoy_nycschools;

// Charles G - Adidev

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.charlesgodoy.a20221114_charlesgodoy_nycschools.databinding.ActivityMainBinding;
import com.charlesgodoy.a20221114_charlesgodoy_nycschools.model.SatScore;
import com.charlesgodoy.a20221114_charlesgodoy_nycschools.model.School;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SchoolAdapter.RecyclerViewClickListener listener;
    private SchoolApi schoolApi;
    private List<School> listOfSchools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        schoolApi = NetworkService.getClient().create(SchoolApi.class);

        // Start of retrofit call to generate school list
        Call<List<School>> call = schoolApi.getAllSchools();
        call.enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(Call<List<School>> call, Response<List<School>> response) {

                listOfSchools = response.body();
                setOnClickListner();
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                SchoolAdapter adapter = new SchoolAdapter(listOfSchools, listener);
                binding.recyclerView.setLayoutManager(layoutManager);
                binding.recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<School>> call, Throwable t) {

                t.printStackTrace();
            }
        });


    }

    // Click listener that will start next activity and will bring school's DBN id
    private void setOnClickListner() {
        listener = new SchoolAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                School dbnPosition = listOfSchools.get(position);
                String dbnString = dbnPosition.getDbn();

                Intent intent = new Intent(getApplicationContext(), SatScoreActivity.class);
                intent.putExtra("dbn", dbnString);
                startActivity(intent);

            }
        };
    }
}