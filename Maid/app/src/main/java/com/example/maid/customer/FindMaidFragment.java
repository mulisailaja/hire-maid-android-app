package com.example.maid.customer;

import static android.content.Context.MODE_PRIVATE;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.CaseMap;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.maid.API;
import com.example.maid.AppConstant;
import com.example.maid.MainActivity;
import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.LoginResponse;
import com.example.maid.api.response.customer.BookMaidResponse;
import com.example.maid.api.response.customer.CategoryResponse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public class FindMaidFragment extends Fragment {
    private String[] courses ;
    private  final String H="200",TT="3000";
    private String[] wages=AppConstant.WAGES;
    private String[] hourlyAmount;
    private String[] monthlyAmount;
    private AppCompatEditText amount,etDate,etName,etPhone,etEmail,etStartTime,etEndTime,etAddress;
    private String service="",wagesSelection="",stringDate;
    private AppCompatButton bSend;
    private AutoCompleteTextView wages1,gender,services;
    private SharedPreferences sf;
    private View view;
    private String endTime24HourFormate,endTime12HourFormate;
    private String startTime24HourFormate,startTime12HourFormate,amountForTime;
    private String gender1,timeAm,TimePm,normalHour,normalHourTwo;
    private int stHour=0,stMinutes=0;
    private int endHour=0,endMinutes=0;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_find_maid, container, false);
        amount=view.findViewById(R.id.amount);
        gender = view.findViewById(R.id.gender);
        etName = view.findViewById(R.id.name);
        etEmail = view.findViewById(R.id.email);
        etStartTime = view.findViewById(R.id.startTime);
        etEndTime = view.findViewById(R.id.endTime);
        etDate = view.findViewById(R.id.date);
        etAddress = view.findViewById(R.id.address);
        bSend = view.findViewById(R.id.send);

        etPhone=view.findViewById(R.id.phone);
        services= view.findViewById(R.id.services);
        wages1 = view.findViewById(R.id.wages);
        progressBar=view.findViewById(R.id.progress);
        sf = getActivity().getSharedPreferences(AppConstant.SHARED_PREF_NAME, MODE_PRIVATE);
        String username = sf.getString(AppConstant.KEY_NAME, null);
        String usermail = sf.getString(AppConstant.KEY_EMAIL, null);
        etName.setText(username);
        etEmail.setText(usermail);
        categoryApi();

        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etName.getText().toString();
                String phone= etPhone.getText().toString();
                String email= etEmail.getText().toString();
                String ser= service;
                String amoun= amount.getText().toString();
                String stime=etStartTime.getText().toString();
                String etime=etEndTime.getText().toString();
                String dt=etDate.getText().toString();
                String addrs=etAddress.getText().toString();
                if(name.isEmpty()||phone.isEmpty()||email.isEmpty()||ser.isEmpty()||amoun.isEmpty()||stime.isEmpty()
                ||etime.isEmpty()||dt.isEmpty()||addrs.isEmpty()||gender1.isEmpty())
                {
                    Toast.makeText(getContext(),"Fill All The Fields",Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    bookMaid(name,phone,email,gender1,ser,amoun,stime,etime,dt,addrs);
                }
            }
        });
        gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gender1=AppConstant.GENDER[position];

            }
        });
        services.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                service=courses[position];
                setAmount(service,wagesSelection);
                if(startTime12HourFormate!=null && startTime24HourFormate!=null && amountForTime!=null)
                {
                    amountCalculation(startTime12HourFormate,endTime12HourFormate,startTime24HourFormate,endTime24HourFormate,amountForTime);
                }
            }
        });
        wages1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                wagesSelection=AppConstant.WAGES[position];
                setAmount(service,wagesSelection);
                if(startTime12HourFormate!=null && startTime24HourFormate!=null && amountForTime!=null)
                {
                    amountCalculation(startTime12HourFormate,endTime12HourFormate,startTime24HourFormate,endTime24HourFormate,amountForTime);
                }
            }
        });
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });
        TimePickerDialog.OnTimeSetListener startTimeListener =new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // TODO Auto-generated method stub
                ViewGroup vg=(ViewGroup) view.getChildAt(0);
                stHour = hourOfDay;
                stMinutes = minute;
//                String am_pm = (hourOfDay < 12) ? "AM" : "PM";
                 startTime24HourFormate = stHour +":"+ stMinutes;
                startTime12HourFormate= time(startTime24HourFormate);
                etStartTime.setText(startTime12HourFormate);
                if(endTime12HourFormate!=null && endTime24HourFormate!=null && amountForTime!=null)
                {
                    amountCalculation(startTime12HourFormate,endTime12HourFormate,startTime24HourFormate,endTime24HourFormate,amountForTime);
                }
            }
        };
        TimePickerDialog.OnTimeSetListener endTimeListener =new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // TODO Auto-generated method stub
                ViewGroup vg=(ViewGroup) view.getChildAt(0);
                endHour = hourOfDay;
                endMinutes = minute;
//                String am_pm = (hourOfDay < 12) ? "AM" : "PM";
                 endTime24HourFormate = endHour +":"+ endMinutes;
                endTime12HourFormate=time(endTime24HourFormate);
                etEndTime.setText(endTime12HourFormate);

                if(startTime12HourFormate!=null && startTime24HourFormate!=null && amountForTime!=null)
                {
                    amountCalculation(startTime12HourFormate,endTime12HourFormate,startTime24HourFormate,endTime24HourFormate,amountForTime);
                }
//                amountCalculation();
            }
        };
        etStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), startTimeListener, stHour, stMinutes, false).show();
            }
        });
        etEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), endTimeListener, endHour, endMinutes, false).show();
            }
        });

        return view;
    }
    private String time(String time)
    {
        String input = time;
        //Date/time pattern of input date
        DateFormat df = new SimpleDateFormat("HH:mm");
        //Date/time pattern of desired output date
        DateFormat outputformat = new SimpleDateFormat("hh:mm aa");
        Date date = null;
        String output = null;
        try{
            //Conversion of input String to date
            date= df.parse(input);
            //old date format to new date format
            output = outputformat.format(date);
        }catch(ParseException pe){
            pe.printStackTrace();
        }
        return output;

    }
    public  void amountCalculation(String sTime,String eTime,String startTime,String endTime,String amount)
    {
        String[] h=sTime.split("[':'|' ']");
        int hour=Integer.parseInt(h[0]);
        int min=Integer.parseInt(h[1]);
        String[] eh=eTime.split("[':'|' ']");
        int ehour=Integer.parseInt(eh[0]);
        int emin=Integer.parseInt(eh[1]);
        for(int i=0;i<h.length;i++)
        {
            System.out.println(h[i]);
        }
        for(int i=0;i<eh.length;i++)
        {
            System.out.println(eh[i]);
        }

        minitsCalculation(hour,min,h[2],ehour,emin,eh[2],startTime,endTime,amount);
    }
    private  void minitsCalculation(int h,int m,String ap,int eh,int em,String ap1,String startTime,String endTime,String amount)
    {
        if((h>=7 && ap.equalsIgnoreCase("am")) || (h<=5 && ap.equalsIgnoreCase("pm")))
        {
            if(((eh>=7 && ap1.equalsIgnoreCase("am")) || (eh<=5 && ap1.equalsIgnoreCase("pm"))))
            {
                String[] stime=startTime.split(":");
                String[] etime=endTime.split(":");
                int shc=0;
                int ehc=0;
                for(int i=Integer.parseInt(stime[0]);i<Integer.parseInt(etime[0]);i++)
                {
                    shc++;
                }
                int minits=(shc*60)+(Integer.parseInt(stime[1])+Integer.parseInt(etime[1]));
                float perMinitsAmount=(float)Integer.parseInt(amount)/60;
                int calculate=(int)(perMinitsAmount*minits);
                this.amount.setText(""+calculate);
            }
            else {
                etStartTime.setText(null);
                etEndTime.setText(null);
                Toast.makeText(getContext(),"inner if starting time 7 AM, ending time 5 PM",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            etStartTime.setText(null);
            etEndTime.setText(null);
            Toast.makeText(getContext(),"outer if starting time 7 AM, ending time 5 PM",Toast.LENGTH_SHORT).show();
        }
    }
    public  String time1(String s)
    {
        //Input date in String format
        String input = s;
        //Date/time pattern of input date
        DateFormat df = new SimpleDateFormat("HH:mm");
        //Date/time pattern of desired output date
        DateFormat outputformat = new SimpleDateFormat("hh:mm aa");
        Date date = null;
        String output = null;
        try{
            //Conversion of input String to date
            date= df.parse(input);
            //old date format to new date format
            output = outputformat.format(date);
//	    	  System.out.println(output);
        }catch(ParseException pe){
            pe.printStackTrace();
        }
        return output;
    }

    private void bookMaid(String name,String phone,String email,String gender,String service,String amount,String startTime,
                          String endTime,String date,String address)
    {
//        Log.e(TAG,name+" "+phone+" "+email+" "+gender+" "+service+" "+amount+" "+startTime+
//                " "+endTime+" "+date+" "+address);
        Call<BookMaidResponse> responseCall=RestClient.makeAPI().bookMaid(name,phone,email,gender,service,amount,startTime,
                endTime,date,address);
        responseCall.enqueue(new Callback<BookMaidResponse>() {
            @Override
            public void onResponse(Call<BookMaidResponse> call, Response<BookMaidResponse> response) {
                if(response.isSuccessful())
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(),"server busy",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BookMaidResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),"check your internet connection",Toast.LENGTH_SHORT).show();
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
                        courses=new String[ci.size()];
                        hourlyAmount=new String[ci.size()];
                        monthlyAmount=new String[ci.size()];
                        for(int i=0;i<ci.size();i++){
                            courses[i]=ci.get(i).getCategory_name();
                            hourlyAmount[i]=ci.get(i).getPer_hour_amount();
                            monthlyAmount[i]=ci.get(i).getMonthly_amount();
                        }
                        ArrayAdapter<String> aGender = new ArrayAdapter<String>(getContext(), R.layout.login_as_item, AppConstant.GENDER);
                        gender.setAdapter(aGender);
                        ArrayAdapter<String> aWages = new ArrayAdapter<String>(getContext(), R.layout.login_as_item, AppConstant.WAGES);
                        wages1.setAdapter(aWages);
                        ArrayAdapter<String> aServices = new ArrayAdapter<String>(getContext(), R.layout.login_as_item, courses);
                        services.setAdapter(aServices);
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
                         etDate.setText(stringDate);
                    }
                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                year, month, day);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();
    }
    private void setAmount(String service,String wagesin)
    {
        if(startTime12HourFormate!=null && startTime24HourFormate!=null && amountForTime!=null)
        {
            amountCalculation(startTime12HourFormate,endTime12HourFormate,startTime24HourFormate,endTime24HourFormate,amountForTime);
        }
        for(int i=0;i<courses.length;i++)
        {
            if(service.equalsIgnoreCase(courses[i]))
            {
                for(int j=0;j<wages.length;j++) {
                    if (wagesin.equalsIgnoreCase(wages[0])) {
                        amountForTime=hourlyAmount[i];
                        amount.setText(hourlyAmount[i]);
                    }else if (wagesin.equalsIgnoreCase(wages[1])) {
                        amountForTime=monthlyAmount[i];
                        amount.setText(monthlyAmount[i]);
                    }
                }
            }
        }
    }
}