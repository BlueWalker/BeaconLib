package com.acompagno.beacon.example.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.acompagno.beacon.Beacon;
import com.acompagno.beacon.example.R;

public class BeaconFragment extends Fragment {
    
    private static final String TEXT_VIEW_FORMAT = "UUID - %s\nMajor %d\nMinor %d";
    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(final LayoutInflater inflater, 
            final ViewGroup container, final Bundle savedInstanceState) {
        final ScrollView mainScroll = (ScrollView) inflater.inflate(R.layout.beacon_layout, null);
        final TextView beaconNameTextView = (TextView)mainScroll.findViewById(R.id.beacon_name);
        final Beacon beacon = getArguments().getParcelable("beacon");
        beaconNameTextView.setText(String.format(TEXT_VIEW_FORMAT, 
                                                    beacon.getUUID().toString(), 
                                                    beacon.getMajor(),
                                                    beacon.getMinor()));
        return mainScroll;
    }

}