package com.acompagno.beacon.example.fragments;

import java.util.ArrayList;
import java.util.List;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.acompagno.beacon.Beacon;
import com.acompagno.beacon.BluetoothDeviceToBeacon;
import com.acompagno.beacon.example.BeaconListAdapter;
import com.acompagno.beacon.example.MainActivity;
import com.acompagno.client.BeaconClientBuilder;
import com.acompagno.client.BeaconScanClient;

/**
 * Fragment that displays a list of Beacons found
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public class BeaconListFragment extends ListFragment {

    /**
     * Log message format
     */
    private static final String BEACON_FOUND_LOG = "Name - %s\n\tRaw data (hex) - %s";

    /**
     * Holds the Beacons found
     */
    private List<Beacon> beacons;
    /**
     * The client that scans for Beacons
     */
    private BeaconScanClient scanClient;

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Lazy initialize all these things since this method gets called
        // every time the fragment is displayed.
        if (this.beacons == null) {
            this.beacons = new ArrayList<Beacon>();
        }
        if (getListAdapter() == null) {
            final BeaconListAdapter adapter = new BeaconListAdapter(getActivity() , this.beacons);
            setListAdapter(adapter);
        }
        if (this.scanClient == null) {
            this.scanClient = new BeaconClientBuilder()
                    .setContext(getActivity())
                    .setLeScanCallback(leScanCallback)
                    .build();
            this.scanClient.startScanning();
        }
    }

    @Override
    public void onListItemClick(final ListView l, final View v, final int position, final long id) {
        final BeaconFragment beaconFragment = new BeaconFragment();
        final Bundle beaconBundle = new Bundle();
        beaconBundle.putParcelable("beacon", (Beacon)getListAdapter().getItem(position));
        beaconFragment.setArguments(beaconBundle);
        ((MainActivity) getActivity()).changeFragment(beaconFragment);

    }

    /**
     * Callback that defines what will happen once a Bluetooth device is found
     */
    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            final BeaconListAdapter listAdapter = (BeaconListAdapter) getListAdapter();
            final Beacon beacon = BluetoothDeviceToBeacon.toBeacon(device, rssi, scanRecord);
            if (beacon == null) {
                return;
            }
            getActivity().runOnUiThread(new LeCallbackRunnable(beacon, listAdapter));
        }
    };

    /**
     * Runnable that is responsible for adding the beacons to the adapter 
     */
    private class LeCallbackRunnable implements Runnable {

        private Beacon beacon;
        private ArrayAdapter<Beacon> listAdapter;

        public LeCallbackRunnable(final Beacon beacon, final ArrayAdapter<Beacon> listAdapter) {
            this.beacon = beacon;
            this.listAdapter = listAdapter;
        }

        @Override
        public void run() {
            final int beaconPosition = listAdapter.getPosition(beacon);
            if (beaconPosition < 0) {
                Log.d(this.getClass().getName(),
                        String.format(BEACON_FOUND_LOG, beacon.getName(), 
                                        BluetoothDeviceToBeacon.rawSignalToString(beacon.getRawData())));
                listAdapter.add(beacon);
            }
        }
    }
}