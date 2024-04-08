package com.example.maid.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maid.R;
import com.example.maid.maid.AssignRequestFragment;


public class RequestFragment extends Fragment {

    private AppCompatButton newRequest,assignedRequest,canceledRequest,totalRequest;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_request, container, false);
        newRequest=view.findViewById(R.id.newRequest);
        assignedRequest=view.findViewById(R.id.assignRequest);
        canceledRequest=view.findViewById(R.id.cancelRequest);
        totalRequest=view.findViewById(R.id.totalRequest);
        FragmentTransaction tx=getActivity().getSupportFragmentManager().beginTransaction();
        newRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new NewRequestFragment()).addToBackStack("1");
                tx.commit();
            }
        });
        assignedRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new AssignedRequestFragment()).addToBackStack("1");
                tx.commit();
            }
        });
        canceledRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new CancelledRequestFragment()).addToBackStack("1");
                tx.commit();
            }
        });
        totalRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new TotalRequestFragment()).addToBackStack("1");
                tx.commit();
            }
        });

        return view;
    }
}