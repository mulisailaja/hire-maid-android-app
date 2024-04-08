package com.example.maid.login;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.maid.AppConstant;
import com.example.maid.MainActivity;
import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.LoginResponse;
import com.example.maid.api.response.admin.AddCategoryResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {


    private TextInputEditText name,email,number;
    private ProgressBar progressBar;

    private AppCompatButton update;
    private String email1,name1,number1;
    private SharedPreferences sf;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        number=view.findViewById(R.id.phone);
        update=view.findViewById(R.id.update);
        progressBar=view.findViewById(R.id.progress);
         sf = getActivity().getSharedPreferences(AppConstant.SHARED_PREF_NAME, MODE_PRIVATE);
        String type = sf.getString(AppConstant.LOGIN_US, null);
         email1 = sf.getString(AppConstant.KEY_EMAIL, null);
         name1 = sf.getString(AppConstant.KEY_NAME, null);
        number1 = sf.getString(AppConstant.CONTACT_NUMBER, null);
        String id = sf.getString(AppConstant.KEY_ID, null);
        this.name.setText(name1);
        this.email.setText(email1);
        this.number.setText(number1);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email1=email.getText().toString();
                name1=name.getText().toString();
                number1=number.getText().toString();
                if(email1.isEmpty()||name1.isEmpty()||number1.isEmpty())
                {
                    Toast.makeText(getContext(),"Fill All The Fields",Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    profileApi(id,email1,name1,number1,type);
                }
            }
        });

        return  view;
    }
    private void profileApi(String id,String email,String name,String number,String type)
    {
        Call<AddCategoryResponse> responseCall= RestClient.makeAPI().profile(id,email,name,number,type);
        responseCall.enqueue(new Callback<AddCategoryResponse>() {
            @Override
            public void onResponse(Call<AddCategoryResponse> call, Response<AddCategoryResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus()==200)
                    {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sf.edit();
                        editor.putString(AppConstant.KEY_NAME,name );
                        editor.putString(AppConstant.CONTACT_NUMBER, number);
                        editor.putString(AppConstant.KEY_EMAIL, email);
                        editor.apply();
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