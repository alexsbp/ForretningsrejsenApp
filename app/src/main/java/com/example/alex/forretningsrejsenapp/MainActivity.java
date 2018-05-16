package com.example.alex.forretningsrejsenapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Fields
    public ArrayList<BusinessTrip> myBusinessTrip = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    //removes the keyboard so it aint getting stuck
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    //change scene
                    setContentView(R.layout.activity_main);
                }
                else
                {
                    //makes a toast to tell user the error
                    Toast toast = Toast.makeText(MainActivity.this  , "You need to enter a name for the business trip", Toast.LENGTH_SHORT);
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
        int i = 0;
        for (BusinessTrip trip: myBusinessTrip)
        {
            //tripNames[i] = trip.name;
        }
    }


    //removes a businessTrip from the list
    public void RemoveTrip(int id)
    {
        //removing the trip from the list
        myBusinessTrip.remove(id);
        //updates the spinner
        //tripNames = new String[id];
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
}