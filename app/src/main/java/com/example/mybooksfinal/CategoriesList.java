package com.example.mybooksfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoriesList extends AppCompatActivity {

    //variables
    ListView clistView;
    ArrayList<String> categoryList;
    ArrayAdapter<String> cadapter;
    FirebaseDatabase database;
    //Button
    Button categoryScreen;
    DatabaseReference databaseref;

    public static String selectedCategory,goal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);

        String UserID = FirebaseAuth.getInstance().getUid().trim();
        database = FirebaseDatabase.getInstance();

        databaseref = database.getReference("User").child(UserID).child("BookCategories");

        categoryScreen = findViewById(R.id.backButton5);
        clistView=findViewById(R.id.categoriesListView);

        ArrayList<String> CatList = new ArrayList<>();
        categoryList = new ArrayList<>();
        cadapter= new ArrayAdapter<String>(this, R.layout.category_info, R.id.categoryInfo, categoryList);

        databaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){

                    String catN = ds.child("category").getValue().toString();
                    goal = ds.child("goal").getValue().toString();

                    //bookcategories = ds.getValue(BookCategories.class);
                    snapshot.getChildren();
                    CatList.add(catN);
                    categoryList.add("Book Category: " + catN + "\n" + "\n" + "\n"+
                            "Goal: " + goal);

                    cadapter.notifyDataSetChanged();

                }
                clistView.setAdapter(cadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        clistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selectedCategory = CatList.get(i).trim();
                Intent intent = new Intent(CategoriesList.this, MyBooksListScreen.class);
                startActivity(intent);

            }});

        categoryScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(CategoriesList.this, AddCategoryScreen.class);
                startActivity(backIntent);
            }
        });

    }
}