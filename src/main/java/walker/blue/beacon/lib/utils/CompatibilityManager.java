package walker.blue.beacon.lib.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Class in charge of checking device compatibility with Bluetooth LE
 * TODO Add separate check for different things?
 */
public class CompatibilityManager {

    /**
     * Log message for devices running an unsupported version of android
     */
    private static final String INVALID_VERSION_MESSAGE =
            "Android version (%d) on device does not support Bluetooth LE";
    /**
     * Log message for when the device doesnt support BLE
     */
    private static final String UNSUPPORTED_DEVICE_MESSAGE =
            "Device does not support Bluetooth LE";
    /**
     * Log message for when the CompatibilityManager isn't initialized
     */
    private static final String INITALIZATION_ERROR =
            "CompatibilityManager has not been initalized";
    /**
     * Holds the application context
     */
    private static Context applicationContext;

    /**
     * Private constructor so this class can't be initialized
     */
    private CompatibilityManager() {}

    /**
     * Initializes the CompatibilityManager 
     *
     * @param context Context
     */
    public static void init(final Context context) {
        applicationContext = context;
    }

    /**
     * Indicates whether the CompatibilityManager has been initialized
     *
     * @return boolean
     */
    public static boolean isInitalized() {
        return applicationContext != null;
    }

    /**
     * Checks if version of android the device is running and the device
     * hardware supports Bluetooth LE. If the device doesn't support BLE, 
     * the reasons will be printed to the log. If the class has not yet
     * been initialized, false will be returned TODO Check more things? 
     *
     * @return boolean
     */
    public static boolean isDeviceCompatible() {
        if (!isInitalized()) {
            Log.w(getClassName(), INITALIZATION_ERROR);
            return false;
        }
        // Check if device is running at least android version 4.3
        final int deviceVersion =  android.os.Build.VERSION.SDK_INT;
        //Check if device hardware supports BLE
        final boolean compatibleHardware = applicationContext.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
        if (deviceVersion < 18) {
            Log.w(getClassName(), String.format(INVALID_VERSION_MESSAGE, deviceVersion));
        }
        if (!compatibleHardware) {
            Log.w(getClassName(), UNSUPPORTED_DEVICE_MESSAGE);
        }
        return deviceVersion >= 18 && compatibleHardware;
    }

    /**
     * Gets the class name since we can't use this.getClass()
     * from static methods
     *
     * @return String
     */
    public static String getClassName() {
        return CompatibilityManager.class.getName();
    }
}