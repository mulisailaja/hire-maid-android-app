package com.example.maid.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.admin.AddCategoryResponse;
import com.example.maid.api.response.admin.ManageMaid;
import com.example.maid.api.response.customer.CategoryResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManageMaidFragment extends Fragment {

    ArrayList<CustomerServiceStatusDataSet> css;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_manage_maid, container, false);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        RecyclerView recyclerView=view.findViewById(R.id.manageMaidRecyclerView);
        progressBar=view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        Call<ManageMaid> responseCall= RestClient.makeAPI().manageMaid();
        responseCall.enqueue(new Callback<ManageMaid>() {
            @Override
            public void onResponse(Call<ManageMaid> call, Response<ManageMaid> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus()==200)
                    {
                        css = new ArrayList<>();
                        progressBar.setVisibility(View.GONE);
                        for(int i=0;i<response.body().getData().size();i++) {
                            ManageMaid.ManageMaidInnerClass mis = response.body().getData().get(i);
                            css.add(new CustomerServiceStatusDataSet(mis.getProficient(),mis.getName(),mis.getEmail(),
                                    mis.getContactNumber(),mis.getExperience(),mis.getLocation(),mis.getWilling_to_Work()
                            ,mis.getDate_of_Registration(),mis.getPreferredLocations()));
                        }
                        MaidStatusAdapter maidBookingAdapter = new MaidStatusAdapter(css, getContext(),view);
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
            public void onFailure(Call<ManageMaid> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),"check your internet connection",Toast.LENGTH_SHORT).show();
            }
        });
        return  view;
    }
    private class MaidStatusAdapter extends RecyclerView.Adapter<MaidStatusAdapter.MaidStatusHolder>{
        private ArrayList<CustomerServiceStatusDataSet> amds;
        private Context context;
        private View view;
        public MaidStatusAdapter(ArrayList<CustomerServiceStatusDataSet> mds, Context context,View view) {
            this.amds = mds;
            this.context = context;
            this.view=view;
        }
        @NonNull
        @Override
        public MaidStatusAdapter.MaidStatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_maid_layout,parent,false);
            return new MaidStatusAdapter.MaidStatusHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MaidStatusAdapter.MaidStatusHolder holder, int position) {
            CustomerServiceStatusDataSet mds=amds.get(position);
            holder.Proficient.setText(mds.getProficient());
            holder.tvName.setText(mds.getName());
            holder.tvEmail.setText(mds.getEmail());
            holder.tvPhone.setText(mds.getContactNumber());
            holder.tvExperience.setText(mds.getExperience());
            holder.tvLocation.setText(mds.getLocation());
            holder.tvWilling_to_Work.setText(mds.getWilling_to_Work());
            holder.tvDoR.setText(mds.getDor());
            holder.tvpreferredLocations.setText(mds.getPreferredLocations());
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getContext(),mds.getEmail(),Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setMessage("Do you want to delete ?");

                    builder.setTitle("Alert !");

                    builder.setCancelable(false);

                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        deleteMaid(mds.getEmail());
                        dialog.cancel();
                    });

                    builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
        }
        private void deleteMaid(String id)
        {
           ProgressBar progressBar= view.findViewById(R.id.progress);
           progressBar.setVisibility(View.VISIBLE);

            Call<AddCategoryResponse> requestCall= RestClient.makeAPI().deleteMaid(id);
            requestCall.enqueue(new Callback<AddCategoryResponse>() {
                @Override
                public void onResponse(Call<AddCategoryResponse> call, Response<AddCategoryResponse> response) {
                    if(response.isSuccessful())
                    {
                        if(response.body().getStatus()==200){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
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
                public void onFailure(Call<AddCategoryResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(),"check your internet connection",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return amds.size();
        }
        private class MaidStatusHolder extends RecyclerView.ViewHolder{
            private TextView Proficient,tvName,tvEmail,tvPhone,tvExperience,tvLocation,tvWilling_to_Work,tvDoR
                    ,tvpreferredLocations;
            private AppCompatButton edit,delete;
            private LinearLayout linearLayout;
            public MaidStatusHolder(@NonNull View itemView) {
                super(itemView);
                Proficient=itemView.findViewById(R.id.maidService);
                tvName=itemView.findViewById(R.id.name);
                tvPhone=itemView.findViewById(R.id.contact);
                tvEmail=itemView.findViewById(R.id.email);
                tvExperience=itemView.findViewById(R.id.experience);
                tvLocation=itemView.findViewById(R.id.location);
                tvWilling_to_Work=itemView.findViewById(R.id.willingToWork);
                tvDoR=itemView.findViewById(R.id.date_of_Registration);
                delete=itemView.findViewById(R.id.delete);
                tvpreferredLocations=itemView.findViewById(R.id.preferredLocations);
            }
        }
    }
    private class CustomerServiceStatusDataSet{
        private String preferredLocations;

        public String getPreferredLocations() {
            return preferredLocations;
        }

        public void setPreferredLocations(String preferredLocations) {
            this.preferredLocations = preferredLocations;
        }

        public CustomerServiceStatusDataSet(String proficient, String name, String email, String contactNumber,
                                            String experience, String location, String willing_to_Work, String dor,
                                            String preferredLocations) {
            Proficient = proficient;
            Name = name;
            Email = email;
            ContactNumber = contactNumber;
            Experience = experience;
            Location = location;
            Willing_to_Work = willing_to_Work;
            this.dor=dor;
            this.preferredLocations=preferredLocations;
        }
        private String dor;
        private String Proficient;
        private String Name;
        private String Email;
        private String ContactNumber;
        private String Experience;
        private String Location;
        private String Willing_to_Work;

        public String getProficient() {
            return Proficient;
        }

        public void setProficient(String proficient) {
            Proficient = proficient;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getContactNumber() {
            return ContactNumber;
        }

        public void setContactNumber(String contactNumber) {
            ContactNumber = contactNumber;
        }

        public String getExperience() {
            return Experience;
        }

        public void setExperience(String experience) {
            Experience = experience;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String location) {
            Location = location;
        }

        public String getWilling_to_Work() {
            return Willing_to_Work;
        }

        public String getDor() {
            return dor;
        }

        public void setDor(String dor) {
            this.dor = dor;
        }

        public void setWilling_to_Work(String willing_to_Work) {
            Willing_to_Work = willing_to_Work;
        }


    }
}