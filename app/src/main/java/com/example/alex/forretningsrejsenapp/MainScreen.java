package com.example.alex.forretningsrejsenapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainScreen extends Activity {
    //Fields
    public static ArrayList<BusinessTrip> myBusinessTrip = new ArrayList<BusinessTrip>();
    public static ArrayList<String> tripNames = new ArrayList<String>();
    public static Object currentTrip;
    public static String[]  myStringArray={"A","B","C"};
    public int totalCost;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        for (BusinessTrip B : myBusinessTrip)
        {
            
        }

       /* // Makes the spinner
        tripNames.add("test");
        tripNames.add("test2");
        Spinner spinner = findViewById(R.id.businesstripSpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tripNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);*/


        //Checks if this is the first time the app is started up
        if(myBusinessTrip.size() < 1)
        {
            //Sets view to Startup view
            setContentView(R.layout.start_up);
        }

        //button onClick
        final Button startUp = findViewById(R.id.firstTrip);
        final EditText userInput = (EditText)findViewById(R.id.newTripName);

        //StartupListener:
        startUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //gets the input
                String tmpInput = userInput.getText().toString();
                //Checks if the input isnt null
                if(tmpInput.length() > 0)
                {
                    //adds the trip to list
                    NewTrip(tmpInput);
                    currentTrip = myBusinessTrip.get(0);
                    //change scene
                    setContentView(R.layout.main_screen);
                }
                else
                {
                    //makes a toast to tell user the error
                    Toast toast = Toast.makeText(MainScreen.this  , "You need to enter a name for the business trip", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }); //StartupListener End
    }

    //Adds a businessTrip to the list
    public void NewTrip(String tripName)
    {
        //Makes the new expense and adds it to the trip list
        int id = myBusinessTrip.size();
        myBusinessTrip.add(new BusinessTrip(tripName, id));
        //adds the trip to the list
        tripNames.add(tripName);
    }


    //removes a businessTrip from the list
    public void RemoveTrip(BusinessTrip tripForDelete)
    {
        //removing the trip from the list + spinner list
        tripNames.remove(tripForDelete.id);
        myBusinessTrip.remove(tripForDelete.id);

        ArrayList<String> tripNames = new ArrayList<String>();

        //updates the spinner list + resets all id's
        int i = 0;
        for (BusinessTrip trip: myBusinessTrip)
        {
            trip.id = i;
            //tripNames[i] = trip.name;
        }
    }

    public void AddNewExpense(View v)
    {
        Intent intent = new Intent(this, secondscreen.class);
        startActivity(intent);
    }

    public void SeeExpenses(View view)
    {
        Intent intent = new Intent(this, SeeExpenses.class);
        startActivity(intent);
    }

    public void Cost (View v)
    {
        totalCost = ((BusinessTrip) currentTrip).CalcTotalPrice(null);
    }
}
