package com.example.maid.customer;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maid.AppConstant;
import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.customer.MaidStatusResponse;
import com.google.android.material.imageview.ShapeableImageView;
import com.razorpay.Checkout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaidStatusFragment extends Fragment {

    private ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_maid_status, container, false);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        RecyclerView recyclerView=view.findViewById(R.id.maidStatusRecyclerView);
        SharedPreferences sf = getActivity().getSharedPreferences(AppConstant.SHARED_PREF_NAME, MODE_PRIVATE);
        String username = sf.getString(AppConstant.KEY_NAME, null);
        String usermail = sf.getString(AppConstant.KEY_EMAIL, null);
        progressBar=view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        Call<MaidStatusResponse> responseCall= RestClient.makeAPI().maidStatus(username,usermail);
        responseCall.enqueue(new Callback<MaidStatusResponse>() {
            @Override
            public void onResponse(Call<MaidStatusResponse> call, Response<MaidStatusResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus()==200) {
//                        Toast.makeText(getContext()," "+response.body().getData().size(),Toast.LENGTH_SHORT).show();
                        ArrayList<CustomerServiceStatusDataSet> mds = new ArrayList<>();
                        for(int i=0;i<response.body().getData().size();i++) {
                            MaidStatusResponse.MaidStatusInnerResponse mis = response.body().getData().get(i);
                            mds.add(new CustomerServiceStatusDataSet(mis.getBook_id(), mis.getName(), mis.getAmount(), mis.getAssign_to(),
                                    mis.getService(),mis.getBooking_date(), mis.getStatus()));
                        }
                        MaidStatusAdapter maidBookingAdapter = new MaidStatusAdapter(mds, getContext(),getActivity(),view);
                        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayout);
                        recyclerView.setAdapter(maidBookingAdapter);
                        progressBar.setVisibility(View.GONE);
                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(),"server busy",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MaidStatusResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),"check your internet connection",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private class MaidStatusAdapter extends RecyclerView.Adapter<MaidStatusAdapter.MaidStatusHolder>{
        private ArrayList<CustomerServiceStatusDataSet> amds;
        private Context context;
        private FragmentActivity activity;
        private View view;
        public MaidStatusAdapter(ArrayList<CustomerServiceStatusDataSet> mds, Context context,FragmentActivity activity,View view) {
            this.amds = mds;
            this.context = context;
            this.activity=activity;
            this.view=view;
        }

        @NonNull
        @Override
        public MaidStatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.maid_status_layout,parent,false);
            return new MaidStatusHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MaidStatusHolder holder, int position) {
            CustomerServiceStatusDataSet mds=amds.get(position);
            holder.tvBookingId.setText(mds.getBookingId());
            holder.tvName.setText(mds.getName());
            holder.tvAmount.setText(mds.getAmount());
            holder.tvMaidName.setText(mds.getMaidName());
            holder.tvService.setText(mds.getService());
            holder.tvBookingDate.setText(mds.getBookingDate());
            holder.tvStatus.setText(mds.getStatus());


            if(mds.getStatus().equalsIgnoreCase("approved"))
            {
                holder.bPay.setVisibility(View.VISIBLE);
                holder.bPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        FragmentTransaction tx=activity.getSupportFragmentManager().beginTransaction();
//                        tx.replace(R.id.frameLayout,);
                        payment(mds.getBookingId(),Long.parseLong(mds.getAmount()),mds.getName());
                    }
                });
            }else{
                holder.bPay.setVisibility(View.GONE);
            }

        }
        private void payment(String id,long amount,String username)
        {
            // initialize Razorpay account.
            Checkout checkout = new Checkout();
            // set your id as below
            checkout.setKeyID("rzp_test_U2XWpODmhRkL0l" +
                    "");
            SharedPreferences sf=activity.getSharedPreferences(AppConstant.SHARED_PREF_NAME,MODE_PRIVATE);
            SharedPreferences.Editor editor=sf.edit();
            editor.putString(AppConstant.KEY_BOOKING_ID,id);
            editor.apply();
            // set image
            // initialize json object
            JSONObject object = new JSONObject();
            try {
                // to put name
                object.put("name", username);
                // put description
                object.put("description", "Test payment");
                // to set theme color
                object.put("theme.color", "6374432752");
                // put the currency
                object.put("currency", "INR");
                // put amount
                object.put("amount", (amount*100));
                // put mobile number
                object.put("prefill.contact", "6374432752");
                // put package name
                object.put("package name","customer payment");
                // put email
                // open razorpay to checkout activity
                checkout.open(activity, object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return amds.size();
        }
        private class MaidStatusHolder extends RecyclerView.ViewHolder{

            private TextView tvBookingId,tvName,tvAmount,tvMaidName,tvService,tvBookingDate,tvStatus;
            private AppCompatButton bPay;
            private LinearLayout linearLayout;
            public MaidStatusHolder(@NonNull View itemView) {
                super(itemView);
                tvBookingId=itemView.findViewById(R.id.bookingId);
                tvName=itemView.findViewById(R.id.name);
                tvAmount=itemView.findViewById(R.id.amount);
                tvMaidName=itemView.findViewById(R.id.maidName);
                tvService=itemView.findViewById(R.id.service);
                tvBookingDate=itemView.findViewById(R.id.bookingDate);
                tvStatus=itemView.findViewById(R.id.status);
                bPay=itemView.findViewById(R.id.pay);
//                linearLayout=itemView.findViewById(R.id.idForPayButton);
            }
        }
    }
    private class CustomerServiceStatusDataSet{
        private String bookingId;
        private String name;
        private String amount;
        private String maidName;
        private String service;
        private String bookingDate;
        private String status;

        public CustomerServiceStatusDataSet(String bookingId, String name, String amount,
                                            String maidName, String service, String bookingDate, String status) {
            this.bookingId = bookingId;
            this.name = name;
            this.amount = amount;
            this.maidName = maidName;
            this.service = service;
            this.bookingDate = bookingDate;
            this.status = status;
        }

        public String getBookingId() {
            return bookingId;
        }

        public void setBookingId(String bookingId) {
            this.bookingId = bookingId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMaidName() {
            return maidName;
        }

        public void setMaidName(String maidName) {
            this.maidName = maidName;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getBookingDate() {
            return bookingDate;
        }

        public void setBookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}