package com.example.maid.admin;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maid.R;


public class AdminHomeFragment extends Fragment {
    private AppCompatButton bTotalCategory,bListMaids,bNewRequest,bAssignRequest,bRejectedRequest,bTotalRequest;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_home, container, false);
        bTotalCategory=view.findViewById(R.id.totalCategory);
        bListMaids=view.findViewById(R.id.listMaid);
        bNewRequest=view.findViewById(R.id.newRequest);
        bAssignRequest=view.findViewById(R.id.assignRequest);
        bRejectedRequest=view.findViewById(R.id.cancelRequest);
        bTotalRequest=view.findViewById(R.id.totalRequest);
        FragmentTransaction tx=getActivity().getSupportFragmentManager().beginTransaction();
        bTotalCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new ManageCategoryFragment()).addToBackStack("1");
                tx.commit();
            }
        });
        bListMaids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new ManageMaidFragment()).addToBackStack("1");
                tx.commit();
            }
        });
        bNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new NewRequestFragment()).addToBackStack("1");
                tx.commit();
            }
        });
        bAssignRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new AssignedRequestFragment()).addToBackStack("1");
                tx.commit();
            }
        });
        bRejectedRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new CancelledRequestFragment()).addToBackStack("1");
                tx.commit();
            }
        });
        bTotalRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new TotalRequestFragment()).addToBackStack("1");
                tx.commit();
            }
        });


        return view;
    }
}