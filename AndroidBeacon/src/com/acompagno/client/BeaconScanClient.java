package com.acompagno.client;

import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import com.acompagno.service.BLEScanner;
import com.acompagno.utils.CompatibilityManager;

/**
 * Client in charge of communicating with the Service and the user. Starts 
 * and stops the service as necessary and surfaces the results to the user.
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public class BeaconScanClient extends BeaconClientBase {

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
                builder.getContext());
    }

    /**
     * Creates an instance of the BeaconScanClient using the given parameters.
     * 
     * @param scanInterval int (in milliseconds)
     * @param validUUIDs Set<UUID>
     */
    public BeaconScanClient(final int scanInterval,
            final Set<String> validUUIDs,
            final BluetoothAdapter.LeScanCallback leScanCallback,
            final Context context) {
        if (!CompatibilityManager.isInitalized()) {
            CompatibilityManager.init(context);
        }
        this.scanInterval = scanInterval;
        this.validUUIDs = validUUIDs;
        this.bleScanner = new BLEScanner(context, leScanCallback);
    }

    /**
     * Starts the BLEScanner
     */
    public void startScanning() {
        bleScanner.setScanStatus(true);
    }

    /**
     * Stops the BLEScanner
     */
    public void stopScanning() {
        bleScanner.setScanStatus(false);
    }
}