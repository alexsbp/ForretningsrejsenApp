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
    Button btn, btn2;
    ImageView imgView;
    EditText priceTxt;
    EditText descriptionTxt;
    static final int CAM_REQUEST = 1;
    private static final int PICK_IMAGE = 1;

    public static Bitmap scaled;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondscreen);

        //btn = (Button) findViewById(R.id.takepicture);
        btn2 = (Button) findViewById(R.id.choosepicture);
        imgView = (ImageView) findViewById(R.id.image_view);
        priceTxt = (EditText) findViewById(R.id.priceText);
        descriptionTxt = (EditText) findViewById(R.id.descriptionText);


        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent camera_intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                File file = null;
                try {
                    file = GetFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        }
        );*/

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openGallery();
            }
        }
        );




    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    private File GetFile() throws IOException {
        //creates new folder in the external storage
        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Test");
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File path = android.os.Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PIC_" + timeStamp + "_";

        //check if folder is available or not
        if (!folder.exists())
        {
            folder.mkdir();
        }

        File image_file = File.createTempFile(imageFileName, " .jpg", folder);
        return image_file;
    }

    private void openGallery()
    {
        /*Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);*/

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
        try {

            MainActivity.myBusinessTrip.get(0).AddExpense(EnumExpense.Transport, scaled, priceText, descriptionText);
            Toast.makeText(this, "added to list", Toast.LENGTH_LONG).show();

            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            // Create an ArrayAdapter using the businesstrip list and a default spinner layout
            ArrayAdapter<BusinessTrip> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, MainActivity.categories);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
