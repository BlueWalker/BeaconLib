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

public class BeaconListFragment extends ListFragment {

    private static final String BEACON_FOUND_LOG_TAG = "Found new Beacon!";
    private static final String BEACON_FOUND_LOG = "Name - %s\n\tRaw data (hex) - %s";

    private List<Beacon> beacons;
    private BeaconScanClient scanClient;

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.beacons = new ArrayList<Beacon>();
        final BeaconListAdapter adapter = new BeaconListAdapter(getActivity() , this.beacons);
        setListAdapter(adapter);
        buildBeaconClient();
        this.scanClient.startScanning();
    }

    @Override
    public void onListItemClick(final ListView l, final View v, final int position, final long id) {
        final BeaconFragment beaconFragment = new BeaconFragment();
        final Bundle beaconBundle = new Bundle();
        beaconBundle.putParcelable("beacon", (Beacon)getListAdapter().getItem(position));
        beaconFragment.setArguments(beaconBundle);
        ((MainActivity) getActivity()).changeFragment(beaconFragment);

    }

    private void buildBeaconClient() {
        if (scanClient == null) {
            scanClient = new BeaconClientBuilder()
                .setContext(getActivity())
                .setLeScanCallback(leScanCallback)
                .build();
        }
    }

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
                Log.w(BEACON_FOUND_LOG_TAG, String.format(BEACON_FOUND_LOG, beacon.getName(),
                        BluetoothDeviceToBeacon.rawSignalToString(beacon.getRawData())));
                listAdapter.add(beacon);
            }
        }
    }
}