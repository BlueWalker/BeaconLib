package walker.blue.beacon.lib.client;


import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import java.util.Set;

import walker.blue.beacon.lib.service.BLEScanner;
import walker.blue.beacon.lib.service.ScanEndUserCallback;
import walker.blue.beacon.lib.utils.CompatibilityManager;

/**
 * Client in charge of communicating with the Scanner and the user. Starts 
 * and stops the service as necessary and surfaces the results to the user.
 */
public class BeaconScanClient extends BeaconClientBase {

    /**
     * Instance of the scanner being used by the client
     */
    private BLEScanner bleScanner;

    /**
     * Creates an instance of the BeaconScanClient using the values 
     * from the BeaconClientBuilder
     * 
     * @param builder BeaconClientBuilder
     */
    public BeaconScanClient(final BeaconClientBuilder builder) {
        this(builder.getScanInterval(),
                builder.getValidUUIDs(),
                builder.getLeScanCallback(),
                builder.getContext(),
                builder.getUserCallback());
    }

    /**
     * Creates an instance of the BeaconScanClient using the given parameters.
     * It also ensure that the compatibility manager is initialized
     *
     * @param scanInterval int (in milliseconds)
     * @param validUUIDs Set of Strings
     * @param leScanCallback BluetoothAdapter.LeScanCallback
     * @param context   Context
     * @param userCallback  ScanEndUserCallback
     */
    public BeaconScanClient(final int scanInterval,
            final Set<String> validUUIDs,
            final BluetoothAdapter.LeScanCallback leScanCallback,
            final Context context,
            final ScanEndUserCallback userCallback) {
        if (!CompatibilityManager.isInitalized()) {
            CompatibilityManager.init(context);
        }
        this.scanInterval = scanInterval;
        this.validUUIDs = validUUIDs;
        this.bleScanner = new BLEScanner(context, leScanCallback);
        this.bleScanner.setUserEndRunable(userCallback);
    }

    /**
     * Starts the BLEScanner
     */
    public void startScanning() {
        bleScanner.setScanStatus(true, this.scanInterval);
    }

    /**
     * Stops the BLEScanner
     */
    public void stopScanning() {
        bleScanner.setScanStatus(false);
    }

    /**
     * Surfaces whether the scanner is currently scanning
     * 
     * @return boolean
     */
    public boolean isScanning() {
        return bleScanner.isScanning();
    }

    /**
     * Set the scan interval for the next scan
     *
     * @param scanInterval int (in milliseconds))
     */
    public void setScanningInterval(final int scanInterval) {
        this.scanInterval = scanInterval;
    }
}