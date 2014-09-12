package com.acompagno.client;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Builds a BeaconScanClient
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public class BeaconClientBuilder {

    /**
     * Default scan interval value in milliseconds
     */
    private static final int DEFAULT_SCAN_INTERVAL = 1000;

    /**
     * Amount of time in milliseconds the client will wait while the service 
     * is scanning for Beacons
     */
    private int scanInterval;
    /**
     * Set of UUIDs that the client will use as a whitelist when scanning
     * for Beacons. Only Beacons whose UUID is in this set will be surfaced
     * by the client. If it is left empty, all Beacons will be surfaced by 
     * the client 
     */
    private Set<UUID> validUUIDs;

    /**
     * Creates an instance of the BeaconClientBuilder and sets all the fields 
     * to their default values
     */
    public BeaconClientBuilder() {
        this.scanInterval = DEFAULT_SCAN_INTERVAL;
        this.validUUIDs = new HashSet<UUID>();
    }

    /**
     * Builds and returns a BeaconScanClient using the values set 
     * in the builder
     * 
     * @return BeaconScanClient
     */
    public BeaconScanClient build() {
        return new BeaconScanClient(this.scanInterval, this.validUUIDs);
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
    public BeaconClientBuilder addUUID(final UUID...uuids) {
        this.validUUIDs.addAll(Arrays.asList(uuids));
        return this;
    }
}