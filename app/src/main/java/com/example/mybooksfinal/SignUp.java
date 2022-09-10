package com.example.mybooksfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText signUpEmail;
    EditText signUpPassword;
    EditText confirmPassword;
    Button signUp;

    //variable to handle firebase
    private FirebaseAuth signUpAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpEmail = findViewById(R.id.editTextEmailAddress);
        signUpPassword = findViewById(R.id.editTextSignUpPassword);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        signUp = findViewById(R.id.signUpButton2);
        signUpAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if the user clicks on this btn --> get the values --> pass into the variables
                if(view.getId() == R.id.signUpButton2){
                    String email = signUpEmail.getText().toString().trim();
                    String password1 = signUpPassword.getText().toString().trim();
                    String password2 = confirmPassword.getText().toString().trim();

                    if(TextUtils.isEmpty(email)){
                        Toast.makeText(SignUp.this, "Enter Your Email Address", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(password1)){
                        Toast.makeText(SignUp.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(password2)){
                        Toast.makeText(SignUp.this, "Confirm Your Password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //range checks --> don't allow passwords less that 6
                    if(password1.length() < 6 || password2.length() < 6){
                        Toast.makeText(SignUp.this, "Password Must be A Minimum Of 6 Characters", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(password2.equals(password1)){

                        //Bring in Firebase
                        //Create a User
                        signUpAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    UserEmail user = new UserEmail(email);
                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            //allow them to proceed to login
                                            Toast.makeText(SignUp.this, "Sign-Up was Successful", Toast.LENGTH_SHORT).show();
                                            Intent loginIntent = new Intent(SignUp.this, Login.class);
                                            startActivity(loginIntent);
                                            finish();

                                        } });
                                }else{

                                    //Notify User That Sign-Up has Failed
                                    Toast.makeText(SignUp.this, "Sign-Up Has Failed", Toast.LENGTH_SHORT).show();
                                } }});

                    }else{

                        //Notify user that their password do not match
                        Toast.makeText(SignUp.this, "Your Passwords Do Not Match", Toast.LENGTH_SHORT).show();
                    } } }}); }}
