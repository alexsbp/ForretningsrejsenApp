package com.example.alex.forretningsrejsenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Used for holding all expenses of current Trip
    private ArrayList<Expense> myExpenses = new ArrayList<>();

    //hvis kamera ikke virker, prøv som alternativt at tage fat på galleri.
    public void TakePicture (View v)
    {

    }

    public void Save (View v)
    {

    }

    public void AddNewExpense(View v)
    {
        Intent intent = new Intent(this, secondscreen.class);
        startActivity(intent);
    }
}