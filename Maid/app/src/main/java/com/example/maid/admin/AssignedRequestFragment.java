package com.example.maid.admin;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.admin.AssignedRequestResponse;
import com.example.maid.api.response.admin.NewRequestResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AssignedRequestFragment extends Fragment {
    private ArrayList<CustomerServiceStatusDataSet> css;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_assigned_request, container, false);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        RecyclerView recyclerView=view.findViewById(R.id.assignRequestRecyclerView);
        progressBar=view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);

        Call<AssignedRequestResponse> responseCall= RestClient.makeAPI().approvedRequest();
        responseCall.enqueue(new Callback<AssignedRequestResponse>() {
            @Override
            public void onResponse(Call<AssignedRequestResponse> call, Response<AssignedRequestResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus()==200)
                    {
                        css = new ArrayList<>();
                        progressBar.setVisibility(View.GONE);
                        for(int i=0;i<response.body().getData().size();i++) {
                            AssignedRequestResponse.AssignedRequestInner mis = response.body().getData().get(i);
                            css.add(new CustomerServiceStatusDataSet(mis.getBooking_id(),mis.getName(),mis.getContact_Number(),
                                    mis.getEmail(),mis.getBooking_date(),mis.getAssignTo(),mis.getStatus()));
                            Log.e(TAG,mis.getBooking_id()+mis.getName()+mis.getContact_Number()+
                                    mis.getEmail()+mis.getBooking_date()+mis.getAssignTo());
                        }
                        MaidStatusAdapter maidBookingAdapter = new MaidStatusAdapter(css, getContext(),getActivity());
                        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayout);
                        recyclerView.setAdapter(maidBookingAdapter);
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
            public void onFailure(Call<AssignedRequestResponse> call, Throwable t) {
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
        public MaidStatusAdapter(ArrayList<CustomerServiceStatusDataSet> mds, Context context,FragmentActivity activity) {
            this.amds = mds;
            this.context = context;
            this.activity=activity;
        }
        @NonNull
        @Override
        public MaidStatusAdapter.MaidStatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.assigned_layout,parent,false);
            return new MaidStatusAdapter.MaidStatusHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MaidStatusAdapter.MaidStatusHolder holder, int position) {
            CustomerServiceStatusDataSet mds=amds.get(position);
            holder.bookId.setText(mds.getBooking_ID());
            holder.tvName.setText(mds.getName());
            holder.tvEmail.setText(mds.getEmail());
            holder.tvPhone.setText(mds.getMobile_Number());
            holder.bookDate.setText(mds.getBooking_Date());
            holder.tvAssignTo.setText(mds.getAssign_To());
            holder.tvStatus.setText(mds.getStatus());

            holder.viewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction tx=activity.getSupportFragmentManager().beginTransaction();
                    TakeActionFragment tk=new TakeActionFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("bookId",mds.getBooking_ID());
                    tk.setArguments(bundle);
                    tx.replace(R.id.frameLayout,tk).addToBackStack(null);
                    tx.commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return amds.size();
        }
        private class MaidStatusHolder extends RecyclerView.ViewHolder{
            private TextView bookId,tvName,tvEmail,tvPhone,bookDate,tvAssignTo,tvStatus;
            private AppCompatButton viewDetails;
            public MaidStatusHolder(@NonNull View itemView) {
                super(itemView);
                bookId=itemView.findViewById(R.id.maidService);
                tvName=itemView.findViewById(R.id.name);
                tvPhone=itemView.findViewById(R.id.contact);
                tvEmail=itemView.findViewById(R.id.email);
                bookDate=itemView.findViewById(R.id.date_of_Registration);
                tvAssignTo=itemView.findViewById(R.id.assignTo);
                tvStatus=itemView.findViewById(R.id.status);
                viewDetails=itemView.findViewById(R.id.viewDetails);
            }
        }
    }
    private class CustomerServiceStatusDataSet{
        private String Booking_ID;
        private String Name;
        private String Mobile_Number;
        private String Email;
        private String Category_Name;
        private String Booking_Date;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getMobile_Number() {
            return Mobile_Number;
        }

        public void setMobile_Number(String mobile_Number) {
            Mobile_Number = mobile_Number;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getCategory_Name() {
            return Category_Name;
        }

        public void setCategory_Name(String category_Name) {
            Category_Name = category_Name;
        }

        public String getBooking_Date() {
            return Booking_Date;
        }

        public void setBooking_Date(String booking_Date) {
            Booking_Date = booking_Date;
        }

        public String getAssign_To() {
            return Assign_To;
        }

        public void setAssign_To(String assign_To) {
            Assign_To = assign_To;
        }

        public String getBooking_ID() {
            return Booking_ID;
        }

        public void setBooking_ID(String booking_ID) {
            Booking_ID = booking_ID;
        }
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public CustomerServiceStatusDataSet(String booking_ID, String name, String mobile_Number, String email,
                                            String booking_Date, String assign_To, String status) {
            Booking_ID = booking_ID;
            Name = name;
            Mobile_Number = mobile_Number;
            Email = email;
            Booking_Date = booking_Date;
            Assign_To = assign_To;
            this.status=status;
        }

        private String Assign_To;

    }
}