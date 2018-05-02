package com.example.alex.forretningsrejsenapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Used for holding all expenses of current Trip
    private List<Expense> myExpenses;

    public void TakePicture (View v)
    {

    }

    public void AddNote (View v)
    {

    }
    public void AddMoney (View v)
    {

    }
    public void Save (View v)
    {

    }
}