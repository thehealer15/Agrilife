package com.example.agrilife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrilife.model_classes.FinancingPlansModel;
import com.example.agrilife.model_classes.insuranceModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

public class FinancingFragment extends Fragment {
    View parentHolder;
    Context mContext;

    RecyclerView loan_feed_recycler_view;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }

    public FinancingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder =  inflater.inflate(R.layout.fragment_financing, container, false);
        loan_feed_recycler_view = parentHolder.findViewById(R.id.loan_feed);
        firebaseFirestore=FirebaseFirestore.getInstance();

        //Query
        Query query=firebaseFirestore.collection("loans");

        //RecyclerOption
        FirestoreRecyclerOptions<FinancingPlansModel> options=new FirestoreRecyclerOptions.Builder<FinancingPlansModel>().
                setQuery(query,FinancingPlansModel.class).build();


        adapter=new FirestoreRecyclerAdapter<FinancingPlansModel, FinancingFragment.loanViewHolder >(options){

            @NonNull
            @Override
            public FinancingFragment.loanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.loan_cardview,parent,false);

                return new FinancingFragment.loanViewHolder (view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FinancingFragment.loanViewHolder  holder, int position, @NonNull FinancingPlansModel model) {
                holder.planName.setText("Plan Name :" +model.getPlanName());
                holder.loanAmount.setText("Loan Amount : "+model.getAmount());
                holder.interestRate.setText("Interest Rate :"+model.getInterestRate());
                holder.tenureToReturn.setText("Repayment shall be done in "+model.getReturnPeriod_inMonths()+ " months");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(mContext, LoanDetails.class);
                        try {
                            i.putExtra("model",  model);
                            startActivity(i);
//                            finish();
                        }catch (Exception e){
                            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        };

        loan_feed_recycler_view.setHasFixedSize(true);
        loan_feed_recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        loan_feed_recycler_view.setAdapter(adapter);

        return  parentHolder;
    }

    //View Holder
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
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
    }


}