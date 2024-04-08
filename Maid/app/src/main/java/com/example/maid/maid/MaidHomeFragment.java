package com.example.maid.maid;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maid.R;
import com.example.maid.admin.AssignedRequestFragment;


public class MaidHomeFragment extends Fragment {

    private AppCompatButton add,view;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_maid_home, container, false);
        this.view=view.findViewById(R.id.view);
        add=view.findViewById(R.id.add);
        FragmentTransaction tx=getActivity().getSupportFragmentManager().beginTransaction();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new AddMaidFragment()).addToBackStack("1");
                tx.commit();
            }
        });
        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.replace(R.id.frameLayout,new AssignRequestFragment()).addToBackStack("1");
                tx.commit();
            }
        });
        return  view;
    }

}