package com.yuhumai.modernartui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity {
    private static final int NUMBER_OF_VIEWS = 5;

    private TextView[] viewArray = new TextView[NUMBER_OF_VIEWS];
    private int[] redArray = new int[NUMBER_OF_VIEWS];
    private int[] greenArray = new int[NUMBER_OF_VIEWS];
    private int[] blueArray = new int[NUMBER_OF_VIEWS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewArray[0] = (TextView) findViewById(R.id.view1);
        viewArray[1] = (TextView) findViewById(R.id.view2);
        viewArray[2] = (TextView) findViewById(R.id.view3);
        viewArray[3] = (TextView) findViewById(R.id.view4);
        viewArray[4] = (TextView) findViewById(R.id.view5);

        for(int i = 0; i < NUMBER_OF_VIEWS; i++) {
            Random random = new Random();
            redArray[i] = random.nextInt(255);
            blueArray[i] = random.nextInt(255);
            greenArray[i] = random.nextInt(255);
        }

        for(int i = 0; i < NUMBER_OF_VIEWS; i++) {
            setBackgroundColorForView(i, 0);
        }

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for(int i = 0; i < NUMBER_OF_VIEWS; i++) {
                    setBackgroundColorForView(i, progress);
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

    void setBackgroundColorForView(int index, int progress) {
        int red = redArray[index];
        int blue = blueArray[index];
        int green = (greenArray[index] + progress) % 255;
        if (green < 0) {
            green = 0;
        } else if(green > 255) {
            green = 255;
        }

        int color = Color.rgb(red, blue, green);

        viewArray[index].setBackgroundColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_more_information) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialog_message);
            builder.setNegativeButton(R.string.dialog_negative_button, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                    startActivity(browserIntent);
                }
            });
            builder.setPositiveButton(R.string.dialog_positive_button, null);

            AlertDialog dialog = builder.create();
            dialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
