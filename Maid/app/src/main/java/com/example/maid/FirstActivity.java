package com.example.maid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.maid.login.LoginFragment;

public class FirstActivity extends AppCompatActivity {

    private AppCompatButton bAdmin,bCustomer,bMaid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        bAdmin=findViewById(R.id.admin);
        bCustomer=findViewById(R.id.customer);
        bMaid=findViewById(R.id.maid);
        SharedPreferences sf = getSharedPreferences(AppConstant.SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        bAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                editor.putString(AppConstant.LOGIN_US,bAdmin.getText().toString());
                editor.apply();
               LoginFragment loginFragment= new LoginFragment();
               transaction.replace(R.id.replace1, loginFragment).addToBackStack("login");
               transaction.commit();
            }
        });
        bCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                editor.putString(AppConstant.LOGIN_US,bCustomer.getText().toString());
                editor.apply();
                LoginFragment loginFragment= new LoginFragment();
                transaction.replace(R.id.replace1, loginFragment).addToBackStack("login");
                transaction.commit();
            }
        });
        bMaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                editor.putString(AppConstant.LOGIN_US,bMaid.getText().toString());
                editor.apply();
                LoginFragment loginFragment= new LoginFragment();
                transaction.replace(R.id.replace1, loginFragment).addToBackStack("login");
                transaction.commit();
            }
        });

    }

}