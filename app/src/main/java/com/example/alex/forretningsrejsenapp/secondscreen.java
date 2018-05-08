package com.example.alex.forretningsrejsenapp;

import android.app.Activity;
import android.content.Intent;
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
    Button btn;
    ImageView imgView;
    static final int CAM_REQUEST = 1;
    private int i = 1;
    private ArrayList listImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondscreen);

        btn = (Button) findViewById(R.id.takepicture);
        imgView = (ImageView) findViewById(R.id.image_view);
        listImage = new ArrayList();

        btn.setOnClickListener(new View.OnClickListener() {
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
        i++;
        //creates new folder in the external storage
        File folder = new File("sdcard/DCIM/Camera/Forretningsrejse_Pictures");

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        //check if folder is available or not
        if (!folder.exists())
        {
            folder.mkdir();
        }

        File image_file = File.createTempFile(imageFileName, " .jpg", folder);
        return image_file;
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data )
    {
        //get image from directory and put it in imageview
        String path = "sdcard/DCIM/Camera/Forretningsrejse_Pictures/app_image.png";
        imgView.setImageDrawable(Drawable.createFromPath(path));
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
}
