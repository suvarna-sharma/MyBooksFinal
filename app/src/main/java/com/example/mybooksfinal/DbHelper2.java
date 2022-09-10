package com.example.mybooksfinal;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DbHelper2 {

    /*private DatabaseReference dbRef;
    String UserID = FirebaseAuth.getInstance().getUid().trim();
    AddBookScreen addBookScreen = new AddBookScreen();

    public DbHelper2(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        //dbRef = db.getReference(BookCategories.class.getSimpleName());
        dbRef = db.getReference("User").child(UserID);

    }

    public Task<Void> addCategory(BookCategories bookCategories){

        return dbRef.push().setValue(bookCategories);

    }*/
}
