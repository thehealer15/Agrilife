package com.example.agrilife;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrilife.model_classes.FinancingPlansModel;
import com.example.agrilife.model_classes.LoanCollateralClass;
import com.example.agrilife.model_classes.insuranceModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class LoanDetails extends AppCompatActivity {
    TextView LoanNameDetails,InterestRateDetails,repaymentTenureDetails,loanAmountDetails;
    Button  Loan_apply;
    TextView Loan_alreadyApplied;
    private FinancingPlansModel model;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_details);
        model= (FinancingPlansModel) getIntent().getSerializableExtra("model");

        firebaseFirestore=FirebaseFirestore.getInstance();
        String uidi= FirebaseAuth.getInstance().getUid().toString();

        DocumentReference docIdRef = firebaseFirestore.collection("farmer_policies").document(uidi).collection("loan_opted").document(model.getPlanName());

        Loan_apply=findViewById(R.id.Loan_apply);
        LoanNameDetails = findViewById(R.id.loanNameDetails);
        InterestRateDetails = findViewById(R.id.interestRateDetails);
        repaymentTenureDetails = findViewById(R.id.repaymentTenureLoanDetails);
        loanAmountDetails = findViewById(R.id.loanAmountDetails);
        Loan_alreadyApplied = findViewById(R.id.Loan_alreadyApplied);
        LoanNameDetails.setText("Loan Name :"+model.getPlanName());
        loanAmountDetails.setText("\n\nLoan Amount  :"+model.getAmount());
        InterestRateDetails.setText( "\n\nInterest Rate :"+Double.toString(model.getInterestRate()));
        repaymentTenureDetails.setText("\n\nRepayment Tenure   :"+model.getReturnPeriod_inMonths());


        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Loan_alreadyApplied.setVisibility(View.VISIBLE);
                        Loan_apply.setVisibility(View.INVISIBLE);
                    } else {
                        ;
//                        Toast.makeText(getApplicationContext(), "NOT EXISTS", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d(TAG, "Failed with: ", task.getException());
                }
            }
        });






        Loan_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                firebaseFirestore.collection("farmer_policies").collection(uidi).document(model.getPlanId()).set(model);
                LoanCollateralClass collateralClass=new LoanCollateralClass();
                FinancingPlansModel upload=new FinancingPlansModel(model.getAmount(),model.getPlanName(),0.0d,collateralClass,model.getInterestRate(),model.getReturnPeriod_inMonths(),0.2d);

                firebaseFirestore.collection("farmer_policies").document(uidi).collection("loan_opted").document(model.getPlanName()).set(upload);


                Toast.makeText(getApplicationContext(), " Opted !!! ", Toast.LENGTH_SHORT).show();

                Loan_alreadyApplied.setVisibility(View.VISIBLE);
                Loan_apply.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i =new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}