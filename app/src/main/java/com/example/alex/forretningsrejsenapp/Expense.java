package com.example.alex.forretningsrejsenapp;

/**
 * Created by ander on 02-May-18.
 */

//for each expense the user make a new instance of this will be made, and that instance should be saved on a list
public class Expense {
    //Fields
    public EnumExpense genre;
    public Object picture;
    public float cost;
    public String description;

    //Properties

    //Constructor
    public Expense(EnumExpense genre, Object picture, float cost, String description)
    {
        //Saves the given parameters locally
        this.genre = genre;
        this.picture = picture;
        this.cost  = cost;
        this.description = description;
    }
}
