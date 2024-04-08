package com.example.maid.maid;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.maid.AppConstant;
import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.admin.AddCategoryResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaidTakeActionFragment extends Fragment {
    private AutoCompleteTextView tvStatus;
    private TextInputEditText tie;
    private String status;
    private AppCompatButton button;
    private ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_maid_take_action, container, false);
        tvStatus=view.findViewById(R.id.status);
        button=view.findViewById(R.id.update);
        tie=view.findViewById(R.id.remark);
        progressBar=view.findViewById(R.id.progress);
        ArrayAdapter<String> aGender = new ArrayAdapter<String>(getContext(), R.layout.login_as_item, AppConstant.TAKE_ACTION);
        tvStatus.setAdapter(aGender);
        tvStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                status = AppConstant.TAKE_ACTION[position];
            }
        });
        Bundle bundle=getArguments();
        String bookId=bundle.getString("bookId");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String remark=tie.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                takeActionApi(bookId,status,remark);
            }
        });

        return  view;
    }
    private void takeActionApi(String bid,String remark,String status){
        Call<AddCategoryResponse> responseCall= RestClient.makeAPI().maidTakeAction(bid,remark,status);
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
                    else{
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }

                }else{
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