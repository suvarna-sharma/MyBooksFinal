package com.example.mybooksfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCategoryScreen extends AppCompatActivity {

    //Variables
    EditText categoryName, goal;
    Button add, categoryListScreen, back;


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref;
    String UserID = FirebaseAuth.getInstance().getUid().trim();
    String categoryN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_screen);

        dbref = db.getReference("User").child(UserID);

        categoryName = findViewById(R.id.editTextCategoryName);
        goal = findViewById(R.id.editTextCategoryGoal);
        add = findViewById(R.id.AddCategoryAndGoal);
        categoryListScreen = findViewById(R.id.backButton4);
        back = findViewById(R.id.backButton6);


        //add category and goal to the realtime database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                categoryN = categoryName.getText().toString();
                BookCategories bookCategories = new BookCategories(categoryName.getText().toString(), goal.getText().toString());

                dbref.child("BookCategories").child(categoryN).setValue(bookCategories).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AddCategoryScreen.this, "Category and Goal added successfully", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(AddCategoryScreen.this, "Category and Goal was not added", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
        categoryListScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(AddCategoryScreen.this, CategoriesList.class);
                startActivity(back);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(AddCategoryScreen.this, MainActivity.class);
                startActivity(back);
            }
        });

    }
}