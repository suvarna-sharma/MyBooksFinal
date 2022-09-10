package com.example.mybooksfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddBookScreen extends AppCompatActivity {


    //Variables
    TextView titleLabel, authorLabel, publisherLabel, descriptionLabel, dateLabel;
    EditText edTitle, edAuthor, edPublisher, edDescription, edAcqDate;
    Button addBook, back, camerabtn, uploadbtn, homebtn, updatebtn;

    DatabaseReference databaseReference;
    String bookN;
    String bookCollectionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_screen);

        String UserID = FirebaseAuth.getInstance().getUid().trim();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        CategoriesList categoriesList = new CategoriesList();
        bookCollectionName = categoriesList.selectedCategory;

        databaseReference = db.getReference("User").child(UserID).child("BookCategories").child(bookCollectionName);

        //type casting
        //labels
        titleLabel = findViewById(R.id.textViewBookTitle);
        authorLabel = findViewById(R.id.textViewAuthor);
        publisherLabel = findViewById(R.id.textViewPublisher);
        descriptionLabel = findViewById(R.id.textViewDescription);
        dateLabel = findViewById(R.id.textViewDateAcquired);

        //edit texts
        edTitle = findViewById(R.id.editTextBookTitle);
        edAuthor = findViewById(R.id.editTextAuthor);
        edPublisher = findViewById(R.id.editTextTextPublisher);
        edDescription = findViewById(R.id.editTextDescription);
        edAcqDate = findViewById(R.id.editTextDateAcquired);

        //buttons
        addBook = findViewById(R.id.addBookButton);
        back = findViewById(R.id.backButton);
        camerabtn = findViewById(R.id.cameraButton);
        uploadbtn = findViewById(R.id.uploadImageButton);
        homebtn = findViewById(R.id.homeScreen);

        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(AddBookScreen.this, Camera.class);
                startActivity(cameraIntent);
            }
        });
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uploadIntent = new Intent(AddBookScreen.this, CaptureAndUpload.class);
                startActivity(uploadIntent);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(AddBookScreen.this, MainActivity.class);
                startActivity(back);
            }
        });

        //add books to the realtime database
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bookN = edTitle.getText().toString();
                BooksCollection booksCollection = new BooksCollection(edTitle.getText().toString(),
                        edAuthor.getText().toString(), edPublisher.getText().toString(),
                        edDescription.getText().toString(), edAcqDate.getText().toString());

                databaseReference.child("BooksCollection").child(bookN).setValue(booksCollection).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddBookScreen.this, "Book was added", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(AddBookScreen.this, "Book was not added", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent back = new Intent(AddBookScreen.this, MyBooksListScreen.class);
                        startActivity(back);
                    }
                });








            }
        });
    }}