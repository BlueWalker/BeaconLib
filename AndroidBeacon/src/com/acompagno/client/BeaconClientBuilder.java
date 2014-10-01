package com.acompagno.client;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

/**
 * Builds a BeaconScanClient
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public class BeaconClientBuilder extends BeaconClientBase {

    /**
     * Default scan interval value in milliseconds
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

    public BeaconClientBuilder setLeScanCallback(final BluetoothAdapter.LeScanCallback leScanCallback) {
        this.leScanCallback = leScanCallback;
        return this;
    }

    public BeaconClientBuilder setContext(final Context context) {
        this.context = context;
        return this;
    }
}