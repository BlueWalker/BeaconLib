package com.acompagno.service;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;

import com.acompagno.utils.CompatibilityManager;

/**
 * Service in charge of scanning for iBeacon modules.
 *
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
@TargetApi(18)
public class BLEScanner {

    private BluetoothAdapter bluetoothAdapter;
    private Context context;
    private BluetoothAdapter.LeScanCallback leScanCallback;
    private Handler scanEndHandler;
    private boolean scanning;

    public BLEScanner(final Context context, final BluetoothAdapter.LeScanCallback leScanCallback) {
        this.context = context;
        this.leScanCallback = leScanCallback;
        this.scanEndHandler = new Handler();
        if (CompatibilityManager.isDeviceCompatible()) {
            final BluetoothManager btManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
            this.bluetoothAdapter = btManager.getAdapter();
        }
    }

    /**
     * Indicates whether the the scanner is currently scanning for beacons
     * 
     * @return boolean
     */
    public boolean isScanning() {
        return scanning;
    }

    /**
     * Starts scanning for bluetooth devices
     * 
     * @param enable
     */
    public void setScanStatus(final boolean enable) {
        if (enable) {
            scanEndHandler.postDelayed(new EndBLEScanRunnable(), 10000);
            scanning = true;
            bluetoothAdapter.startLeScan(leScanCallback);
        } else {
            stopLeScan();
        }
    }

    private void stopLeScan() {
        scanning = false;
        bluetoothAdapter.stopLeScan(leScanCallback);
    }

    private BluetoothAdapter getBluetoothAdapter() {
        if (android.os.Build.VERSION.SDK_INT < 18) {
            return null;
        } else if (this.bluetoothAdapter == null){
            final BluetoothManager bluetoothManager = 
                    (BluetoothManager) this.context.getSystemService(Context.BLUETOOTH_SERVICE);
            this.bluetoothAdapter = bluetoothManager.getAdapter();
        }
        return this.bluetoothAdapter;
    }

    private class EndBLEScanRunnable implements Runnable {
        @Override
        public void run() {
            stopLeScan();
        }
    }

}