package com.example.maid;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

public class AppConstant {

    public static final String LOGIN_US="loginUs";
    public static final String BASE_URL = "  https://baf7-2401-4900-6287-8ca0-411-f58e-78a2-6101.ngrok-free.app/Maid/mhms/api/";
    public static final String SHARED_PREF_NAME = "maidpref";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_TYPE = "type";
    public static final String CONTACT_NUMBER = "number";
    public static final String KEY_BOOKING_ID="booking_Id";
    public static final String[] GENDER = { "Male","Female" };
    public static final String[] WAGES = { "Hourly","Monthly" };
    public static final String[] WILLING_TO_WORK={ "Yes","No" };
    public static final String[] TAKE_ACTION={ "Approved" , "Cancelled" };

}
