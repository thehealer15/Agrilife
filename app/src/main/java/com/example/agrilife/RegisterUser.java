package com.example.agrilife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agrilife.model_classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.WriteResult;

public class RegisterUser extends AppCompatActivity {


    private EditText name,mail , password1,password2 , phoneno ,address,aadharno,bankaccno,bankifsc;
    //    private EditText description,sector;
    Button signup;
    private FirebaseAuth mAuth;

    FirebaseFirestore firebaseFirestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth= FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        signup= findViewById(R.id.button3);
        mail = findViewById(R.id.email);
        phoneno = findViewById(R.id.phoneno);
        address = findViewById(R.id.address);
//        description = findViewById(R.id.edittextdescription);
//        sector = findViewById(R.id.edittextsector);
        aadharno = findViewById(R.id.edittextaadharcard);
        bankaccno = findViewById(R.id.edittextaccountno);
        bankifsc =  findViewById(R.id.edittextifsc);


        firebaseFirestore=FirebaseFirestore.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString();
                String passwordstring1 = password1.getText().toString();
                String passwordstring2 = password2.getText().toString();
                String newusername = name.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }
                else if(isEmailValid(email)){
                    if(passwordstring1.isEmpty() || passwordstring2.isEmpty()){
                        Toast.makeText(getApplicationContext(),"enter password please ",Toast.LENGTH_SHORT).show();
                    }

                    else {
                        if(passwordstring1.equals(passwordstring2))
                        {
                            if(newusername.isEmpty() || phoneno.getText().toString().isEmpty() || address.getText().toString().isEmpty() )
                                Toast.makeText(getApplicationContext(),"Please Do not keep a Field Empty  ",Toast.LENGTH_SHORT).show();
                            else{
                                User user = new User(newusername,email,passwordstring1,phoneno.getText().toString(),address.getText().toString(),"fornow",aadharno.getText().toString() ,bankaccno.getText().toString(),bankifsc.getText().toString()," "," ");
                                createAccount(user);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Password does n0t match ",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"enter valid email address",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createAccount(User userobj) {
        //[START create_user_with_email]
        String email = userobj.getEmail();
        String password = userobj.getPassword();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userID= mAuth.getCurrentUser().getUid();
                            userobj.setUid(userID);
                            firebaseFirestore.collection("USERS").document(userID).set(userobj);
                            // block on response if required
//                            System.out.println("Update time : " + future.get().getUpdateTime());


                            Intent intent = new Intent(getApplicationContext(), newLogin.class);
                            Toast.makeText(getApplicationContext(), " Registred Succesfully ", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            // Sign in success, update UI with the signed-in user's information

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // [END create_user_with_email]
    }


    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}