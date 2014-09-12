package com.acompagno.beacon;

import java.util.UUID;

/**
 * Parses the raw data broadcasted by an iBeacon and breaks it down into its 
 * separate components. TODO this all needs to be implemented
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public enum BeaconDataParser {
    INSTANCE;

    /**
     * Takes the raw data from an iBeacon as input and extracts the Beacons 
     * UUID from it
     *  
     * @param rawData String
     * @return UUID
     */
    public static UUID getUUIDFromRawData(final String rawData) {
        return null;
    }

    /**
     * Takes the raw data from an iBeacon as input and extracts the Beacons 
     * Major value from it
     *  
     * @param rawData String
     * @return int
     */
    public static int getMajorFromRawData(final String rawData) {
        return -1;
    }

    /**
     * Takes the raw data from an iBeacon as input and extracts the Beacons 
     * Minor value from it
     *  
     * @param rawData String
     * @return int
     */
    public static int getMinorFromRawData(final String rawData) {
        return -1;
    }

    /**
     * Takes the raw data from an iBeacon as input and extracts the Beacons 
     * calibration RSSI value from it
     *  
     * @param rawData String
     * @return int
     */
    public static int getRSSIFromRawData(final String rawData) {
        return -1;
    }
}