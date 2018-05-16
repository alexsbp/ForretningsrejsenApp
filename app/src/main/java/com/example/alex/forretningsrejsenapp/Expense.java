package com.example.alex.forretningsrejsenapp;

import android.graphics.Bitmap;

/**
 * Created by ander on 02-May-18.
 */

//for each expense the user make a new instance of this will be made, and that instance should be saved on a list
public class Expense {
    //Fields
    public EnumExpense genre;
    public Bitmap picture;
    public float cost;
    public String description;
    public int id;
    //Properties

    //Constructor
    public Expense(EnumExpense genre, Bitmap picture, float cost, String description, int id)
    {
        //Saves the given parameters locally
        this.genre = genre;
        this.picture = picture;
        this.cost  = cost;
        this.description = description;
        this.id = id;
    }
}
