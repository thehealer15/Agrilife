package com.example.agrilife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrilife.model_classes.User;
import com.example.agrilife.model_classes.insuranceModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Map;

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
    Map<String,Object> userInfo;
    RecyclerView opted_polices;
    private TextView profile_name,profile_email,profile_phone,profile_address;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;


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
        profile_name=parentHolder.findViewById(R.id.profile_name);
        profile_address=parentHolder.findViewById(R.id.profile_address);
        profile_phone=parentHolder.findViewById(R.id.profile_phone);
        profile_email=parentHolder.findViewById(R.id.profile_email);
        String uidi=FirebaseAuth.getInstance().getUid().toString();
        DocumentReference db = FirebaseFirestore.getInstance().collection("USERS").document(uidi);

        db.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                          @Override
                                          public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            userInfo=documentSnapshot.getData();
                                            profile_name.setText("Name :"+userInfo.get("name").toString());
                                            profile_address.setText("Address :"+userInfo.get("address").toString());
                                            profile_email.setText("Email :"+userInfo.get("email").toString());
                                            profile_phone.setText("Phone :"+userInfo.get("phone").toString());
//                                              Toast.makeText(mContext, "TO "+userInfo.get("name"), Toast.LENGTH_SHORT).show();
                                          }
                                      }
        );

        opted_polices=parentHolder.findViewById(R.id.opted_policies);
        firebaseFirestore=FirebaseFirestore.getInstance();

        //Query
        Query query=firebaseFirestore.collection("farmer_policies").document(FirebaseAuth.getInstance().getUid().toString()).collection("policies_opted");
        //RecyclerOption
        FirestoreRecyclerOptions<insuranceModel> options=new FirestoreRecyclerOptions.Builder<insuranceModel>().
                setQuery(query,insuranceModel.class).build();


        adapter=new FirestoreRecyclerAdapter<insuranceModel, opted_insuranceViewHolder>(options){

            @NonNull
            @Override
            public opted_insuranceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.opted_insurance_cardview,parent,false);

                return new opted_insuranceViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull opted_insuranceViewHolder holder, int position, @NonNull insuranceModel model) {
                holder.policyName.setText("\n\nPolicy Name :" +model.getPolicyName());
                holder.policyDesc.setText("\n\nPolicy Description "+model.getPolicyDescription());
                holder.premium.setText("\n\nPremimum  :"+model.getPremiumLowerBound());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "CLICK IT!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        };

        opted_polices.setHasFixedSize(true);
        opted_polices.setLayoutManager(new LinearLayoutManager(mContext));
        opted_polices.setAdapter(adapter);


        return  parentHolder;
    }
    private  class opted_insuranceViewHolder extends RecyclerView.ViewHolder {
        private TextView policyName,policyDesc,premium;
        public opted_insuranceViewHolder(@NonNull View itemView) {
            super(itemView);

            policyName=itemView.findViewById(R.id.opted_policy_name);
            policyDesc=itemView.findViewById(R.id.opted_policy_desc);
            premium=itemView.findViewById(R.id.opted_policy_premium);
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