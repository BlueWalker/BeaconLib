package com.acompagno.beacon;

import java.util.UUID;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents an iBeacon module and holds all it's information
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public class Beacon implements Comparable<Beacon>, Parcelable {

    /**
     * Holds the raw data for the given iBeacon Module
     */
    private String rawData;
    /**
     * Holds the UUID for the iBeacon Module
     */
    private UUID uuid;
    /**
     * Holds the Major value for the iBeacon Module
     */
    private int major;
    /**
     * Holds the minor value for the iBeacon Module
     */
    private int minor;
    /**
     * Holds the RSSI value @ 1 meter for the iBeacon Module
     */
    private int rssi;

    /**
     * Creates an instance of Beacon using the values stored in the
     * given parcel. 
     * 
     * @param parcel Parcel
     */
    private Beacon(final Parcel parcel) {
        this.rawData = parcel.readString();
        this.uuid = UUID.fromString(parcel.readString());
        this.major = parcel.readInt();
        this.minor = parcel.readInt();
        this.rssi = parcel.readInt();
    }

    /**
     * Creates an instance of Beacon using the given raw broadcast data
     * to parse out the information of the Beacon.
     * 
     * @param rawData String
     */
    public Beacon(final String rawData) {
        this.rawData = rawData;
        this.uuid = BeaconDataParser.getUUIDFromRawData(rawData);
        this.major = BeaconDataParser.getMajorFromRawData(rawData);
        this.minor = BeaconDataParser.getMinorFromRawData(rawData);
        this.rssi = BeaconDataParser.getRSSIFromRawData(rawData);
    }

    /**
     * Surfaces the UUID of the Beacon
     * 
     * @return UUID
     */
    public UUID getUUID() {
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
    public String getRawData() {
        return this.rawData;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        final Beacon otherBeacon = (Beacon) obj;
        return this.getUUID().equals(otherBeacon.getUUID())
                && this.getMajor() == otherBeacon.getMajor()
                && this.getMinor() == otherBeacon.getMinor();
    }

    @Override
    public int compareTo(Beacon another) {
        final int uuidCompare = this.uuid.compareTo(another.getUUID());
        if (uuidCompare != 0) {
            return uuidCompare;
        } else if (this.major != another.getMajor()) {
            return this.major > another.getMajor() ? 1 : -1; 
        } else if (this.minor != another.getMinor()) {
            return this.minor > another.getMinor() ? 1 : -1;
        } else {
            return 0;
        }
    }

    @Override
    public int describeContents() {
        // TODO forgot what I was supposed to put here
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rawData);
        dest.writeString(this.uuid.toString());
        dest.writeInt(this.major);
        dest.writeInt(this.minor);
        dest.writeInt(this.rssi);
    }

    /**
     * This is used by android to automatically recreate an object from a
     * parcel when its being sent/received
     */
    public static final Parcelable.Creator<Beacon> CREATOR= 
            new Parcelable.Creator<Beacon>() {
        public Beacon createFromParcel(Parcel in) {
            return new Beacon(in);
        }

        public Beacon[] newArray(int size) {
            return new Beacon[size];
        }
    };
}