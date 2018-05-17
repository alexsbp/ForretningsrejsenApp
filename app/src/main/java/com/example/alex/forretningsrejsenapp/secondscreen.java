package com.example.alex.forretningsrejsenapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alex on 03-05-2018.
 */

public class secondscreen extends Activity implements AdapterView.OnItemSelectedListener
{
    Button btn2;
    ImageView imgView;
    EditText priceTxt;
    EditText descriptionTxt;
    Spinner spinner, spinner2;
    String spinnerItem;
    private static final int PICK_IMAGE = 1;

    public static Bitmap scaled;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondscreen);

        btn2 = (Button) findViewById(R.id.choosepicture);
        imgView = (ImageView) findViewById(R.id.image_view);
        priceTxt = (EditText) findViewById(R.id.priceText);
        descriptionTxt = (EditText) findViewById(R.id.descriptionText);
        spinner = (Spinner) findViewById(R.id.spinner);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openGallery();
            }
        }
        );

        // Create an ArrayAdapter using the businesstrip list and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, EnumExpense.values());
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    private void openGallery()
    {
        //makes sure its only pictures that is being displayed
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE && null != data)
        {
            Uri uri = data.getData();
            try
            {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                imgView.setImageBitmap(scaled);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {}

    public void Save (View v)
    {
        float priceText = Float.parseFloat(priceTxt.getText().toString());
        String descriptionText = descriptionTxt.getText().toString();
        spinnerItem = spinner.getSelectedItem().toString();
        try {

            MainActivity.myBusinessTrip.get(0).AddExpense(EnumExpense.Transport, scaled, priceText, descriptionText, spinnerItem);
            Toast.makeText(this, "added to list", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void MainSpinner()
    {
        spinner2 = (Spinner) findViewById(R.id.businesstripSpinner);
        // Create an ArrayAdapter using the businesstrip list and a default spinner layout
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, MainActivity.categories2);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);
    }
}
