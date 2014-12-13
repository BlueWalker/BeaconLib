package walker.blue.beacon.lib.beacon;

import java.util.List;

/**
 * Interface for a beacon. Defines the getters for all the fields
 */
public interface BeaconInterface {

    /**
     * Surfaces the name of the Beacon
     * 
     * @return String
     */
    public String getName();

    /**
     * Surfaces the mac address of the Beacon
     * 
     * @return String
     */
    public String getAddress();

    /**
     * Surfaces the UUID of the Beacon
     * 
     * @return String
     */
    public String getUUID();

    /**
     * Surfaces the major value of the Beacon
     * 
     * @return int
     */
    public int getMajor();

    /**
     * Surfaces the minor value of the Beacon
     * 
     * @return int
     */
    public int getMinor();

    /**
     * Surfaces the RSSI calibration value of the Beacon
     * 
     * @return int
     */
    public int getRSSI();

    /**
     * Surfaces the raw broadcast data of the Beacon
     * 
     * @return int
     */
    public byte[] getRawData();

    /**
     * Surfaces a list of the measured rssi values for the Beacon
     *
     * @return List<Integer>
     */
    public List<Integer> getMeasuredRSSIValues();
}