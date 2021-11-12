package com.example.agrilife;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agrilife.adapter_classes.insurence_feed_adapter;
import com.example.agrilife.model_classes.insuranceModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Home fragment i.e. home screen of app it will show list of insurence plans available also one
 * can select and avail for this plan
 * **/
public class HomeFragment extends Fragment {
    View parentHolder;
    Context mContext;
    RecyclerView insurance_feed_recycler_view;
    ArrayList<insuranceModel> feed_list = new ArrayList<>();
    insurence_feed_adapter adapter;

    FirebaseFirestore db;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentHolder =  inflater.inflate(R.layout.fragment_home, container, false);

        insurance_feed_recycler_view = parentHolder.findViewById(R.id.insurance_feed);
        insurance_feed_recycler_view.setHasFixedSize(true);
        insurance_feed_recycler_view.setLayoutManager(new LinearLayoutManager(mContext));

/*
String policyName, String policyDescription, int policyCover, String targetEntity, int premiumLowerBound,
double claimSettlementRatio, double amountSettlementRatio, UUID planId, int waitingPeriodInDays, int noClaimBonus)
//  */
        feed_list.add(new insuranceModel("Crop health gold", "Insurance covering crop health",1000000,"CROP",1000,98.8,99.8
        , UUID.randomUUID(),7,10000));

//
        try {
            adapter = new insurence_feed_adapter(mContext,feed_list);
            insurance_feed_recycler_view.setAdapter(adapter);
        }
        catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return parentHolder;
    }
}