package com.example.alex.forretningsrejsenapp;

import android.graphics.Bitmap;
import android.widget.Spinner;

/**
 * Created by ander on 02-May-18.
 */

//for each expense the user make a new instance of this will be made, and that instance should be saved on a list
public class Expense {
    //Fields
    public Bitmap picture;
    public float cost;
    public String description;
    public int id;

    public String spinner;
    //Properties

    //Constructor
    public Expense(Bitmap picture, float cost, String description, int id, String Genre)
    {
        //Saves the given parameters locally
        this.picture = picture;
        this.cost  = cost;
        this.description = description;
        this.id = id;

        this.spinner = Genre;
    }
}
