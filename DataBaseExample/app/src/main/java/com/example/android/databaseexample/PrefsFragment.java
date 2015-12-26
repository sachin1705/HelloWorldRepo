package com.example.android.databaseexample;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by satalrej on 12/13/2015.
 */
public class PrefsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferencefragment);
    }
}
