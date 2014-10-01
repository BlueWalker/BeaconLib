package com.acompagno.beacon.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.acompagno.beacon.example.fragments.BeaconFragment;
import com.acompagno.beacon.example.fragments.BeaconListFragment;

/**
 * Main activity of the application. 
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public class MainActivity extends ActionBarActivity {

    /**
     * Holds the fragment that displays the list of beacons
     */
    private Fragment mainBeaconListFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBeaconListFragment =  new BeaconListFragment();
        this.getSupportFragmentManager().beginTransaction()
            .replace(R.id.content_frame, mainBeaconListFragment).commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void onBackPressed() {
        final Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (currentFragment!= null && currentFragment instanceof BeaconFragment) {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, mainBeaconListFragment).commit();
        } else {
            this.moveTaskToBack(true);
            super.onBackPressed();
        }
    }

    public void changeFragment(final Fragment fragment) {
        if (fragment != null) {
            this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment).commit();
        }
    }
}
