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
import android.media.MediaPlayer;

import java.util.ArrayList;

public class MainScreen extends Activity {
    //Fields
    public static ArrayList<BusinessTrip> myBusinessTrip = new ArrayList<BusinessTrip>();
    public static ArrayList<String> tripNames = new ArrayList<String>();
    public static Object currentTrip;
    public int totalCost;
    public static String newName;
    //Lyd Fields
    public static MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        //MediaPlayer til lyd i app
        mediaPlayer = MediaPlayer.create(this, R.raw.wosh);
        //Checks if this is the first time the app is started up
        if(myBusinessTrip.size() < 1)
        {
            //Sets view to Startup view
            setContentView(R.layout.start_up);
        }
        //button onClick
        final Button startUp = findViewById(R.id.firstTrip);
        final EditText userInput = findViewById(R.id.newTripName);

        //StartupListener:
        startUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //gets the input
               newName = userInput.getText().toString();
                //Checks if the input isnt null
                if(newName.length() > 0)
                {
                    //adds the trip to list
                    NewTrip(v);
                    currentTrip = myBusinessTrip.get(0);
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
    public void NewTrip(View v)
    {
        if(newName.length()>0){
            //Makes the new expense and adds it to the trip list
            int id = myBusinessTrip.size();
            myBusinessTrip.add(new BusinessTrip(newName, id));
            tripNames.add(newName);
            //adds the trip to the list
            mediaPlayer.start();
            setContentView(R.layout.main_screen);
        }
        else
            {
            //makes a toast to tell user the error
            Toast toast = Toast.makeText(MainScreen.this  , "You need to enter a name for the business trip", Toast.LENGTH_SHORT);
            toast.show();
        }
        newName =null;
    }

    //removes a businessTrip from the list
    public void RemoveTrip(int id)
    {
        /*
        //removing the trip from the list + spinner list
        myBusinessTrip.remove(id);
        tripNames.remove(id);
        ArrayList<BusinessTrip> tmp = new ArrayList<BusinessTrip>();
        ArrayList<String> tmpNames = new ArrayList<String>();
        //updates the spinner list + resets all id's
        int i = 0;
        for (BusinessTrip trip: myBusinessTrip)
        {
            trip.id = i;
            tmp.add(trip);
            tmpNames.add(trip.name);
            //removes the trip from this list
            tmp.remove(trip);
        }
        //resets the lists so the tmp lists gets set instead
        myBusinessTrip = tmp;
        currentTrip = myBusinessTrip.get(0);
        */
    }

    public void AddNewExpense(View v)
    {
        mediaPlayer.start();
        Intent intent = new Intent(this, secondscreen.class);
        startActivity(intent);
    }

    public void SeeExpenses(View view)
    {
        mediaPlayer.start();
        Intent intent = new Intent(this, SeeExpenses.class);
        startActivity(intent);
    }

    public void Cost (View v)
    {
        if(((BusinessTrip)currentTrip).myExpenses.size()>0){
            totalCost = ((BusinessTrip) currentTrip).CalcTotalPrice(null);

            Toast toast = Toast.makeText(MainScreen.this  , "Total expense: "+ totalCost, Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(MainScreen.this  , "No Expenses yet", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void RemoveCurrently (View v)
    {
        if(myBusinessTrip.size()>1){
            RemoveTrip(((BusinessTrip)currentTrip).id);
        }else{
            //makes a toast to tell user the error
            Toast toast = Toast.makeText(MainScreen.this  , "Must have minimum 2 businesstrips, Create a new before deleting this trip", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void SaveNameh (View v){

        final  EditText newnametrip = findViewById(R.id.businesstripNameText);
        newName = newnametrip.getText().toString();
        mediaPlayer.start();
        NewTrip(v);
    }

    public void NewButton(View v){
        mediaPlayer.start();
        setContentView(R.layout.newtrip);
    }
}
