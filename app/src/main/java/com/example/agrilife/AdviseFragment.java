package com.example.agrilife;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/***
 * Advise fragment utility:
 * - request a call from expert ( expert in agri recommendation )
 * - Upload information which ML model will need to predict crops
 * - get recommendation derived from that data
 * - recommendation will be from ML model generated and agri-expert monitored
 * */
public class AdviseFragment extends Fragment {
    View parentHolder;
    Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public AdviseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder  = inflater.inflate(R.layout.fragment_advise, container, false);


        return parentHolder;
    }
}