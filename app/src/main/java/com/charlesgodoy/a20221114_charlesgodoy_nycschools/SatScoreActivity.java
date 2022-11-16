package com.charlesgodoy.a20221114_charlesgodoy_nycschools;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.charlesgodoy.a20221114_charlesgodoy_nycschools.databinding.ActivitySatScoreBinding;
import com.charlesgodoy.a20221114_charlesgodoy_nycschools.model.SatScore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SatScoreActivity extends AppCompatActivity {

    private ActivitySatScoreBinding binding;
    SchoolApi schoolApi;
    List<SatScore> listOfSatScores;
    String dbnString;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySatScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        schoolApi = NetworkService.getClient().create(SchoolApi.class);

        // Gets DBN id and saves it as string
        retrieveDbnString();

        // Retrofit call that compares DBN id from from first JSON, then search and compares to second JSON
        // If found, setText to views
        // If not found, show AlertDialog and return to previous Activity
        retrieveSatScores(dbnString);

    }

    // Gets DBN id and saves it as string
    private void retrieveDbnString() {

        Intent intent = getIntent();
        if (intent != null) {

            dbnString = intent.getStringExtra("dbn");
        }

    }

    // If DBN not found, then display AlertDialog and go back to previous activity
    private void dbnNotFound() {

        builder = new AlertDialog.Builder(this);

        builder.setMessage("SAT scores were not found for selected School. \nPlease select another school.")
                .setCancelable(false)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        Toast.makeText(getApplicationContext(), "Select a new school",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("DBN Not Found");
        alert.show();
    }

    // Retrofit call that compares DBN id from from first JSON, then search and compares to second JSON
    // If found, setText to views
    // If not found, show AlertDialog and return to previous Activity
    private void retrieveSatScores(String dbnString) {

        Call<List<SatScore>> callSat = schoolApi.getAllSatScores();
        callSat.enqueue(new Callback<List<SatScore>>() {
            @Override
            public void onResponse(Call<List<SatScore>> call, Response<List<SatScore>> response) {

                listOfSatScores = response.body();
                for (int i = 0; i < listOfSatScores.size(); i++) {
                    if (dbnString.equals(listOfSatScores.get(i).getDbn())) {

                        binding.tvDbnSchool.setText(listOfSatScores.get(i).getSchool_name());
                        binding.tvReading.setText(listOfSatScores.get(i).getSat_critical_reading_avg_score());
                        binding.tvMath.setText(listOfSatScores.get(i).getSat_math_avg_score());
                        binding.tvWriting.setText(listOfSatScores.get(i).getSat_writing_avg_score());

                        return;
                    }
                }
                // If DBN not found, then display AlertDialog and go back to previous activity
                dbnNotFound();
            }

            @Override
            public void onFailure(Call<List<SatScore>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}