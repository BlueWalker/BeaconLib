package com.acompagno.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Class in charge of checking device compatibility with Bluetooth LE
 * TODO Add separate check for different things?
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public class CompatibilityManager {

    private static final String INVALID_VERSION_MESSAGE = 
            "Android version (%d) on device does not support Bluetooth LE";
    private static final String UNSUPPORTED_DEVICE_MESSAGE = 
            "Device does not support Bluetooth LE";
    
    /**
     * Holds the applications context
     */
    private Context applicationContext;

    /**
     * Creates an instance of the compatibility manager and sets
     * the application context field
     * 
     * @param applicationContext Context
     */
    public CompatibilityManager(final Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Checks if version of android the device is running and the device
     * hardware supports Bluetooth LE. If the device doesnt support BLE, 
     * the reasons will be printed to the log. TODO Check more things? 
     *  
     * @return boolean
     */
    public boolean isDeviceCompatible() {
        // Check if device is running at least android version 4.3
        final int deviceVersion =  android.os.Build.VERSION.SDK_INT;
        //Check if device hardware supports BLE
        final boolean compatibleHardware = this.applicationContext.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
        if (deviceVersion < 18) {
            Log.w("isDeviceCompatible", String.format(INVALID_VERSION_MESSAGE, deviceVersion));
        }
        if (!compatibleHardware) {
            Log.w("isDeviceCompatible", UNSUPPORTED_DEVICE_MESSAGE);
        }
        return deviceVersion >= 18 && compatibleHardware; 
    }


}