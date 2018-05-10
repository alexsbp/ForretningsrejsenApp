package com.example.alex.forretningsrejsenapp;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URI;
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
    static final int CAM_REQUEST = 1;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondscreen);

        //btn = (Button) findViewById(R.id.takepicture);
        btn2 = (Button) findViewById(R.id.choosepicture);
        imgView = (ImageView) findViewById(R.id.image_view);

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

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dropdown_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

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
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
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

    }
}
