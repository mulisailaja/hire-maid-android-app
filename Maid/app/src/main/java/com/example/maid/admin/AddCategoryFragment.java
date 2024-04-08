package com.example.maid.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.maid.API;
import com.example.maid.AppConstant;
import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.admin.AddCategoryResponse;
import com.example.maid.api.response.customer.CategoryResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AddCategoryFragment extends Fragment {
    private AppCompatEditText etCategoryName,etHourlyAmount,etMonthlyAmount;
    private AppCompatButton add;
    private ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_category, container, false);
        etCategoryName=view.findViewById(R.id.categoryName);
        etHourlyAmount=view.findViewById(R.id.perHourAmount);
        etMonthlyAmount=view.findViewById(R.id.monthlyAmount);
        progressBar=view.findViewById(R.id.progress);
        add=view.findViewById(R.id.addCategory);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sName=etCategoryName.getText().toString();
                String shAmount=etHourlyAmount.getText().toString();
                String smAmount=etMonthlyAmount.getText().toString();
                if(sName.isEmpty()||shAmount.isEmpty()||smAmount.isEmpty())
                {
                    Toast.makeText(getContext(),"Fill All The Field",Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    addCategoryApi(sName,Integer.parseInt(shAmount),Integer.parseInt(smAmount));
                }
            }
        });
        return  view;
    }
    private void addCategoryApi(String name,int hourlyAmount,int monthlyAmount)
    {
        Call<AddCategoryResponse> responseCall= RestClient.makeAPI().addCategory(name,hourlyAmount,monthlyAmount);
        responseCall.enqueue(new Callback<AddCategoryResponse>() {
            @Override
            public void onResponse(Call<AddCategoryResponse> call, Response<AddCategoryResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus()==200)
                    {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(),"server busy",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AddCategoryResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),"check your internet connection",Toast.LENGTH_SHORT).show();

            }
        });
    }
}