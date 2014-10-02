package com.acompagno.service;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.acompagno.utils.CompatibilityManager;

/**
 * Scans for iBeacon modules.
 *
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
@TargetApi(18)
public class BLEScanner {

    /**
     * Log message
     */
    private static final String LOG_ALREADY_SCANNING = "Cant start scanning. Scanner is already scanning.";
    /**
     * Log message
     */
    private static final String LOG_NOT_SCANNING = "Cant stop scanning. Scanner is not currently scanning.";

    /**
     * Stores the BluetoothAdapter used in the scanner
     */
    private BluetoothAdapter bluetoothAdapter;
    /**
     * Stores the context used in the scanner
     */
    private Context context;
    /**
     * Stores the LeScanCallback used in the scanner. 
     * This defines what is done with each LE device once its found
     */
    private BluetoothAdapter.LeScanCallback leScanCallback;
    /**
     * Handler that times out the scanner
     */
    private Handler scanEndHandler;
    /**
     * Runnable that stops the scanner
     */
    private EndBLEScanRunnable endScanRunnable;
    /**
     * Boolean indicating whether the scanner is running
     */
    private boolean scanning;
    /**
     * Amount of time (in milliseconds) before the scan times out
     * TODO Make this changeable
     */
    private int scanTime;

    /**
     * Creates an instance of the BLEScanner using the given parameters
     * 
     * @param context Context
     * @param leScanCallback BluetoothAdapter.LeScanCallback
     */
    public BLEScanner(final Context context, final BluetoothAdapter.LeScanCallback leScanCallback) {
        this.context = context;
        this.leScanCallback = leScanCallback;
        this.scanEndHandler = new Handler();
        this.endScanRunnable = new EndBLEScanRunnable();
        this.bluetoothAdapter = getBluetoothAdapter();
        this.scanTime = 10000;
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
     * Sets the status of the scanner
     * 
     * @param enable boolean
     */
    public void setScanStatus(final boolean enable) {
        if (enable && !scanning) {
            startLeScan();
        } else if (!enable && scanning) { 
            stopLeScan();
        } else if (!enable && !scanning) {
            Log.w(this.getClass().getName(), LOG_NOT_SCANNING);
        } else if (enable && scanning) {
            Log.w(this.getClass().getName(), LOG_ALREADY_SCANNING);
        }
    }

    /**
     * Starts the scanner and sets up the timeout runnable
     */
    private void startLeScan() {
        scanEndHandler.postDelayed(endScanRunnable, scanTime);
        scanning = true;
        bluetoothAdapter.startLeScan(leScanCallback);
    }

    /**
     * Stops the scanner and removes the timeout runnable
     */
    private void stopLeScan() {
        scanEndHandler.removeCallbacks(endScanRunnable);
        scanning = false;
        getBluetoothAdapter().stopLeScan(leScanCallback);

    }

    /**
     * Gets the Bluetooth adapter. Returns null if the device isn't compatible
     * 
     * @return BluetoothAdapter
     */
    private BluetoothAdapter getBluetoothAdapter() {
        if (!CompatibilityManager.isDeviceCompatible()) {
            return null;
        } else if (this.bluetoothAdapter == null){
            final BluetoothManager bluetoothManager = 
                    (BluetoothManager) this.context.getSystemService(Context.BLUETOOTH_SERVICE);
            this.bluetoothAdapter = bluetoothManager.getAdapter();
        }
        return this.bluetoothAdapter;
    }

    /**
     * Runnable in charge of stopping the scanner when it reaches it's time out
     */
    private class EndBLEScanRunnable implements Runnable {
        @Override
        public void run() {
            stopLeScan();
        }
    }
}