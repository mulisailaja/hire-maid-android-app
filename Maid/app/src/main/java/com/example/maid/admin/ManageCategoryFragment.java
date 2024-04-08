package com.example.maid.admin;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maid.AppConstant;
import com.example.maid.R;
import com.example.maid.RestClient;
import com.example.maid.api.response.admin.AddCategoryResponse;
import com.example.maid.api.response.customer.CategoryResponse;
import com.example.maid.api.response.customer.MaidStatusResponse;
import com.example.maid.api.response.maid.AssignRequestResponse;
import com.example.maid.customer.MaidStatusFragment;
import com.example.maid.maid.AssignRequestFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageCategoryFragment extends Fragment {

    private ArrayList<CustomerServiceStatusDataSet> css;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_manage_category, container, false);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        RecyclerView recyclerView=view.findViewById(R.id.manageCategoryRecyclerView);
        progressBar=view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        Call<CategoryResponse> responseCall= RestClient.makeAPI().getCategory();
        responseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus()==200)
                    {
                        progressBar.setVisibility(View.GONE);
                        css = new ArrayList<>();

                        for(int i=0;i<response.body().getData().size();i++) {
                            CategoryResponse.CategoryInnerResponse mis = response.body().getData().get(i);
                            css.add(new CustomerServiceStatusDataSet(mis.getId(), mis.getCategory_name(), mis.getPer_hour_amount(),
                                    mis.getMonthly_amount()));
                        }
                        MaidStatusAdapter maidBookingAdapter = new MaidStatusAdapter(css, getContext(),getActivity(),view);
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
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
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
        private Dialog dialog;
        private View view;
        public MaidStatusAdapter(ArrayList<CustomerServiceStatusDataSet> mds, Context context, FragmentActivity activity,View view) {
            this.amds = mds;
            this.context = context;
            this.activity=activity;
            this.view=view;
        }

        @NonNull
        @Override
        public MaidStatusAdapter.MaidStatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_category_layout,parent,false);
            return new MaidStatusAdapter.MaidStatusHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MaidStatusAdapter.MaidStatusHolder holder, int position) {
            CustomerServiceStatusDataSet mds=amds.get(position);
            holder.tvBookingId.setText(mds.getCat_name());
            holder.tvName.setText(mds.getHourAmount());
            holder.tvAmount.setText(mds.getMonthlyAmount());
            FragmentTransaction tx=activity.getSupportFragmentManager().beginTransaction();
            Bundle bundle=new Bundle();
            bundle.putString("catId",mds.getId());
            bundle.putString("catName",mds.getCat_name());
            bundle.putString("catHourlyAmount",mds.getHourAmount());
            bundle.putString("catMonthlyAmount",mds.getMonthlyAmount());
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditCategoryFragment c=new EditCategoryFragment();
                    c.setArguments(bundle);
                    tx.replace(R.id.frameLayout,c).addToBackStack("1");
                    tx.commit();
                }
            });

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setMessage("Do you want to delete ?");

                    builder.setTitle("Alert !");

                    builder.setCancelable(false);

                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        deleteApi(mds.getId());
                        dialog.cancel();
                    });

                    builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });

                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();
                    // Show the Alert Dialog box
                    alertDialog.show();
                }
            });



        }
        private void deleteApi(String delete)
        {
            ProgressBar progressBar1=view.findViewById(R.id.progress);
            progressBar1.setVisibility(View.VISIBLE);
            Call<AddCategoryResponse> requestCall= RestClient.makeAPI().deleteCategory(delete);
            requestCall.enqueue(new Callback<AddCategoryResponse>() {
                @Override
                public void onResponse(Call<AddCategoryResponse> call, Response<AddCategoryResponse> response) {
                    if(response.isSuccessful())
                    {
                        if(response.body().getStatus()==200){

                            progressBar1.setVisibility(View.GONE);
                            Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressBar1.setVisibility(View.GONE);
                            Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        progressBar1.setVisibility(View.GONE);
                        Toast.makeText(getContext(),"server busy",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AddCategoryResponse> call, Throwable t) {
                    progressBar1.setVisibility(View.GONE);
                    Toast.makeText(getContext(),"check your internet connection",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return amds.size();
        }
        private class MaidStatusHolder extends RecyclerView.ViewHolder{
            private TextView tvBookingId,tvName,tvAmount,tvMaidName,tvService,tvBookingDate,tvStatus;
            private AppCompatButton edit,delete,dialogDelete,dialogCancel;
            private LinearLayout linearLayout;
            private Dialog dialog;
            public MaidStatusHolder(@NonNull View itemView) {
                super(itemView);
                tvBookingId=itemView.findViewById(R.id.category);
                tvName=itemView.findViewById(R.id.hour);
                tvAmount=itemView.findViewById(R.id.month);
                edit=itemView.findViewById(R.id.editCategory);
                delete=itemView.findViewById(R.id.deleteCategory);
                dialogDelete=itemView.findViewById(R.id.deleteDialog);
                dialogCancel=itemView.findViewById(R.id.cancel);

            }
        }
    }
    private class CustomerServiceStatusDataSet{
       private String id;
       private String cat_name;
       private String hourAmount;
       private String monthlyAmount;

        public CustomerServiceStatusDataSet(String id, String cat_name, String hourAmount, String monthlyAmount) {
            this.id = id;
            this.cat_name = cat_name;
            this.hourAmount = hourAmount;
            this.monthlyAmount = monthlyAmount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getHourAmount() {
            return hourAmount;
        }

        public void setHourAmount(String hourAmount) {
            this.hourAmount = hourAmount;
        }

        public String getMonthlyAmount() {
            return monthlyAmount;
        }

        public void setMonthlyAmount(String monthlyAmount) {
            this.monthlyAmount = monthlyAmount;
        }
    }
}