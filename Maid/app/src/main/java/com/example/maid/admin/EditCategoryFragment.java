package com.example.maid.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.admin.AddCategoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCategoryFragment extends Fragment {

    private AppCompatEditText etCategoryName,etHourlyAmount,etMonthlyAmount;
    private AppCompatButton update;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit_category, container, false);
        etCategoryName=view.findViewById(R.id.categoryName);
        etHourlyAmount=view.findViewById(R.id.perHourAmount);
        etMonthlyAmount=view.findViewById(R.id.monthlyAmount);
        progressBar=view.findViewById(R.id.progress);
        update=view.findViewById(R.id.updateCategory);

        Bundle bundle=getArguments();
        etCategoryName.setText(bundle.getString("catName").toString());
        etHourlyAmount.setText(bundle.getString("catHourlyAmount").toString());
        etMonthlyAmount.setText(bundle.getString("catMonthlyAmount").toString());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=bundle.getString("catId");
                String name=etCategoryName.getText().toString();
                String hAmount=etHourlyAmount.getText().toString();
                String mAmount=etMonthlyAmount.getText().toString();
                if(id.isEmpty()||name.isEmpty()||hAmount.isEmpty()||mAmount.isEmpty())
                {
                    Toast.makeText(getContext(),"Fill All The Field",Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    updateCategoryApi(id,name,hAmount,mAmount);
                }
            }
        });
        return  view;
    }
    private void updateCategoryApi(String id,String catName,String catHourlyAmount,String MonthlyAmount)
    {
        Call<AddCategoryResponse> responseCall= RestClient.makeAPI().updateCategory(id, catName, catHourlyAmount, MonthlyAmount);
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