package walker.blue.beacon.lib.client;


import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import java.util.Arrays;
import java.util.HashSet;

import walker.blue.beacon.lib.service.ScanEndUserCallback;

/**
 * Builds a BeaconScanClient
 */
public class BeaconClientBuilder extends BeaconClientBase {

    /**
     * Default scan interval value in milliseconds (10 seconds)
     */
    private static final int DEFAULT_SCAN_INTERVAL = 10000;

    /**
     * Creates an instance of the BeaconClientBuilder and sets all the fields 
     * to their default values
     */
    public BeaconClientBuilder() {
        this.scanInterval = DEFAULT_SCAN_INTERVAL;
        this.validUUIDs = new HashSet<String>();
    }

    /**
     * Builds and returns a BeaconScanClient using the values set 
     * in the builder
     * 
     * @return BeaconScanClient
     */
    public BeaconScanClient build() {
        return new BeaconScanClient(this);
    }

    /**
     * Sets the scan interval in milliseconds for the client being built
     * 
     * @param scanInterval int
     * @return BeaconClientBuilder (current instance of the builder)
     */
    public BeaconClientBuilder scanInterval(final int scanInterval) {
        this.scanInterval = scanInterval;
        return this;
    }

    /**
     * Adds valid UUIDs to the whitelist of the client being built.
     * 
     * @param uuids UUID
     * @return BeaconClientBuilder (current instance of the builder)
     */
    public BeaconClientBuilder addUUID(final String...uuids) {
        this.validUUIDs.addAll(Arrays.asList(uuids));
        return this;
    }

    /**
     * Sets the LeScanCallback that will be used by the client
     * 
     * @param leScanCallback BluetoothAdapter.LeScanCallback
     * @return BeaconClientBuilder (current instance of the builder)
     */
    public BeaconClientBuilder setLeScanCallback(final BluetoothAdapter.LeScanCallback leScanCallback) {
        this.leScanCallback = leScanCallback;
        return this;
    }

    /**
     * Sets the context that will be used by the client 
     * 
     * @param context Context
     * @return BeaconClientBuilder (current instance of the builder)
     */
    public BeaconClientBuilder setContext(final Context context) {
        this.context = context;
        return this;
    }

    /**
     * Sets the callback executed once the client stops scanning
     *
     * @param userCallback ScanEndUserCallback
     * @return BeaconClientBuilder (current instance of the builder)
     */
    public BeaconClientBuilder setUserCallback(final ScanEndUserCallback userCallback) {
        this.userCallback = userCallback;
        return this;
    }
}