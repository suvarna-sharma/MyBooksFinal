package com.example.mybooksfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyBooksListScreen extends AppCompatActivity {

    //variables
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    PieChart pieChart;
    //Button
    Button back2;

    String bookCollectionName;
    private CategoriesList categoriesList = new CategoriesList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books_list_screen);


        bookCollectionName = categoriesList.selectedCategory.trim();


        String UserID = FirebaseAuth.getInstance().getUid().trim();
        database = FirebaseDatabase.getInstance();

        ref = database.getReference("User").child(UserID).child("BookCategories").child(bookCollectionName).child("BooksCollection");


        back2 = findViewById(R.id.backButton3);
        listView=findViewById(R.id.booksListView);
        pieChart = findViewById(R.id.piechart);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.book_info, R.id.booksInfo, list);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){
                int count = (int) snapshot.getChildrenCount();
                showPieChart(count);
                    String bookT = ds.child("title").getValue().toString();
                    String bookA = ds.child("author").getValue().toString();
                    String bookP = ds.child("publisher").getValue().toString();
                    String bookD = ds.child("description").getValue().toString();
                    String bookDa = ds.child("date").getValue().toString();

                   snapshot.getChildren();

                    list.add("Book Title: " + bookT + "\n" +  "\n" +
                            "Author: " + bookA + "\n" +  "\n" +
                            "Publisher: " + bookP + "\n" +  "\n" +
                            "Description: " + bookD + "\n" + "\n" +
                            "Date Acquired: " + bookDa);

                    adapter.notifyDataSetChanged();
                }

                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(MyBooksListScreen.this, AddBookScreen.class);
                startActivity(backIntent);
            }
        });



    }

    public void showPieChart(int count){
        String label = "type";

        int CategoryGoal = Integer.parseInt(CategoriesList.goal);
        int CategoryAmount = CategoryGoal - count;
        ArrayList<PieEntry> pieEntries = new ArrayList<PieEntry>();

        Map<String, Integer> typeAmountMap = new HashMap<>();

        typeAmountMap.put("Current Books", count);
        typeAmountMap.put("Books Needed", CategoryAmount);


        ArrayList<Integer> colours = new ArrayList<>();
        colours.add(Color.parseColor("#800080"));
        colours.add(Color.parseColor("#C0C0C0"));


        for (String type: typeAmountMap.keySet())
        {

            pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
        }


        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);

        pieDataSet.setValueTextSize(20f);

        pieDataSet.setColors(colours);

        PieData pieData = new PieData(pieDataSet);

        pieData.setDrawValues(true);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}