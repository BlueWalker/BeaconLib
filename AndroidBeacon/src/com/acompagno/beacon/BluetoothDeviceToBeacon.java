package com.acompagno.beacon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

/**
 * Class responsible for converting the objects found by scanning for
 * BLE devices into iBeacons 
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public class BluetoothDeviceToBeacon {

    /**
     * Regex used to determine whether the data given belongs to a beacon
     * "02011a1aff4c00021" is the Bluetooth signature that should be the same
     *      for all ibeacons
     * "[a-f0-9]{43}" refers to the unique data for each beacon. This checks
     *      that there are at least 43 more valid hex values in the signal
     * ".*" TODO make this better? according to the stack overflow question
     *      below there should be some sort of checksum after the data, but
     *      most beacons i check just have 0's not sure....
     * 
     *http://stackoverflow.com/questions/18906988/what-is-the-ibeacon-bluetooth-profile
     *  
     */
    private static final String BEACON_DATA_REGEX = "^02011a1aff4c00021[a-f0-9]{43}.*";
    /**
     * Pattern used to match serch for the regex in the signal
     */
    private static final Pattern BEACON_DATA_PATTERN = Pattern.compile(BEACON_DATA_REGEX);

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
            Log.w("Not an iBeacon!", String.format("Failed iBeacon - %s", btDevice.getName()));
            return null;
        }
        final String beaconUUID = beaconSignal.substring(18,50);
        final int beaconMajor = Integer.parseInt(beaconSignal.substring(50, 54), 16);
        final int beaconMinor = Integer.parseInt(beaconSignal.substring(54, 58), 16);
        final int calibrationRSSI = Short.valueOf(beaconSignal.substring(58, 60),16).byteValue();
        return new BeaconBuilder()
                    .setBeaconName(btDevice.getName())
                    .setBeaconRawData(scanRecord)
                    .setBeaconUUID(beaconUUID)
                    .setBeaconMajor(beaconMajor)
                    .setBeaconMinor(beaconMinor)
                    .setBeaconRSSI(calibrationRSSI)
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
}