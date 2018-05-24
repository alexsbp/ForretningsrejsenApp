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
    public MediaPlayer mediaPlayer;
    private TextView txsong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        //MediaPlayer til lyd i app
        txsong.setText("Song.mp3");
        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        //Checks if this is the first time the app is started up
        if(myBusinessTrip.size() < 1)
        {
            //Sets view to Startup view
            setContentView(R.layout.start_up);
        }
        mediaPlayer.start();

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
    public void NewTrip(View v)
    {
        if(newName.length()>0){
            //Makes the new expense and adds it to the trip list
            int id = myBusinessTrip.size();
            myBusinessTrip.add(new BusinessTrip(newName, id));
            //adds the trip to the list
            tripNames.add(newName);
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
    public void RemoveTrip(BusinessTrip tripForDelete)
    {
        /*
        //removing the trip from the list + spinner list
        tripNames.remove(tripForDelete.id);
        myBusinessTrip.remove(tripForDelete.id);
        //resets the tripNames
        for (String name: tripNames)
        {
            //removes the trip from this list
            name = null;
        }
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
        tripNames = tmpNames;
        currentTrip = myBusinessTrip.get(0);
    */
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

    public void RemoveCurrently (View v)
    {
        if(myBusinessTrip.size()>1){
            RemoveTrip((BusinessTrip)currentTrip);
        }else{
            //makes a toast to tell user the error
            Toast toast = Toast.makeText(MainScreen.this  , "Must have minimum 2 businesstrips, Create a new before deleting this trip", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void SaveNameh (View v){

        final  EditText newnametrip = findViewById(R.id.businesstripNameText);
        newName = newnametrip.getText().toString();
        NewTrip(v);
    }

    public void NewButton(View v){
        setContentView(R.layout.newtrip);
    }
}
