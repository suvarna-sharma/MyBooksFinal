package com.example.mybooksfinal;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DbHelper {

    /*private DatabaseReference databaseReference;
    String UserID = FirebaseAuth.getInstance().getUid().trim();
    AddBookScreen addBookScreen = new AddBookScreen();

    public DbHelper(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        //databaseReference = db.getReference(BooksCollection.class.getSimpleName());
        databaseReference = db.getReference("User").child(UserID);

    }

    public Task<Void> addItem(BooksCollection booksCollection){

        return databaseReference.push().setValue(booksCollection);

    }*/

}
