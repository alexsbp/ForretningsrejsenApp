package com.example.alex.forretningsrejsenapp;

import android.graphics.Bitmap;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by ander on 07-May-18.
 */

public class BusinessTrip {

    //Field
    public String name;
    public int id;
    private ArrayList<Expense> myExpenses = new ArrayList<>();

    //Constructor
    public BusinessTrip(String tripName, int id)
    {
    //Saves the parameters locally
        this.name = tripName;
        this.id  = id;
    }
    public BusinessTrip()
    {

    }

    public void AddExpense(Bitmap picture, float cost, String description, String Genre)
    {
        //Makes the new expense and adds it to the trip list
        int id = myExpenses.size();
        myExpenses.add(new Expense(picture, cost, description, id, Genre));
    }

    public void RemoveExpense(Expense currentExpense)
    {
        //uses the id to remove the expense
        myExpenses.remove(currentExpense.id);
    }

    public int CalcTotalPrice(String sortGenre)
    {
        //local field
        int totalCost = 0;
        //looper alle igennem
        for (Expense expense: myExpenses)
        {
            //sorts by wanted enum (if null use all)
            if(expense.spinner == sortGenre|| sortGenre == null)
            {
                //adds up the price
                totalCost += expense.cost;
            }
        }
        return totalCost;
    }
}
