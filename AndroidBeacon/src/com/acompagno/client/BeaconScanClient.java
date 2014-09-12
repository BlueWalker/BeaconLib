package com.acompagno.client;

import java.util.Set;
import java.util.UUID;

/**
 * Client in charge of communicating with the Service and the user. Starts 
 * and stops the service as necessary and surfaces the results to the user.
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public class BeaconScanClient {

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
     * Creates an instance of the BeaconScanClient using the given parameters.
     * 
     * @param scanInterval int (in milliseconds)
     * @param validUUIDs Set<UUID>
     */
    public BeaconScanClient(final int scanInterval, final Set<UUID> validUUIDs) {
        this.scanInterval = scanInterval;
        this.validUUIDs = validUUIDs;
    }

    /**
     * Surfaces the current scan interval (in milliseconds) for the client
     * 
     * @return int
     */
    public int getScanInterval() {
        return this.scanInterval;
    }

    /**
     * Set a new scan interval for the client
     * 
     * @param scanInterval int (in milliseconds)
     */
    public void setScanInterval(final int scanInterval) {
        this.scanInterval = scanInterval;
    }

    /**
     * Surfaces the valid UUIDs for the client
     * 
     * @return Set<UUID>
     */
    public Set<UUID> getValicUUIDs() {
        return this.validUUIDs;
    }
}