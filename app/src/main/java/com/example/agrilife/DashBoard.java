package com.example.agrilife;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * It will be dashboard to see which insurance farmer made also facility to request a call from expert
 * So in dashboard :
 *  - plans he opted for
 *  - pay premium
 *  - claim request on small window
 * */
public class DashBoard extends Fragment {
    View parentHolder;
    Context mContext;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public DashBoard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder =  inflater.inflate(R.layout.fragment_dash_board, container, false);

        return  parentHolder;
    }
}