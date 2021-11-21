package com.example.agrilife;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrilife.model_classes.FinancingPlansModel;
import com.example.agrilife.model_classes.User;
import com.example.agrilife.model_classes.insuranceModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Map;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
/**
 * It will be dashboard to see which insurance farmer made also facility to request a call from expert
 * So in dashboard :
 *  - plans he opted for
 *  - pay premium
 *  - claim request on small window
 * */
public class DashBoard extends Fragment   {
    View parentHolder;
    Context mContext;
    Map<String,Object> userInfo;
    RecyclerView opted_polices;
    private TextView profile_name,profile_email,profile_phone,profile_address,loan_opted,policy_opted;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    private FirebaseFirestore Loan_firebaseFirestore;
    private FirestoreRecyclerAdapter Loan_adapter;


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
        loan_opted=parentHolder.findViewById(R.id.loan_opted);
        policy_opted=parentHolder.findViewById(R.id.policy_opted);
        String uidi=FirebaseAuth.getInstance().getUid().toString();
        DocumentReference db = FirebaseFirestore.getInstance().collection("USERS").document(uidi);
        Checkout.preload(getContext());




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
                        // add bottom sheet activity first
                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
                        bottomSheetDialog.setContentView(R.layout.plan_details_bottom_sheet);
                        bottomSheetDialog.setCanceledOnTouchOutside(true);

                        // varibales inside bottom sheet
                        TextView policyName_bottomSheet_tv = bottomSheetDialog.findViewById(R.id.planName_bottom_sheet);
                        Button payPremium_bottomSheet_btn = bottomSheetDialog.findViewById(R.id.pay_premium_bottom_sheet),
                                claimInsurance_bottomSheet_btn = bottomSheetDialog.findViewById(R.id.claim_insurance_bottom_sheet),
                                seeDetails_bottomSheet_btn = bottomSheetDialog.findViewById(R.id.see_plan_details_bottom_sheet);
                        policyName_bottomSheet_tv.setText(model.getPolicyName());
                        payPremium_bottomSheet_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getActivity(),PaymentActivity.class));
                            }
                        });

                        // code to implementation of claiming insurance:
                        claimInsurance_bottomSheet_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                                builder1.setTitle("Claim requested!");
                                builder1.setMessage("Now let your company do work ! please sit back and wait for our agent to contact you !!");
                                builder1.setCancelable(true);

                                builder1.setNegativeButton(
                                        "okay",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                            }
                        });
                        bottomSheetDialog.show();

                        seeDetails_bottomSheet_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(mContext, Insurance_details.class);
                                i.putExtra("model",  model);
                                startActivity(i);
                            }
                        });

                    }
                });
            }
        };

        opted_polices.setHasFixedSize(true);
        opted_polices.setLayoutManager(new LinearLayoutManager(mContext));
        opted_polices.setAdapter(adapter);
        policy_opted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loan_opted.setTypeface(null,Typeface.NORMAL);
                policy_opted.setTypeface(null, Typeface.BOLD);
                opted_polices.setAdapter(adapter)
                ;
            }
        });



        //Loan implements
        Loan_firebaseFirestore=FirebaseFirestore.getInstance();

        //Query
        Query Loan_query=firebaseFirestore.collection("farmer_policies").document(FirebaseAuth.getInstance().getUid().toString()).collection("loan_opted");

        //RecyclerOption
        FirestoreRecyclerOptions<FinancingPlansModel> loan_options=new FirestoreRecyclerOptions.Builder<FinancingPlansModel>().
                setQuery(Loan_query,FinancingPlansModel.class).build();


        Loan_adapter=new FirestoreRecyclerAdapter<FinancingPlansModel, DashBoard.loanViewHolder >(loan_options){

            @NonNull
            @Override
            public DashBoard.loanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.loan_cardview,parent,false);

                return new DashBoard.loanViewHolder (view);
            }

            @Override
            protected void onBindViewHolder(@NonNull DashBoard.loanViewHolder  holder, int position, @NonNull FinancingPlansModel model) {
                holder.planName.setText("Plan Name :" +model.getPlanName());
                holder.loanAmount.setText("Loan Amount : "+model.getAmount());
                holder.interestRate.setText("Interest Rate :"+model.getInterestRate());
                holder.tenureToReturn.setText("Repayment shall be done in "+model.getReturnPeriod_inMonths()+ " months");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
                        bottomSheetDialog.setContentView(R.layout.loan_click_bottom_sheet);
                        bottomSheetDialog.setCanceledOnTouchOutside(true);
                        Button loan_plan_details = bottomSheetDialog.findViewById(R.id.loan_plan_details_btm_sheet),
                                loan_pay_sip = bottomSheetDialog.findViewById(R.id.pay_installment); 
                        
                        loan_plan_details.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "Under Construction", Toast.LENGTH_SHORT).show();
                            }
                        });
                        
                        loan_pay_sip.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    startActivity(new Intent(getActivity(),LoanRepaymentActivity.class));
                                }catch (Exception e){
                                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        bottomSheetDialog.show();

                    }
                });
            }
        };

        opted_polices.setHasFixedSize(true);
        opted_polices.setLayoutManager(new LinearLayoutManager(mContext));

        loan_opted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opted_polices.setAdapter(Loan_adapter);
                loan_opted.setTypeface(null, Typeface.BOLD);
                policy_opted.setTypeface(null,Typeface.NORMAL);
            }
        });



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

    private class loanViewHolder extends RecyclerView.ViewHolder {
        private TextView planName ,loanAmount , interestRate , tenureToReturn;
        public loanViewHolder(@NonNull View itemView) {
            super(itemView);
            planName = itemView.findViewById(R.id.loan_name);
            loanAmount = itemView.findViewById(R.id.loan_amount);
            interestRate = itemView.findViewById(R.id.interst_rate);
            tenureToReturn = itemView.findViewById(R.id.repayment_tenure);

        }

    }



    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
        Loan_adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        Loan_adapter.stopListening();;
    }




}