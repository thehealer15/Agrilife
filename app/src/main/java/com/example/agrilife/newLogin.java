package com.example.agrilife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class newLogin extends AppCompatActivity {


    private EditText username , password;
    Button login,signup;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);


        username =(EditText) findViewById(R.id.editTextTextEmailAddress);
        mAuth= FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        password = (EditText) findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String passwordstring = password.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }
                else if(isEmailValid(email)){
                    if(passwordstring.isEmpty()){
                        Toast.makeText(getApplicationContext(),"enter password please ",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        signIn(email,passwordstring);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"enter valid email address",Toast.LENGTH_SHORT).show();
                }
            }
        });

        signup = findViewById(R.id.button2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterUser.class);
                startActivity(intent);
            }
        });

    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser userref = mAuth.getCurrentUser();
                            String userID =userref.getUid();
                            String currentusername  = "someusernamefornow";
//                            Toast.makeText(getApplicationContext(), "Current user is :" + currentusername, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}