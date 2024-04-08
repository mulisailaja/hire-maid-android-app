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
import com.example.maid.maid.AddMaidFragment;


public class MaidFragment extends Fragment {


    AppCompatButton manage,add;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_maid, container, false);
        manage=view.findViewById(R.id.manage);
        add=view.findViewById(R.id.add);
        FragmentTransaction tx=getActivity().getSupportFragmentManager().beginTransaction();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new AddMaidFragment()).addToBackStack("1");
                tx.commit();
            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new ManageMaidFragment()).addToBackStack("1");
                tx.commit();
            }
        });

        return  view;
    }
}