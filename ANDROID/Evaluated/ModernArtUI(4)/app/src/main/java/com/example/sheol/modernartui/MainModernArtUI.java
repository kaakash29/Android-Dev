package com.example.sheol.modernartui;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;
import android.graphics.Color;


public class MainModernArtUI extends ActionBarActivity {

    private ArrayList<TextView> rectangles;
    private DialogFragment mDialog;

    private static final String TAG = "Lab-ModernArtUI";
    private static final String SITE_URL = "http://www.moma.org/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_modern_art_ui);

        Log.i(TAG, "Start onCreate()");

        //Create ArrayList of TextView from activity_main_modern_art_ui.xml
        this.rectangles = new ArrayList<TextView>();
        //add TextView to list rectangles
        this.rectangles.add((TextView) findViewById(R.id.leftTop));
        this.rectangles.add((TextView) findViewById(R.id.leftBottom));
        this.rectangles.add((TextView) findViewById(R.id.rightTop));
        this.rectangles.add((TextView) findViewById(R.id.rightMiddle));
        this.rectangles.add((TextView) findViewById(R.id.rightBottom));

        //find SeekBar
        SeekBar mySeekBar = (SeekBar) findViewById(R.id.seekBar);

        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.i(TAG, "Entered  onProgressChanged()");

                for (TextView rectangle : rectangles) {

                    Log.i(TAG, "Start Detect color");
                    //Detect color of element
                    int myColor = 0;
                    if (rectangle.getId() == R.id.leftTop) {
                        myColor = getResources().getColor(R.color.leftTop);
                    } else if (rectangle.getId() == R.id.leftBottom) {
                        myColor = getResources().getColor(R.color.leftBottom);
                    } else if (rectangle.getId() == R.id.rightTop) {
                        myColor = getResources().getColor(R.color.rightTop);
                    } else if (rectangle.getId() == R.id.rightBottom) {
                        myColor = getResources().getColor(R.color.rightBottom);
                    }

                    Log.i(TAG, "Start Change color");
                    //change color
                    int red = Color.red(myColor);
                    int green = Color.green(myColor);
                    int blue = Color.blue(myColor);
                    if (red > green && red > green) {
                        if (red - progress > 0 && green - progress > 0 && blue - progress > 0) {
                            rectangle.setBackgroundColor(Color.rgb(red - progress, green, blue));
                        }
                    } else if (green > red && green > red) {
                        if (red - progress > 0 && green - progress > 0 && blue - progress > 0) {
                            rectangle.setBackgroundColor(Color.rgb(red, green - progress, blue));
                        }
                    } else if (blue > red && blue > green) {
                        if (red - progress > 0 && green - progress > 0 && blue - progress > 0) {
                            rectangle.setBackgroundColor(Color.rgb(red, green, blue - progress));
                        }
                    } else {
                        if (red - progress > 0 && green - progress > 0 && blue - progress > 0) {
                            rectangle.setBackgroundColor(Color.rgb(red - progress, green - progress, blue - progress));
                        }
                    }
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_modern_art_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            openNewDialog();
            Log.i(TAG, "Entered onOptionsItemSelected()");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openNewDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_text)
                .setPositiveButton(R.string.dialog_not_now, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i(TAG, "Entered PositiveButton.onClick()");
                    }
                })
                .setNegativeButton(R.string.dialog_visit_moma, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i(TAG, "Entered NegativeButton.onClick()");
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(SITE_URL));
                        startActivity(browserIntent);
                    }
                })
                .show();
    }
}