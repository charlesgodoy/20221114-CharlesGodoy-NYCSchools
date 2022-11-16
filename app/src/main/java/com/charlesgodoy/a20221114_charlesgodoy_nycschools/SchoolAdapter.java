package com.charlesgodoy.a20221114_charlesgodoy_nycschools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.charlesgodoy.a20221114_charlesgodoy_nycschools.databinding.ItemSchoolBinding;
import com.charlesgodoy.a20221114_charlesgodoy_nycschools.model.School;

import java.util.List;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.MyViewholder> {

    private List<School> listOfSchools;
    private RecyclerViewClickListener listener;

    public SchoolAdapter(List<School> listOfSchools, RecyclerViewClickListener listener) {
        this.listOfSchools = listOfSchools;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SchoolAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewholder(ItemSchoolBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolAdapter.MyViewholder holder, int position) {

        School school = listOfSchools.get(position);
        holder.itemSchoolBinding.tvSchoolName.setText(school.getSchool_name());

    }

    @Override
    public int getItemCount() {
        return listOfSchools.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    public class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ItemSchoolBinding itemSchoolBinding;

        public MyViewholder(ItemSchoolBinding itemSchoolBinding) {
            super(itemSchoolBinding.getRoot());

            this.itemSchoolBinding = itemSchoolBinding;
            this.itemSchoolBinding.tvSchoolName.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            listener.onClick(view, getAdapterPosition());

        }
    }
}
