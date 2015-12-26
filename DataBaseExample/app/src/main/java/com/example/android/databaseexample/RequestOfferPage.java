package com.example.android.databaseexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by satalrej on 12/13/2015.
 */
public class RequestOfferPage extends AppCompatActivity {
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requestoffer);
        turnGPSOn();


        LinearLayout layoutLeft = (LinearLayout) findViewById(R.id.layoutLeft);
        LinearLayout layoutRight = (LinearLayout) findViewById(R.id.layoutRight);
        layoutRight.setVisibility(View.GONE);
        layoutLeft.setVisibility(View.GONE);
        Button offerButton = (Button) findViewById(R.id.offer);
        Button requestButton = (Button) findViewById(R.id.request);
        final TextView textView = (TextView) findViewById(R.id.textView);
        ImageView imageView = (ImageView) findViewById(R.id.imageButton);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

        offerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("You are offering a Ride");
                leftVisible();

            }
        });

        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("You are requesting for Ride");
                rightVisible();
//                FragmentManager mFragmentManager = getFragmentManager();
//                FragmentTransaction mFragmentTransaction = mFragmentManager
//                        .beginTransaction();
//                PrefsFragment mPrefsFragment = new PrefsFragment();
//                mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
//                mFragmentTransaction.commit();
            }
        });


    }
    private void turnGPSOn(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(!provider.contains("gps")){ //if gps is disabled
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }
    private static void showGPSDisabledAlertToUser() {
        // TODO Auto-generated method stub

    }


    private void leftVisible() {
//        LinearLayout layoutFull = (LinearLayout) findViewById(R.id.linearLayout);
//        layoutFull.setVisibility(View.GONE);

        LinearLayout layoutLeft = (LinearLayout) findViewById(R.id.layoutLeft);
        LinearLayout layoutRight = (LinearLayout) findViewById(R.id.layoutRight);
        layoutRight.setVisibility(View.GONE);
        layoutLeft.setVisibility(View.VISIBLE);

    }
    private void rightVisible() {
//        LinearLayout layoutFull = (LinearLayout) findViewById(R.id.linearLayout);
//        layoutFull.setVisibility(View.GONE);
        LinearLayout layoutLeft = (LinearLayout) findViewById(R.id.layoutLeft);
        LinearLayout layoutRight = (LinearLayout) findViewById(R.id.layoutRight);
        layoutLeft.setVisibility(View.GONE);
        layoutRight.setVisibility(View.VISIBLE);

    }


}
