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

/**
 * Fragment that displays the information of the given beacon
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public class BeaconFragment extends Fragment {
    
    /**
     * Format for the Name TextView
     */
    private static final String NAME_FORMAT = "Name - %s";
    /**
     * Format for the MAC Address TextView
     */
    private static final String ADDRESS_FORMAT = "MAC Address - %s";
    /**
     * Format for the UUID TextView
     */
    private static final String UUID_FORMAT = "UUID - %s";
    /**
     * Format for the Major TextView
     */
    private static final String MAJOR_FORMAT = "Major - %d";
    /**
     * Format for the Minor TextView
     */
    private static final String MINOR_FORMAT = "Minor - %d";
    /**
     * Format for the Calibration RSSI TextView
     */
    private static final String CALIBRATION_RSSI_FORMAT = "Calibration RSSI - %d";
    /**
     * Bundle Key for the Beacon
     */
    private static final String BEACON_KEY = "beacon";

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(final LayoutInflater inflater, 
            final ViewGroup container, final Bundle savedInstanceState) {
        // Load all the views from the layout
        final ScrollView mainScroll = (ScrollView) inflater.inflate(R.layout.beacon_layout, null);
        final TextView beaconNameTextView = (TextView) mainScroll.findViewById(R.id.beacon_name);
        final TextView beaconAddressTextView = (TextView) mainScroll.findViewById(R.id.beacon_address);
        final TextView beaconUUIDTextView = (TextView) mainScroll.findViewById(R.id.beacon_uuid);
        final TextView beaconMajorTextView = (TextView) mainScroll.findViewById(R.id.beacon_major);
        final TextView beaconMinorTextView = (TextView) mainScroll.findViewById(R.id.beacon_minor);
        final TextView beaconCalibrationRSSITextView = (TextView) mainScroll.findViewById(R.id.beacon_calibration_rssi);
        
        // Get the beacon passed in through the bundle
        final Beacon beacon = getArguments().getParcelable(BEACON_KEY);

        beaconNameTextView.setText(String.format(NAME_FORMAT, beacon.getName()));
        beaconAddressTextView.setText(String.format(ADDRESS_FORMAT, beacon.getAddress()));
        beaconUUIDTextView.setText(String.format(UUID_FORMAT, beacon.getUUID()));
        beaconMajorTextView.setText(String.format(MAJOR_FORMAT, beacon.getMajor()));
        beaconMinorTextView.setText(String.format(MINOR_FORMAT, beacon.getMinor()));
        beaconCalibrationRSSITextView.setText(String.format(CALIBRATION_RSSI_FORMAT, beacon.getRSSI()));

        return mainScroll;
    }
}