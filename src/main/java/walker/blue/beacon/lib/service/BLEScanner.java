package walker.blue.beacon.lib.service;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import walker.blue.beacon.lib.utils.CompatibilityManager;

/**
 * Scans for iBeacon modules.
 */
@TargetApi(18)
public class BLEScanner {

    /**
     * Log messages
     */
    private static final String LOG_ALREADY_SCANNING = "Cant start scanning. Scanner is already scanning.";
    private static final String LOG_NOT_SCANNING = "Cant stop scanning. Scanner is not currently scanning.";
    private static final String LOG_SCAN_STARTED = "BLEScanner scan started for %d milliseconds.";
    private static final String LOG_SCAN_STOPPED = "BLEScanner scan stopped.";

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
     * User defined actions once scan in done
     */
    private ScanEndUserCallback userCallback;

    /**
     * Creates an instance of the BLEScanner using the given parameters
     * 
     * @param context Context
     * @param leScanCallback BluetoothAdapter.LeScanCallback
     */
    public BLEScanner(final Context context, final BluetoothAdapter.LeScanCallback leScanCallback) {
        this.context = context;
        this.leScanCallback = leScanCallback;
        this.scanEndHandler = new Handler(Looper.getMainLooper());
        this.endScanRunnable = new EndBLEScanRunnable();
        this.bluetoothAdapter = getBluetoothAdapter();
    }

    /**
     * Sets the user callback for the Scanner
     * @param userCallback ScanEndUserCallback
     */
    public void setUserEndRunable(final ScanEndUserCallback userCallback) {
        this.userCallback = userCallback;
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
     * Sets the status of the scanner. Defaults scan time to -1
     *
     * @param enable boolean
     */
    public void setScanStatus(final boolean enable) {
        setScanStatus(enable, -1);
    }

    /**
     * Sets the status of the scanner
     * 
     * @param enable boolean
     * @param scanTime int (in milliseconds)
     */
    public void setScanStatus(final boolean enable, final int scanTime) {
        if (enable && !scanning) {
            startLeScan(scanTime);
        } else if (!enable && scanning) { 
            stopLeScan();
        } else if (!enable && !scanning) {
            Log.w(this.getClass().getName(), LOG_NOT_SCANNING);
        } else if (enable && scanning) {
            Log.w(this.getClass().getName(), LOG_ALREADY_SCANNING);
        }
    }

    /**
     * Indicated whether the device has bluetooth enabled.
     *
     * @return boolean
     */
    public boolean isBluetoothEnabled() {
        return this.bluetoothAdapter.isEnabled();
    }

    /**
     * Starts the scanner and sets up the timeout runnable
     *
     * @param scanTime int (in milliseconds)
     */
    private void startLeScan(final int scanTime) {
        scanEndHandler.postDelayed(endScanRunnable, scanTime);
        scanning = true;
        bluetoothAdapter.startLeScan(leScanCallback);
        Log.d(this.getClass().getName(), String.format(LOG_SCAN_STARTED, scanTime));
    }

    /**
     * Stops the scanner and removes the timeout runnable
     */
    private void stopLeScan() {
        scanEndHandler.removeCallbacks(endScanRunnable);
        scanning = false;
        getBluetoothAdapter().stopLeScan(leScanCallback);
        Log.d(this.getClass().getName(), LOG_SCAN_STOPPED);
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
            if (userCallback != null) {
                userCallback.execute();
            }
        }
    }
}