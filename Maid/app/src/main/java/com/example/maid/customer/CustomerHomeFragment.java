package com.example.maid.customer;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.maid.R;
import com.example.maid.login.LoginFragment;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;


public class CustomerHomeFragment extends Fragment {
    private ArrayList<MaidDataSet> mds;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_customer_home, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.maidHomeRecyclerView);
        mds=new ArrayList<>();
        progressBar=view.findViewById(R.id.progress);
        mds.add(new MaidDataSet(R.drawable.cooking,"cooking"));
        mds.add(new MaidDataSet(R.drawable.bathroomcleaning,"Bathroom Cleaning"));
        mds.add(new MaidDataSet(R.drawable.laundry,"Laundry"));
        mds.add(new MaidDataSet(R.drawable.mopping,"Mopping"));
        mds.add(new MaidDataSet(R.drawable.sweeping,"Sweeping"));
        mds.add(new MaidDataSet(R.drawable.utensilscleaning,"utensils Cleaning"));
        mds.add(new MaidDataSet(R.drawable.deepcleaning,"Deep Cleaning"));
        mds.add(new MaidDataSet(R.drawable.babytakecarer,"Baby caring"));

        MaidBookingAdapter maidBookingAdapter=new MaidBookingAdapter(mds,getContext(),getActivity());
        LinearLayoutManager linearLayout=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(maidBookingAdapter);

        return view;
    }
    private class MaidBookingAdapter extends RecyclerView.Adapter<MaidBookingAdapter.MaidBookingHolder>{
        private ArrayList<MaidDataSet> amds;
        private Context context;
        private  FragmentActivity activity;

        public MaidBookingAdapter(ArrayList<MaidDataSet> mds, Context context, FragmentActivity activity) {
            this.amds = mds;
            this.context = context;
            this.activity=activity;
        }

        @NonNull
        @Override
        public MaidBookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_maid_layout,parent,false);
            return new MaidBookingHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MaidBookingHolder holder, int position) {
            MaidDataSet mds=amds.get(position);
            holder.textView.setText(mds.getWork());
            holder.imageView.setImageResource(mds.getImageId());
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction transaction=activity.getSupportFragmentManager().beginTransaction();
                    FindMaidFragment findMaidFragment= new FindMaidFragment();
                    transaction.replace(R.id.frameLayout, findMaidFragment).addToBackStack("1");
                    transaction.commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return amds.size();
        }
        private class MaidBookingHolder extends RecyclerView.ViewHolder{

            private TextView textView;
            private ShapeableImageView imageView;
            private AppCompatButton button;
            public MaidBookingHolder(@NonNull View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.work);
                imageView=itemView.findViewById(R.id.image);
                button=itemView.findViewById(R.id.book);
            }
        }
    }
    private class MaidDataSet{
        private int imageId;
        private String work;

        public MaidDataSet(int imageId, String work) {
            this.imageId = imageId;
            this.work = work;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }
    }
}