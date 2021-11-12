package com.example.agrilife.adapter_classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agrilife.DetailsFragment;
import com.example.agrilife.R;
import com.example.agrilife.model_classes.insuranceModel;

import java.util.ArrayList;

public class insurence_feed_adapter extends RecyclerView.Adapter<insurence_feed_adapter.viewHolder> {
    Context context ;
    ArrayList<insuranceModel> list;
    FragmentManager manager;
    
    public insurence_feed_adapter(Context context, ArrayList<insuranceModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public insurence_feed_adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.insurance_feed_cardview,parent ,false);
        return new viewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull insurence_feed_adapter.viewHolder holder, int position) {
        insuranceModel policy = list.get(position);
        holder.policyName.setText(policy.getPolicyName());
        holder.policyType.setText(policy.getPolicyDescription() + "\nMax claim : " + policy.getPolicyCover());
        holder.premiumStartFrom.setText("Premium Starts from: " + policy.getPremiumLowerBound());
        holder.ASR.setText("Amount settlement : " + Double.toString(policy.getAmountSettlementRatio()));
        holder.CSR.setText("Claim Settlement :" + Double.toString(policy.getClaimSettlementRatio()));
        holder.policyName.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AppCompatActivity activity =(AppCompatActivity)v.getContext();

            Bundle b = new Bundle();
            b.putString("planID",policy.getPlanId().toString());
            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(b);


        }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView policyName,  policyType , premiumStartFrom , CSR , ASR ;
        // CSR => claim settlement ratio
        // ASR => Amount settlement ratio

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            policyName = itemView.findViewById(R.id.policyName);
            policyType  = itemView.findViewById(R.id.policyType);
            CSR = itemView.findViewById(R.id.claim_settlement_ratio);
            ASR = itemView.findViewById(R.id.amount_settlement_ratio);
            premiumStartFrom = itemView.findViewById(R.id.policyPremiumStartsFrom);
        }
    }
    private  void Fragment_Transaction() {


    }

}
