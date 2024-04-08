package com.example.maid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maid.admin.AdminHomeFragment;
import com.example.maid.admin.CategoryFragment;
import com.example.maid.admin.MaidFragment;
import com.example.maid.admin.NewRequestFragment;
import com.example.maid.admin.RequestFragment;
import com.example.maid.api.response.admin.AddCategoryResponse;
import com.example.maid.customer.CustomerHomeFragment;
import com.example.maid.customer.FindMaidFragment;
import com.example.maid.customer.MaidStatusFragment;
import com.example.maid.customer.ServicesFragment;
import com.example.maid.login.ProfileFragment;
import com.example.maid.maid.AddMaidFragment;
import com.example.maid.maid.AssignRequestFragment;
import com.example.maid.maid.MaidHomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.razorpay.PaymentResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    SharedPreferences sf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().clear();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.nav_open, R.string.nav_close);
        drawer.addDrawerListener(toggle);
        toolbar.setTitleTextColor(Color.WHITE);
        toggle.syncState();
        View view= navigationView.inflateHeaderView(R.layout.header_main);
        sf = getSharedPreferences(AppConstant.SHARED_PREF_NAME, MODE_PRIVATE);
        String user_type = sf.getString(AppConstant.LOGIN_US, null);
        TextView text=view.findViewById(R.id.textView123);
        String name=sf.getString(AppConstant.KEY_EMAIL, null);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        if(user_type != null)
        {
            if(user_type.equalsIgnoreCase("customer"))
            {
                text.setText(name);
                navigationView.inflateMenu(R.menu.customer_menu);
                tx.replace(R.id.frameLayout, new ServicesFragment());
                tx.commit();
            }else if(user_type.equalsIgnoreCase("maid")){
                navigationView.inflateMenu(R.menu.maid_menu);
                text.setText(name);
                tx.replace(R.id.frameLayout, new MaidHomeFragment());
                tx.commit();
            }else if(user_type.equalsIgnoreCase("admin")){
                navigationView.inflateMenu(R.menu.admin_menu);
                text.setText(name);
                tx.replace(R.id.frameLayout, new AdminHomeFragment());
                tx.commit();
            }

        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                FragmentManager fm = getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                if(id == R.id.home)
                {
                    getSupportActionBar().setTitle("Home");
                    tx.replace(R.id.frameLayout,new ServicesFragment()).addToBackStack("1");
                    tx.commit();
                }
                else if(id==R.id.maid_home)
                {
                    getSupportActionBar().setTitle("Home");
                    tx.replace(R.id.frameLayout,new MaidHomeFragment()).addToBackStack("1");
                    tx.commit();
                }
                else if(id==R.id.adminHome)
                {
                    getSupportActionBar().setTitle("Home");
                    tx.replace(R.id.frameLayout,new AdminHomeFragment()).addToBackStack("1");
                    tx.commit();
                }
                else if(id==R.id.find_maid)
                {
                    getSupportActionBar().setTitle("Find a Maid");
                    tx.replace(R.id.frameLayout,new FindMaidFragment()).addToBackStack("1");
                    tx.commit();
                }
                else if(id==R.id.logout)
                {
                    finish();
                }
                else if(id==R.id.service)
                {
                    getSupportActionBar().setTitle("Services");
                    tx.replace(R.id.frameLayout,new CustomerHomeFragment()).addToBackStack("1");
                    tx.commit();
                }
                else if(id==R.id.maid_status)
                {
                    getSupportActionBar().setTitle("Maid Status");
                    tx.replace(R.id.frameLayout,new MaidStatusFragment()).addToBackStack("1");
                    tx.commit();
                }
                else if(id==R.id.addMaid)
                {
                    getSupportActionBar().setTitle("Add Maid");
                    tx.replace(R.id.frameLayout,new AddMaidFragment()).addToBackStack("1");
                    tx.commit();
                }
                else if(id==R.id.assignRequest)
                {
                    getSupportActionBar().setTitle("Assign Request");
                    tx.replace(R.id.frameLayout,new AssignRequestFragment()).addToBackStack("1");
                    tx.commit();
                }
                else if(id==R.id.maid)
                {
                    getSupportActionBar().setTitle("Assign Request");
                    tx.replace(R.id.frameLayout,new MaidFragment()).addToBackStack("1");
                    tx.commit();
                }
                else if(id==R.id.category)
                {
                    getSupportActionBar().setTitle("Category");
                    tx.replace(R.id.frameLayout,new CategoryFragment()).addToBackStack("1");
                    tx.commit();
                }
                else if(id==R.id.request)
                {
                    getSupportActionBar().setTitle("Request");
                    tx.replace(R.id.frameLayout,new RequestFragment()).addToBackStack("1");
                    tx.commit();
                }
                else if(id==R.id.profile)
                {
                    getSupportActionBar().setTitle("Profile");
                    tx.replace(R.id.frameLayout,new ProfileFragment()).addToBackStack("1");
                    tx.commit();
                }

                drawer.closeDrawers();

                return true;
            }
        });

    }
//    public void onBackPressed() {
//        super.onBackPressed();
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setMessage("Do you want to exit ?");
//        builder.setTitle("Alert !");
//        builder.setCancelable(false);
//        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
//            finish();
//        });
//        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
//            dialog.cancel();
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(MainActivity.this,"Payment Success",Toast.LENGTH_SHORT).show();
        sf=getSharedPreferences(AppConstant.SHARED_PREF_NAME,MODE_PRIVATE);
        String bid=sf.getString(AppConstant.KEY_BOOKING_ID,null);
        apiCall(bid,"Paid","amount");
//        String amount=sf.getString()
//        apiCall()
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
                        Toast.makeText(MainActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"server busy",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AddCategoryResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"check your internet connection",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(MainActivity.this,"Payment Error",Toast.LENGTH_SHORT).show();
    }
}