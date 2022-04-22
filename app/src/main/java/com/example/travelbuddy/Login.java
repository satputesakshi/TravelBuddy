package com.example.travelbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelbuddy.ui.home.HomeFragment;
import com.example.travelbuddy.ui.home.HomeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    //Declare Variables
    Button signupBtn , loginBtn;
    private FirebaseAuth mAuth;
    TextView txtemail, txtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        signupBtn = findViewById(R.id.signUpBtn);
        loginBtn = findViewById(R.id.btnLogin);
        txtemail= findViewById(R.id.email);
        txtpassword= findViewById(R.id.pass);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtemail.getText().toString();
                String password = txtpassword.getText().toString();
                //Login fields validation
                if(email.isEmpty() || password.isEmpty())
                {
                    txtemail.setError("This field cannot be blank.");
                    txtpassword.setError("This field cannot be blank.");
                } else {
                    //Authenticate user credentials using firebase
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //Successful Login
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Login.this, "Login Successful.", Toast.LENGTH_SHORT).show();

                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(mAuth.getCurrentUser().getUid())
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    //Set Global Variables
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        Global.currentname = snapshot.getValue(Visa.class);
                                                        Global.currentemail = snapshot.getValue(Visa.class);
                                                        Global.currentphone = snapshot.getValue(Visa.class);
                                                        Global.currentcountry = snapshot.getValue(Visa.class);
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });

                                        Intent intent=new Intent(Login.this, MainActivity.class);
                                        startActivity(intent);
                                        FirebaseUser user = mAuth.getCurrentUser();


                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user
                                        Toast.makeText(Login.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });

    }
}