package com.example.agrilife;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrilife.model_classes.insuranceModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class Insurance_details extends AppCompatActivity {

    TextView policyName , policyDescription ,policyCover , targetEntity , premiumLowerBound ,claimSettlementRatio ,amountSettlementRatio, planId , waitingPeriodInDays, noClaimBonus;
    Button apply ;
    TextView applied_already;
    private insuranceModel model;
    private  FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_details);
        model= (insuranceModel) getIntent().getSerializableExtra("model");

        firebaseFirestore=FirebaseFirestore.getInstance();
        DocumentReference docIdRef = firebaseFirestore.collection("farmer_policies").document(model.getPlanId());

        applied_already=findViewById(R.id.alreadyApplied);
        policyName = findViewById(R.id.policyName);
        policyDescription = findViewById(R.id.policyDescription);
        policyCover = findViewById(R.id.policyCover);
        targetEntity = findViewById(R.id.targetEntity);
        premiumLowerBound = findViewById(R.id.premiumLowerBound);
        claimSettlementRatio = findViewById(R.id.claimSettlementRatio);
        amountSettlementRatio = findViewById(R.id.amountSettlementRatio);
        planId = findViewById(R.id.planid);
        waitingPeriodInDays = findViewById(R.id.waitingPeriodInDays);
        noClaimBonus = findViewById(R.id.noClaimBonus);
        apply = findViewById(R.id.apply);
        Calendar c;
        c=Calendar.getInstance();
        String date=""+c.get(Calendar.DATE)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
        model.setApplied_date(date);
        policyName.setText("Policy Name :"+model.getPolicyName());
        policyDescription.setText("\n\nPolicy Description :"+model.getPolicyDescription());
        policyCover.setText( "\n\nPolicy Cover :"+Integer.toString(model.getPolicyCover()));
        targetEntity.setText("\n\nTarget Entity  :"+model.getTargetEntity());
        premiumLowerBound.setText("\n\nPremium Lower Bound :"+Integer.toString(model.getPremiumLowerBound()));
        claimSettlementRatio.setText("\n\nClaim Settlement Ratio :"+Double.toString(model.getClaimSettlementRatio()));
        amountSettlementRatio.setText("\n\nAmount Settlement Ratio :"+Double.toString(model.getAmountSettlementRatio()));
        planId.setText("\n\nPolicy ID :"+model.getPlanId());
        waitingPeriodInDays.setText("\n\nWaiting Period In Days :"+Integer.toString(model.getWaitingPeriodInDays()));
        noClaimBonus.setText("\n\nNo Claim Bonus :"+Integer.toString(model.getNoClaimBonus()));


        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        applied_already.setVisibility(View.VISIBLE);
                        apply.setVisibility(View.INVISIBLE);
                    } else {
                        ;
                        //Toast.makeText(getApplicationContext(), "NOT EXISTS", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d(TAG, "Failed with: ", task.getException());
                }
            }
        });






        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("farmer_policies").document(model.getPlanId()).set(model);
                Toast.makeText(getApplicationContext(), " Applied !!! ", Toast.LENGTH_SHORT).show();

                applied_already.setVisibility(View.VISIBLE);
                apply.setVisibility(View.INVISIBLE);
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent i =new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();

    }

}