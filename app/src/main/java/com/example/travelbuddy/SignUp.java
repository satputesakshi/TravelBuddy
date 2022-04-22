package com.example.travelbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    //Declare Variables
    Button loginBtn;
    Button signupBtn;
    TextView txtEmail, txtPassword, txtPhone, txtCountry, txtName, txtConfirmPass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        loginBtn = findViewById(R.id.btnLogin);
        signupBtn = findViewById(R.id.btnSignup);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtPhone = findViewById(R.id.phoneTxt);
        txtCountry = findViewById(R.id.countryTxt);
        txtName = findViewById(R.id.txtName);
        txtConfirmPass = findViewById(R.id.txtConfirmPass);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPass = txtConfirmPass.getText().toString();
                String name = txtName.getText().toString();
                String phone = txtPhone.getText().toString();
                String country = txtCountry.getText().toString();
                //Form Validations
                if(confirmPass != password)
                {
                    Toast.makeText(SignUp.this,"Password don't match", Toast.LENGTH_SHORT).show();
                }

                if(email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty() || country.isEmpty() || confirmPass.isEmpty())
                {
                    txtEmail.setError("This field cannot be blank.");
                    txtPassword.setError("This field cannot be blank.");
                    txtConfirmPass.setError("This field cannot be blank.");
                    txtName.setError("This field cannot be blank.");
                    txtPhone.setError("This field cannot be blank.");
                    txtCountry.setError("This field cannot be blank.");
                } else
                {
                    //Register user data using firebase
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUp.this, "Authentication successfull.", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        String uid = mAuth.getUid();

                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        DatabaseReference myRef = database.getReference("Users");

                                        Visa visa = new Visa(name, email, phone, country);
                                        myRef.child(uid).setValue(visa);

                                        Intent intent = new Intent(SignUp.this, Login.class);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(SignUp.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }


            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }
}