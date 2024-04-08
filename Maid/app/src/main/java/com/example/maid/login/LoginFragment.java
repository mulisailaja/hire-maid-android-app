package com.example.maid.login;

import static android.content.Context.MODE_PRIVATE;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maid.AppConstant;
import com.example.maid.MainActivity;
import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    private TextView loginUs,signUp;
    private EditText etEmail,etPassword;
    private AppCompatButton login;
    private SharedPreferences sf;
    private ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        loginUs=view.findViewById(R.id.loginUs);
        signUp=view.findViewById(R.id.signUp);
        login=view.findViewById(R.id.login);
        etEmail=view.findViewById(R.id.loginEmail);
        progressBar=view.findViewById(R.id.progress);

        etPassword=view.findViewById(R.id.loginPassword);
         sf = getActivity().getSharedPreferences(AppConstant.SHARED_PREF_NAME, MODE_PRIVATE);
        String sfLogInUs = sf.getString(AppConstant.LOGIN_US, null);
        loginUs.setText(sfLogInUs+" Login");
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
               SignInFragment signInFragment= new SignInFragment();
               transaction.replace(R.id.replace1,signInFragment).addToBackStack("signup");
               transaction.commit();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etEmail.getText().toString();
                String passsword=etPassword.getText().toString();
                if(!email.isEmpty() && !passsword.isEmpty()) {
//                    progressBar.setVisibility(View.VISIBLE);
                    loginApi(email,passsword,sfLogInUs);
                }else{
                    Toast.makeText(getContext(),"Fill All The Fields",Toast.LENGTH_SHORT).show();
                }

//                getActivity().finish();
            }
        });
        return  view;
    }
    private void loginApi(String email,String password,String type)
    {
        Call<LoginResponse> responseCall= RestClient.makeAPI().login(email,password,type);
        responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus()==200)
                    {
                        progressBar.setVisibility(View.GONE);
                        LoginResponse.Data response1=response.body().getData();
                        SharedPreferences.Editor editor = sf.edit();
                        editor.putString(AppConstant.KEY_ID, String.valueOf(response1.getUid()));
                        editor.putString(AppConstant.KEY_NAME, String.valueOf(response1.getUsername()));
                        editor.putString(AppConstant.CONTACT_NUMBER, String.valueOf(response1.getUser_mobile()));
                        editor.putString(AppConstant.KEY_EMAIL, String.valueOf(response1.getUseremail()));
                        editor.apply();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        FragmentManager fm = getFragmentManager();
                        fm.popBackStack();
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
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),"check your internet connection",Toast.LENGTH_SHORT).show();
            }
        });
    }
}