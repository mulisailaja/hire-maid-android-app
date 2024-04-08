package com.example.maid.maid;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.maid.AppConstant;
import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.customer.CategoryResponse;
import com.example.maid.api.response.maid.AddMaidResponse;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class AddMaidFragment extends Fragment {

    private AppCompatEditText etMaid_ID,etEmail,etName,etContact_Number,etExperience,etDate_of_Birth,etAddress,Work_Locations;
    private AppCompatButton bAdd;
    private AutoCompleteTextView tvProficient,tvGender,tvWilling_to_Work;
    private String gender,sWilliToWork,sService,stringDate;
    private String[] service;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add_maid, container, false);
        tvProficient=view.findViewById(R.id.services);
        tvGender = view.findViewById(R.id.gender);
        tvWilling_to_Work=view.findViewById(R.id.willingToWork);
        etMaid_ID=view.findViewById(R.id.maidID);
        etEmail=view.findViewById(R.id.email);
        etName=view.findViewById(R.id.name);
        etContact_Number=view.findViewById(R.id.phone);
        etDate_of_Birth=view.findViewById(R.id.dateOfBirth);
        etAddress=view.findViewById(R.id.location);
        etExperience=view.findViewById(R.id.experience);
        progressBar=view.findViewById(R.id.progress);
        Work_Locations=view.findViewById(R.id.preferredWorkLocations);
        bAdd=view.findViewById(R.id.add);
        categoryApi();
        dropDowns();
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serv = sService;
                String gend = gender;
                String wtw = sWilliToWork;
                String mid = etMaid_ID.getText().toString();
                String emil = etEmail.getText().toString();
                String nam = etName.getText().toString();
                String phon = etContact_Number.getText().toString();
                String dob = etDate_of_Birth.getText().toString();
                String addss = etAddress.getText().toString();
                String wl = Work_Locations.getText().toString();
                String exprs = etExperience.getText().toString();
                if (serv.isEmpty() || gend.isEmpty() || wtw.isEmpty() || mid.isEmpty() || emil.isEmpty() || nam.isEmpty()
                        || phon.isEmpty() || dob.isEmpty() || addss.isEmpty() || wl.isEmpty() || exprs.isEmpty())
                {
                    Toast.makeText(getContext(),"Fill All The Fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    addMaidApi(serv,mid,emil,nam,gend,phon,exprs,dob,addss,wtw,wl);
                }
            }
        });
        etDate_of_Birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });

        return view;
    }
    private void getDate()
    {
        // on below line we are getting
        // the instance of our calendar.
        final Calendar c = Calendar.getInstance();

        // on below line we are getting
        // our day, month and year.
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // on below line we are creating a variable for date picker dialog.
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                // on below line we are passing context.
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // on below line we are setting date to our edit text.
                        stringDate=year + "-" + (monthOfYear + 1) + "-" +  dayOfMonth;
                        etDate_of_Birth.setText(stringDate);
                    }
                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                year, month, day);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();
    }
    private void addMaidApi(String service, String maidId,String email,String name,String gender,String phone,
                            String experience, String dateOfBirth,String address,String willingToWork,String workLocations)
    {
        Call<AddMaidResponse> responseCall=RestClient.makeAPI().addMaid(service, maidId, email, name,
                gender, phone, experience, dateOfBirth, address, willingToWork, workLocations);
        responseCall.enqueue(new Callback<AddMaidResponse>() {
            @Override
            public void onResponse(Call<AddMaidResponse> call, Response<AddMaidResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus()==200){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    } else {
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
            public void onFailure(Call<AddMaidResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),"check your internet connection",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void dropDowns()
    {
        ArrayAdapter<String> aGender = new ArrayAdapter<String>(getContext(), R.layout.login_as_item, AppConstant.GENDER);
        tvGender.setAdapter(aGender);
        tvGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gender= AppConstant.GENDER[position];
            }
        });
        ArrayAdapter<String> willi = new ArrayAdapter<String>(getContext(), R.layout.login_as_item, AppConstant.WILLING_TO_WORK);
        tvWilling_to_Work.setAdapter(willi);
        tvWilling_to_Work.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sWilliToWork= AppConstant.WILLING_TO_WORK[position];
            }
        });
        tvProficient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sService= service[position];
            }
        });
    }
    private void categoryApi()
    {
        Call<CategoryResponse> responseCall= RestClient.makeAPI().getCategory();
        responseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus()==200)
                    {
                        ArrayList<CategoryResponse.CategoryInnerResponse> ci=response.body().getData();
                        service=new String[ci.size()];
                        for(int i=0;i<ci.size();i++){
                            service[i]=ci.get(i).getCategory_name();
                        }
                        ArrayAdapter<String> aServices = new ArrayAdapter<String>(getContext(), R.layout.login_as_item, service);
                        tvProficient.setAdapter(aServices);
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
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(getContext(),"check your internet connection",Toast.LENGTH_SHORT).show();
            }
        });
    }
}