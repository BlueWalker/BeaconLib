package walker.blue.beacon.lib.beacon;


import java.util.List;

/**
 * Base implementation of a Beacon containing all its fields and their getters.
 */
public abstract class BeaconBase implements BeaconInterface {

    /**
     * Holds the name of the given Beacon
     */
    protected String name;
    /**
     * Holds the mac address of the given Beacon
     */
    protected String address;
    /**
     * Holds the raw data for the given Beacon
     */
    protected byte[] rawData;
    /**
     * Holds the UUID for the Beacon
     */
    protected String uuid;
    /**
     * Holds the Major value for the Beacon
     */
    protected int major;
    /**
     * Holds the minor value for the Beacon
     */
    protected int minor;
    /**
     * Holds the RSSI value @ 1 meter for the Beacon
     */
    protected int rssi;
    /**
     * List of measured RSSI values for the Beacon
     */
    protected List<Integer> measuredRSSI;

    /**
     * Surfaces the name of the Beacon
     * 
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Surfaces the mac address of the Beacon
     * 
     * @return String
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Surfaces the UUID of the Beacon
     * 
     * @return String
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Surfaces the major value of the Beacon
     * 
     * @return int
     */
    public int getMajor() {
        return this.major;
    }

    /**
     * Surfaces the minor value of the Beacon
     * 
     * @return int
     */
    public int getMinor() {
        return this.minor;
    }

    /**
     * Surfaces the RSSI calibration value of the Beacon
     * 
     * @return int
     */
    public int getRSSI() {
        return this.rssi;
    }

    /**
     * Surfaces the raw broadcast data of the Beacon
     * 
     * @return int
     */
    public byte[] getRawData() {
        return this.rawData;
    }

    /**
     * Surfaces a list of the measured rssi values for the Beacon
     *
     * @return List of Integers
     */
    public List<Integer> getMeasuredRSSIValues() {
        return this.measuredRSSI;
    }
}