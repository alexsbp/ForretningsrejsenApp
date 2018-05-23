package com.example.alex.forretningsrejsenapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SeeExpenses extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seeexpenses);


        ListAdapter adapter = new ArrayAdapter<BusinessTrip>(this, android.R.layout.simple_list_item_1, MainScreen.myBusinessTrip);
        ListView ExpensesListView = (ListView) findViewById(R.id.ExpensesListView);
        ExpensesListView.setAdapter(adapter);
    }

    public void TestMethod()
    {

    }


}
