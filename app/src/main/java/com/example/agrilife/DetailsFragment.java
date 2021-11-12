package com.example.agrilife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agrilife.model_classes.insuranceModel;

import java.util.UUID;


public class DetailsFragment extends Fragment {
    private  View parentHolder;
    private  UUID planId;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public DetailsFragment(UUID planId) {
        this.planId = planId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentHolder =  inflater.inflate(R.layout.fragment_details, container, false);
        // from firestore import planID data


        return  parentHolder;
    }
}