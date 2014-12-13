package walker.blue.beacon.lib.beacon;

import java.util.ArrayList;

/**
 * Class used to build Beacon objects
 */
public class BeaconBuilder extends BeaconBase {

    /**
     * Initialized the list of measured rssi values
     */
    public BeaconBuilder() {
        this.measuredRSSI = new ArrayList<Integer>();
    }

    /**
     * Sets the name of the Beacon being built
     * 
     * @param name String
     * @return BeaconBuilder
     */
    public BeaconBuilder setBeaconName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the mac address of the Beacon being built
     * 
     * @param address String 
     * @return BeaconBuilder
     */
    public BeaconBuilder setBeaconAddress(final String address) {
        this.address = address;
        return this;
    }
    
    /**
     * Sets the raw data of the Beacon being built
     * 
     * @param rawData byte[]
     * @return BeaconBuilder
     */
    public BeaconBuilder setBeaconRawData(final byte[] rawData) {
        this.rawData = rawData;
        return this;
    }

    /**
     * Sets the UUID of the Beacon being built
     * 
     * @param uuid String
     * @return BeaconBuilder
     */
    public BeaconBuilder setBeaconUUID(final String uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * Sets the major value of the Beacon being built
     * 
     * @param major int 
     * @return BeaconBuilder
     */
    public BeaconBuilder setBeaconMajor(final int major) {
        this.major = major;
        return this;
    }

    /**
     * Sets the minor value of the Beacon being built
     * 
     * @param minor int
     * @return BeaconBuilder
     */
    public BeaconBuilder setBeaconMinor(final int minor) {
        this.minor = minor;
        return this;
    }

    /**
     * Sets the calibration RSSI value of the Beacon being built
     * 
     * @param rssi int
     * @return BeaconBuilder
     */
    public BeaconBuilder setBeaconRSSI(final int rssi) {
        this.rssi = rssi;
        return this;
    }

    /**
     * Sets the first measured RSSI value for the Beacon
     *
     * @param rssi int
     * @return BeaconBuilder
     */
    public BeaconBuilder setMeasuredRSSI(final int rssi) {
        this.measuredRSSI.add(rssi);
        return this;
    }

    /**
     * Builds the new Beacon using the set values
     * 
     * @return Beacon
     */
    public Beacon build() {
        return new Beacon(this);
    }

}