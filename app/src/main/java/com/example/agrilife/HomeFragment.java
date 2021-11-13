package com.example.agrilife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrilife.model_classes.insuranceModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * Home fragment i.e. home screen of app it will show list of insurence plans available also one
 * can select and avail for this plan
 * **/
public class HomeFragment extends Fragment {
    View parentHolder;
    Context mContext;
    RecyclerView insurance_feed_recycler_view;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
//    ArrayList<insuranceModel> feed_list = new ArrayList<>();
//    insurence_feed_adapter adapter;

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }

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
        insurance_feed_recycler_view=parentHolder.findViewById(R.id.insurance_feed);
        firebaseFirestore=FirebaseFirestore.getInstance();

        //Query
        Query query=firebaseFirestore.collection("policies");
        //RecyclerOption
        FirestoreRecyclerOptions<insuranceModel> options=new FirestoreRecyclerOptions.Builder<insuranceModel>().
                setQuery(query,insuranceModel.class).build();


        adapter=new FirestoreRecyclerAdapter<insuranceModel,insuranceViewHolder>(options){

            @NonNull
            @Override
            public insuranceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.insurance_feed_cardview,parent,false);

                return new insuranceViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull insuranceViewHolder holder, int position, @NonNull insuranceModel model) {
                holder.policyName.setText("Policy Name :" +model.getPolicyName());
                holder.policyDesc.setText("Policy Description "+model.getPolicyDescription());
                holder.policyid.setText("Plan ID :"+model.getPlanId());
                holder.claim_settlement_ratio.setText("ClaimSettlement Ratio"+model.getClaimSettlementRatio()+"");
                holder.amount_settlement_ratio.setText("Amount Settlement :"+model.getAmountSettlementRatio()+"");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(mContext, Insurance_details.class);
                        i.putExtra("model",  model);
                        startActivity(i);
                    }
                });
            }
        };

        insurance_feed_recycler_view.setHasFixedSize(true);
        insurance_feed_recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        insurance_feed_recycler_view.setAdapter(adapter);


        return parentHolder;
    }

    //View Holder
    private class insuranceViewHolder extends RecyclerView.ViewHolder {
        private TextView policyName,policyDesc,policyid,claim_settlement_ratio,amount_settlement_ratio;
        public insuranceViewHolder(@NonNull View itemView) {
            super(itemView);

            policyName=itemView.findViewById(R.id.policyName);
            policyDesc=itemView.findViewById(R.id.policyDesc);
            policyid=itemView.findViewById(R.id.planId);
            claim_settlement_ratio=itemView.findViewById(R.id.claim_settlement_ratio);
            amount_settlement_ratio=itemView.findViewById(R.id.amount_settlement_ratio);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}