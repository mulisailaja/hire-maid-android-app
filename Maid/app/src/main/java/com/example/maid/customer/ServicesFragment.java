package com.example.maid.customer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maid.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;


public class ServicesFragment extends Fragment {
    private ArrayList<MaidServicesDataSet> mds;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_services, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.maidServicesRecyclerView);
        mds=new ArrayList<>();
        mds.add(new MaidServicesDataSet(R.drawable.cooking,"cooking"));
        mds.add(new MaidServicesDataSet(R.drawable.bathroomcleaning,"Bathroom Cleaning"));
        mds.add(new MaidServicesDataSet(R.drawable.laundry,"Laundry"));
        mds.add(new MaidServicesDataSet(R.drawable.mopping,"Mopping"));
        mds.add(new MaidServicesDataSet(R.drawable.sweeping,"Sweeping"));
        mds.add(new MaidServicesDataSet(R.drawable.utensilscleaning,"utensils Cleaning"));
        mds.add(new MaidServicesDataSet(R.drawable.deepcleaning,"Deep Cleaning"));
        mds.add(new MaidServicesDataSet(R.drawable.babytakecarer,"Baby caring"));
        MaidServicesAdapter maidServicesAdapter=new MaidServicesAdapter(mds,getContext());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(requireActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(maidServicesAdapter);
        return  view;
    }
    private class MaidServicesAdapter extends RecyclerView.Adapter<MaidServicesAdapter.MaidServicesHolder>{
        private ArrayList<MaidServicesDataSet> amds;
        private Context context;
        public MaidServicesAdapter(ArrayList<MaidServicesDataSet> mds, Context context) {
            this.amds = mds;
            this.context = context;
        }

        @NonNull
        @Override
        public MaidServicesAdapter.MaidServicesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.services_layout,parent,false);
            return new MaidServicesAdapter.MaidServicesHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MaidServicesAdapter.MaidServicesHolder holder, int position) {
            MaidServicesDataSet mds=amds.get(position);
            holder.textView.setText(mds.getWork());
            holder.imageView.setImageResource(mds.getImageId());
        }

        @Override
        public int getItemCount() {
            return amds.size();
        }
        private class MaidServicesHolder extends RecyclerView.ViewHolder{

            private TextView textView;
            private ShapeableImageView imageView;
            public MaidServicesHolder(@NonNull View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.serviceWork);
                imageView=itemView.findViewById(R.id.image);
            }
        }
    }
    private class MaidServicesDataSet{
        private int imageId;
        private String work;
        public MaidServicesDataSet(int imageId, String work) {
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