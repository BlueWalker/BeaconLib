package com.acompagno.beacon.example;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

    private String[] drawerTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private CharSequence openTitle;
    private CharSequence fragmentTitle;

    public class MyActionBarDrawerToggle extends ActionBarDrawerToggle {

        public MyActionBarDrawerToggle(final Activity activity, final DrawerLayout drawerLayout) {
            super(activity,
                    drawerLayout,
                    R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                    R.string.drawer_open,  /* "open drawer" description */
                    R.string.drawer_close  /* "close drawer" description */);
        }
        /** Called when a drawer has settled in a completely closed state. */
        public void onDrawerClosed(View view) {
            setTitle(fragmentTitle);
        }

        /** Called when a drawer has settled in a completely open state. */
        public void onDrawerOpened(View drawerView) {
            setTitle(openTitle);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set the fragment that will first appear in content frame  
        this.getSupportFragmentManager().beginTransaction()
        .replace(R.id.content_frame, new BeaconListFragment()).commit();
        //Set mFragmentTitle (this value is used later on, it keeps track of what the )
        fragmentTitle = getResources().getString(R.string.app_name);

        //set the open title. this will be the title whenever the drawer is opened
        openTitle = "Choose An Example";

        //Drawer layout setup
        //This array holds the text for each element in the drawer
        drawerTitles = new String[]{"Home"};
        //get the drawer layout from the xml file
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //get thelistview from the xml file
        drawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view. the adapter sets whats going to be in the listview 
        drawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, drawerTitles));

        // Set the list's click listener. the method for this is defined later on
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        //Drawer toggle not sure what exactly this does 
        drawerToggle = new MyActionBarDrawerToggle(this, drawerLayout);

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    //Drawer layout methods
    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

    //    //Override what happens whent the back button is pressed 
    //    @Override
    //    public void onBackPressed() {
    //        //if an example fragment is being displayed, push the home fragment into the content frame
    //        if (getSupportFragmentManager().findFragmentById(R.id.content_frame).toString().contains("Example")) {
    //            setTitle("Android Example");
    //            this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MainPageFragment()).commit();
    //        } else {
    //            //if we're home, just do what the back button usually does
    //            // make sure our application gets pushed back to the application stack
    //            this.moveTaskToBack(true);
    //            super.onBackPressed();
    //        }
    //    }

    //allows us to change the title on the action bar from a fragment
    public void changeTitleFromFragment(CharSequence title) {
        fragmentTitle = title;
        setTitle(title);
    }
}
