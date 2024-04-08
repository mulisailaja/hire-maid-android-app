package com.example.maid.login;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maid.AppConstant;
import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.LoginResponse;
import com.example.maid.api.response.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInFragment extends Fragment {
    private EditText tvUserName,tvEmail,tvPassword,tvReEnterPassword,tvPhone,tvLogin;
    private AppCompatButton bCreateAccount;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_in, container, false);
        bCreateAccount=view.findViewById(R.id.createAccount);
        tvUserName=view.findViewById(R.id.userName);
        tvEmail=view.findViewById(R.id.email);
        tvPhone=view.findViewById(R.id.phone);
        tvPassword=view.findViewById(R.id.password);
        tvReEnterPassword=view.findViewById(R.id.con_password);
        SharedPreferences sf = getActivity().getSharedPreferences(AppConstant.SHARED_PREF_NAME, MODE_PRIVATE);
        String sfLogInUs = sf.getString(AppConstant.LOGIN_US, null);
        bCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=tvUserName.getText().toString();
                String email=tvEmail.getText().toString();
                String phone=tvPhone.getText().toString();
                String password=tvPassword.getText().toString();
                String conPassword=tvReEnterPassword.getText().toString();
                if(userName.isEmpty()||email.isEmpty()||phone.isEmpty()||password.isEmpty()||conPassword.isEmpty())
                {
                    Toast.makeText(getContext(),"Fill All The Fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(conPassword)){
                        signUpApi(userName,email,phone,password,sfLogInUs);
                    }
                    else{
                        Toast.makeText(getContext(),"Password Not Not Matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return  view;
    }
    private void signUpApi(String userName,String email,String phone,String password,String type)
    {
        Call<SignUpResponse> responseCall= RestClient.makeAPI().signUp(userName,email,phone,password,type);
        responseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus()==200)
                    {
                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        FragmentManager fm = getFragmentManager(); // or 'getSupportFragmentManager();'
//                int count = fm.getBackStackEntryCount();
                        fm.popBackStack();
                    }
                    else{
                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getContext(),"server busy",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(getContext(),"check your internet connection",Toast.LENGTH_SHORT).show();
            }
        });
    }
}