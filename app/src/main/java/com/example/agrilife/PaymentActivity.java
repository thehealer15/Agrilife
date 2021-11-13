package com.example.agrilife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    private final String RAZORPAY_API_KEY = "rzp_test_VVzU2NEnzy8lWP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Snackbar.make(findViewById(android.R.id.content), "Please Pay", Snackbar.LENGTH_LONG);
        payPremium();
    }
    private void payPremium() {
        int amount= 50000; // 500 rs insurance premium
        Checkout checkout = new Checkout();
        checkout.setKeyID(RAZORPAY_API_KEY);
        checkout.setImage(R.drawable.agri_insurance);

        JSONObject object = new JSONObject();
        try{
            object.put("name","AgriLife");
            object.put("description","Pay Premium of Insurance Plan");
            object.put("theme.color", "");
            object.put("currency", "INR");
            object.put("amount", amount);
            object.put("prefill.contact", "7666278774");
            object.put("prefill.email", "akshaygpawar15@gmail.com");

            // opening razorpay's  checkout activity
            checkout.open(PaymentActivity.this, object);
        }
        catch (Exception e){
            Snackbar.make(findViewById(android.R.id.content), "Something went wrong", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Snackbar.make(findViewById(android.R.id.content), "Payment Successful", Snackbar.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "successfull !\nTx ID - " + s, Toast.LENGTH_LONG).show();
        Intent i =new Intent(PaymentActivity.this,MainActivity.class);
        startActivity(i);
        Snackbar.make(findViewById(android.R.id.content), "Payment Successful", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Something went wrong", Snackbar.LENGTH_LONG);
        snackbar.show();
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