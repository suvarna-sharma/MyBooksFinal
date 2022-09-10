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

public class Login extends AppCompatActivity {

    //variables
    EditText username;
    EditText password;
    Button loginbtn;
    Button signUpbtn;

    //variable to handle firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        signUpbtn = findViewById(R.id.signUpButton1);
        loginbtn = findViewById(R.id.loginButton);
        mAuth = FirebaseAuth.getInstance();

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignUpIntent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(SignUpIntent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{
                    //Get the email address and password
                    String email = username.getText().toString().trim();
                    String loginPassword = password.getText().toString().trim();

                    //houseKeeping (error handling)
                    //Checks for the username field to be blank
                    if(TextUtils.isEmpty(email)){

                        //Notifies user that username is blank
                        Toast.makeText(Login.this, "Enter Your Email Address To Login", Toast.LENGTH_SHORT).show();
                        //Focuses on the username
                        username.requestFocus();
                        //Returns to the users main page
                        return;
                    }
                    if(TextUtils.isEmpty(loginPassword)){

                        //Notifies user that password is blank
                        Toast.makeText(Login.this, "Enter Your Password To Login", Toast.LENGTH_SHORT).show();
                        //Focuses on the password
                        password.requestFocus();
                        //Returns to the users main page
                        return;

                    }

                    mAuth.signInWithEmailAndPassword(email, loginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                //Clear edit textboxes
                                username.setText("");
                                password.setText("");
                                username.requestFocus();

                                //Take user to the next screen once they have logged in successfully
                                Intent MainActIntent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(MainActIntent);

                            }else{

                                //inform the user their login has failed
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }catch(Exception ex){

                    //Inform the user their login has failed
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
}