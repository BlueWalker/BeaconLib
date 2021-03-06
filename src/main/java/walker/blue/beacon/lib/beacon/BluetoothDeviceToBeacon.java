package walker.blue.beacon.lib.beacon;


import android.bluetooth.BluetoothDevice;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class responsible for converting the objects found by scanning for
 * BLE devices into iBeacons 
 */
public class BluetoothDeviceToBeacon {

    /**
     * Regex used to determine whether the data given belongs to a beacon
     * "0201" Shouldn't change between beacons
     * "[a-f0-9]{2}' Manufacturer Identifier
     * "1aff4c00021" Shouldn't change between beacons
     * "[a-f0-9]{43}" refers to the unique data for each beacon. This checks
     *      that there are at least 43 more valid hex values in the signal
     * ".*" TODO make this better? according to the stack overflow question
     *      below there should be some sort of checksum after the data, but
     *      most beacons i check just have 0's not sure....
     * 
     *http://stackoverflow.com/questions/18906988/what-is-the-ibeacon-bluetooth-profile
     */
    private static final String BEACON_DATA_REGEX = "^0201[a-f0-9]{2}1aff[a-f0-9]{4}0215[a-f0-9]{42}.*";
    /**
     * Pattern used to match serch for the regex in the signal
     */
    private static final Pattern BEACON_DATA_PATTERN = Pattern.compile(BEACON_DATA_REGEX);
    /**
     * Log message format for when a Beacon is found
     */
    private static final String LOG_BEACON_FOUND_FORMAT = "Given device converted to Beacon - %s";
    /**
     * Log message format for when a ble device which is not a Beacon is found 
     */
    private static final String LOG_NOT_BEACON_FORMAT = "Given device is not a Beacon - %s";

    /**
     * Private constructor so this class can't be initialized
     */
    private BluetoothDeviceToBeacon() {}

    /**
     * Converts the given BluetoothDevice, RSSI value, and Scan record into a Beacon
     * 
     * @param btDevice BluetoothDevice
     * @param rssi int
     * @param scanRecord byte[]
     * @return Beacon
     */
    public static Beacon toBeacon(final BluetoothDevice btDevice, final int rssi, final byte[] scanRecord) {
        final String beaconSignal = rawSignalToString(scanRecord);
        if (!isBeacon(beaconSignal)) {
            Log.d(getClassName(), String.format(LOG_NOT_BEACON_FORMAT, btDevice.getName()));
            return null;
        }
        final String beaconUUID = beaconSignal.substring(18,50);
        final int beaconMajor = Integer.parseInt(beaconSignal.substring(50, 54), 16);
        final int beaconMinor = Integer.parseInt(beaconSignal.substring(54, 58), 16);
        final int calibrationRSSI = Short.valueOf(beaconSignal.substring(58, 60),16).byteValue();
        Log.d(getClassName(), String.format(LOG_BEACON_FOUND_FORMAT, btDevice.getName()));
        return new BeaconBuilder()
                    .setBeaconName(btDevice.getName())
                    .setBeaconAddress(btDevice.getAddress())
                    .setBeaconRawData(scanRecord)
                    .setBeaconUUID(beaconUUID)
                    .setBeaconMajor(beaconMajor)
                    .setBeaconMinor(beaconMinor)
                    .setBeaconRSSI(calibrationRSSI)
                    .setMeasuredRSSI(rssi)
                    .build();
    }

    /**
     * Converts the given raw data of type byte[] into a string of hex values
     * 
     * @param rawData byte[]
     * @return String
     */
    public static String rawSignalToString(final byte[] rawData) {
        final StringBuilder hexStringBuilder = new StringBuilder();
        for (final byte b : rawData) {
            hexStringBuilder.append(String.format("%02x", b));
        }
        return hexStringBuilder.toString();
    }

    /**
     * Checks whether the given data belongs to an iBeacon
     * 
     * @param beaconData String
     * @return boolean
     */
    public static boolean isBeacon(final String beaconData) {
        final Matcher dataMatcher = BEACON_DATA_PATTERN.matcher(beaconData);
        return dataMatcher.find();
    }

    /**
     * Gets the class name since we can't use this.getClass()
     * from static methods
     * 
     * @return String
     */
    private static String getClassName() {
        return BluetoothDeviceToBeacon.class.getName();
    }
}