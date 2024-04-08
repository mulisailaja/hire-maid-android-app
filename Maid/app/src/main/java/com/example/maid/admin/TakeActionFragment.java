package com.example.maid.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.maid.AppConstant;
import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.admin.AddCategoryResponse;
import com.example.maid.api.response.admin.ManageMaid;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TakeActionFragment extends Fragment {
    private AutoCompleteTextView tvStatus,tvAssignTo;
    private AppCompatButton update;
    private String status,assign,bookId;
    private TextInputLayout til;
    private ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_take_action, container, false);
        tvStatus=view.findViewById(R.id.status);
        tvAssignTo=view.findViewById(R.id.assignTo);
        update=view.findViewById(R.id.update);
        til=view.findViewById(R.id.show);
        progressBar=view.findViewById(R.id.progress);
        ArrayAdapter<String> aGender = new ArrayAdapter<String>(getContext(), R.layout.login_as_item, AppConstant.TAKE_ACTION);
        tvStatus.setAdapter(aGender);
        Bundle bundle=getArguments();
        bookId=bundle.getString("bookId");
        tvStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                status= AppConstant.TAKE_ACTION[position];
                if(status.equalsIgnoreCase("Approved")){
                    til.setVisibility(View.VISIBLE);
                }else{
                    til.setVisibility(View.GONE);
                    assign="default";
                }
            }
        });
        Call<ManageMaid> responseCall= RestClient.makeAPI().manageMaid();
        responseCall.enqueue(new Callback<ManageMaid>() {
            @Override
            public void onResponse(Call<ManageMaid> call, Response<ManageMaid> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus()==200)
                    {
                        String[] maidName=new String[response.body().getData().size()];
                        for(int i=0;i<response.body().getData().size();i++) {
                            ManageMaid.ManageMaidInnerClass mis = response.body().getData().get(i);
                            maidName[i]=mis.getName();
                        }
                        ArrayAdapter<String> assig = new ArrayAdapter<String>(getContext(), R.layout.login_as_item, maidName);
                        tvAssignTo.setAdapter(assig);
                        tvAssignTo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                assign= maidName[position];
                            }
                        });
                    }
                    else {
                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getContext(),"server busy",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ManageMaid> call, Throwable t) {
                Toast.makeText(getContext(),"check your internet connection",Toast.LENGTH_SHORT).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                apiCall(bookId,status,assign);
            }
        });

        return view;
    }
    private void apiCall(String id,String status,String assignTo)
    {
        Call<AddCategoryResponse> responseCall= RestClient.makeAPI().takeAction(id, status, assignTo);
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