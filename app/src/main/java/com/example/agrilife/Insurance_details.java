package com.example.agrilife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agrilife.model_classes.insuranceModel;

public class Insurance_details extends AppCompatActivity {

    EditText policyName , policyDescription ,policyCover , targetEntity , premiumLowerBound ,claimSettlementRatio ,amountSettlementRatio, planId , waitingPeriodInDays, noClaimBonus;
    Button apply ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_details);

        insuranceModel model = (insuranceModel) getIntent().getSerializableExtra("model");


        policyName = findViewById(R.id.policyName);
        policyDescription = findViewById(R.id.policyDescription);
        policyCover = findViewById(R.id.policyCover);
        targetEntity = findViewById(R.id.targetEntity);
        premiumLowerBound = findViewById(R.id.premiumLowerBound);
        claimSettlementRatio = findViewById(R.id.claimSettlementRatio);
        amountSettlementRatio = findViewById(R.id.amountSettlementRatio);
        planId = findViewById(R.id.planId);
        waitingPeriodInDays = findViewById(R.id.waitingPeriodInDays);
        noClaimBonus = findViewById(R.id.noClaimBonus);

        policyName.setText(model.getPolicyName());
        policyDescription.setText(model.getPolicyDescription());
        policyCover.setText( Integer.toString(model.getPolicyCover()));
        targetEntity.setText(model.getTargetEntity());
        premiumLowerBound.setText(Integer.toString(model.getPremiumLowerBound()));
        claimSettlementRatio.setText(Double.toString(model.getClaimSettlementRatio()));
        amountSettlementRatio.setText(Double.toString(model.getAmountSettlementRatio()));
        planId.setText(model.getPlanId());
        waitingPeriodInDays.setText(Integer.toString(model.getWaitingPeriodInDays()));
        noClaimBonus.setText(Integer.toString(model.getNoClaimBonus()));

        apply = findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " Applied !!! ", Toast.LENGTH_SHORT).show();
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