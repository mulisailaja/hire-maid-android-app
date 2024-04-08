package com.example.maid;

import com.example.maid.api.response.LoginResponse;
import com.example.maid.api.response.SignUpResponse;
import com.example.maid.api.response.admin.AddCategoryResponse;
import com.example.maid.api.response.admin.AssignedRequestResponse;
import com.example.maid.api.response.admin.ManageMaid;
import com.example.maid.api.response.admin.NewRequestResponse;
import com.example.maid.api.response.customer.BookMaidResponse;
import com.example.maid.api.response.customer.CategoryResponse;
import com.example.maid.api.response.customer.MaidStatusResponse;
import com.example.maid.api.response.maid.AddMaidResponse;
import com.example.maid.api.response.maid.AssignRequestResponse;
import com.example.maid.maid.AddMaidFragment;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(@Field("email")String email,@Field("password")String password,@Field("type")String type);
    @FormUrlEncoded
    @POST("signup.php")
    Call<SignUpResponse> signUp(@Field("user_name")String userName, @Field("user_email")String userEmail,
                                @Field("user_password")String password, @Field("user_phone")String phone,
                                @Field("type")String type);
    @POST("categories.php")
    Call<CategoryResponse> getCategory();
    @FormUrlEncoded
    @POST("book_maid.php")
    Call<BookMaidResponse> bookMaid(@Field("name")String name, @Field("phone")String phone,
                                    @Field("email")String email, @Field("gender")String gender,
                                    @Field("service")String service, @Field("amount")String amount,
                                    @Field("start_time")String startTime, @Field("end_time")String endTime,
                                    @Field("date")String date, @Field("address")String address);
    @FormUrlEncoded
    @POST("maid_status.php")
    Call<MaidStatusResponse> maidStatus(@Field("name")String name,@Field("email")String email);
    @FormUrlEncoded
    @POST("add_maid.php")
    Call<AddMaidResponse> addMaid(@Field("Proficient")String service, @Field("Maid_ID")String maidId,
                                  @Field("Email")String email, @Field("Name")String name, @Field("Gender")String gender,
                                  @Field("Contact_Number")String phone, @Field("Experience")String experience,
                                  @Field("Date_of_Birth")String dateOfBirth, @Field("address")String address,
                                  @Field("Willing_to_Work")String willingToWork, @Field("Work_Locations")String workLocations);
    @FormUrlEncoded
    @POST("assign_request.php")
    Call<AssignRequestResponse> assignRequest(@Field("name")String name);
    @FormUrlEncoded
    @POST("add_categories.php")
    Call<AddCategoryResponse> addCategory(@Field("category_name")String name, @Field("per_hour_amount")int hourlyAmount,
                                          @Field("monthly_amount")int monthlyAmount);
    @POST("manage_maid.php")
    Call<ManageMaid> manageMaid();
    @POST("new_request.php")
    Call<NewRequestResponse> newRequest();
    @POST("approve_request.php")
    Call<AssignedRequestResponse> approvedRequest();
    @POST("cancel_request.php")
    Call<AssignedRequestResponse> canceledRequest();
    @POST("allrequest.php")
    Call<AssignedRequestResponse> allRequest();
    @FormUrlEncoded
    @POST("take_action.php")
    Call<AddCategoryResponse> takeAction(@Field("booking_id")String name, @Field("status")String hourlyAmount,
                                          @Field("assign_to")String monthlyAmount);
    @FormUrlEncoded
    @POST("edit_categori.php")
    Call<AddCategoryResponse> updateCategory(@Field("category_id")String id,@Field("category_name")String name,
                                             @Field("perhour_amount")String hourlyAmount,
                                             @Field("monthly_amount")String monthlyAmount);

    @FormUrlEncoded
    @POST("delete_category.php")
    Call<AddCategoryResponse> deleteCategory(@Field("category_id")String catId);
    @FormUrlEncoded
    @POST("delete_maid.php")
    Call<AddCategoryResponse> deleteMaid(@Field("maid_id")String maidId);
    @FormUrlEncoded
    @POST("maid_take_action.php")
    Call<AddCategoryResponse> maidTakeAction(@Field("booking_id")String id, @Field("status")String status,
                                             @Field("remark")String remark);
    @FormUrlEncoded
    @POST("profile_api.php")
    Call<AddCategoryResponse> profile(@Field("id")String id,@Field("email")String email, @Field("name")String name,
                                             @Field("contact_number")String number,@Field("type")String type);


}
