package com.example.maid.maid;

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
import com.example.maid.api.response.maid.AssignRequestResponse;
import com.example.maid.customer.MaidStatusFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignRequestFragment extends Fragment {

    SharedPreferences sf;
    private ArrayList<CustomerServiceStatusDataSet> mds;
    private ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_assign_request, container, false);
         sf = getActivity().getSharedPreferences(AppConstant.SHARED_PREF_NAME, MODE_PRIVATE);
        String username = sf.getString(AppConstant.KEY_NAME, null);
        progressBar=view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        RecyclerView recyclerView=view.findViewById(R.id.assignRequestRecyclerView);
        Call<AssignRequestResponse> requestCall= RestClient.makeAPI().assignRequest(username);
        requestCall.enqueue(new Callback<AssignRequestResponse>() {
            @Override
            public void onResponse(Call<AssignRequestResponse> call, Response<AssignRequestResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus()==200){
                        mds = new ArrayList<>();
                        progressBar.setVisibility(View.GONE);
                        for(int i=0;i<response.body().getData().size();i++) {
                            AssignRequestResponse.AssignRequestInnerResponse mis = response.body().getData().get(i);
                            mds.add(new CustomerServiceStatusDataSet(mis.getBooking_id(),mis.getName(),mis.getEmail(),
                            mis.getContact_Number(),mis.getAmount(),mis.getService(),mis.getBooking_date(),mis.getStatus()
                            ,mis.getAssignTo()));
                        }
                        MaidStatusAdapter maidBookingAdapter = new MaidStatusAdapter(mds, getContext(),getActivity());
                        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayout);
                        recyclerView.setAdapter(maidBookingAdapter);
                    }
                    else{
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
            public void onFailure(Call<AssignRequestResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),"check your internet connection",Toast.LENGTH_SHORT).show();

            }
        });
        return  view;
    }
    private class MaidStatusAdapter extends RecyclerView.Adapter<MaidStatusAdapter.MaidStatusHolder>{
        private ArrayList<CustomerServiceStatusDataSet> amds;
        private Context context;
        private FragmentActivity activity;
        public MaidStatusAdapter(ArrayList<CustomerServiceStatusDataSet> mds, Context context, FragmentActivity activity) {
            this.amds = mds;
            this.context = context;
            this.activity=activity;
        }

        @NonNull
        @Override
        public MaidStatusAdapter.MaidStatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.assign_request_layout,parent,false);
            return new MaidStatusAdapter.MaidStatusHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MaidStatusAdapter.MaidStatusHolder holder, int position) {
            CustomerServiceStatusDataSet mds=amds.get(position);
            holder.tvBookingId.setText(mds.getBookingId());
            holder.tvName.setText(mds.getName());
            holder.tvAmount.setText(mds.getAmount());
            holder.tvService.setText(mds.getService());
            holder.tvBookingDate.setText(mds.getBookingDate());
            holder.tvStatus.setText(mds.getStatus());
            holder.tvPhone.setText(mds.getPhone());
            holder.tvEmail.setText(mds.getEmail());
            holder.tvAssignTo.setText(mds.getAssignTo());
            FragmentTransaction tx=activity.getSupportFragmentManager().beginTransaction();
            holder.bPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    bundle.putString("bookId",mds.getBookingId());
                    MaidTakeActionFragment ac=new MaidTakeActionFragment();
                    ac.setArguments(bundle);
                    tx.replace(R.id.frameLayout,ac).addToBackStack("1");
                    tx.commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return amds.size();
        }
        private class MaidStatusHolder extends RecyclerView.ViewHolder{

            private TextView tvBookingId,tvName,tvAmount,tvService,tvBookingDate,tvStatus,tvAssignTo,tvEmail,tvPhone;
            private AppCompatButton bPay;
            public MaidStatusHolder(@NonNull View itemView) {
                super(itemView);
                tvBookingId=itemView.findViewById(R.id.bookingId);
                tvName=itemView.findViewById(R.id.name);
                tvAmount=itemView.findViewById(R.id.amount);
                tvService=itemView.findViewById(R.id.service);
                tvBookingDate=itemView.findViewById(R.id.bookingDate);
                tvStatus=itemView.findViewById(R.id.status);
                tvAssignTo=itemView.findViewById(R.id.assignTo);
                tvEmail=itemView.findViewById(R.id.email);
                tvPhone=itemView.findViewById(R.id.contact);
                bPay=itemView.findViewById(R.id.update);
            }
        }
    }
    private class CustomerServiceStatusDataSet{
        public CustomerServiceStatusDataSet(String bookingId, String name, String email, String phone, String amount,
                                            String service, String bookingDate, String status, String assignTo) {
            this.bookingId = bookingId;
            this.name = name;
            this.amount = amount;
            this.service = service;
            this.bookingDate = bookingDate;
            this.status = status;
            this.email=email;
            this.phone=phone;
            this.assignTo=assignTo;
        }
        private String bookingId;
        private String name;
        private String amount;
        private String service;
        private String bookingDate;
        private String status;
        private String email;
        private String phone;
        private String assignTo;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAssignTo() {
            return assignTo;
        }

        public void setAssignTo(String assignTo) {
            this.assignTo = assignTo;
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