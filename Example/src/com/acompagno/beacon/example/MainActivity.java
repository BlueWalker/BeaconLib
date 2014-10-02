package com.acompagno.beacon.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import com.acompagno.beacon.example.fragments.BeaconFragment;
import com.acompagno.beacon.example.fragments.BeaconListFragment;

/**
 * Main activity of the application. 
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeFragment(new BeaconListFragment());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void onBackPressed() {
        final Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (currentFragment!= null && currentFragment instanceof BeaconFragment) {
            goBackToMainList();
        } else {
            this.moveTaskToBack(true);
            super.onBackPressed();
        }
    }

    /**
     * Changes the fragment being displayed in the content_frame and pushes
     * that transaction into the back stack. 
     * 
     * @param fragment Fragment
     */
    public void changeFragment(final Fragment fragment) {
        if (fragment != null) {
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null) 
                    .commit();
        }
    }

    /**
     * Checks if there are entries in the BackStack. If there are entries,
     * the most recent one is popped. If there aren't any entries, nothing happens. 
     */
    private void goBackToMainList() {
        final FragmentManager fragmentManager = this.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }
}
