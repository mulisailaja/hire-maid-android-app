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

public class CategoryFragment extends Fragment {

    private AppCompatButton add,manage;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_category, container, false);
        add=view.findViewById(R.id.add);
        manage=view.findViewById(R.id.manage);
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.frameLayout,new AddCategoryFragment()).addToBackStack("1");
                fragmentTransaction.commit();
            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.frameLayout,new ManageCategoryFragment()).addToBackStack("1");
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}