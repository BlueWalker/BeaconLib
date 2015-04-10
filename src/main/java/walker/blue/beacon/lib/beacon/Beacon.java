package walker.blue.beacon.lib.beacon;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an iBeacon module and holds all it's information
 */
public class Beacon extends BeaconBase implements Comparable<Beacon>, Parcelable {

    /**
     * Average value of all the rssi values
     */
    private double average;

    /**
     * Creates an instance of Beacon using the values stored in the
     * given parcel. 
     *
     * @param parcel Parcel
     */
    private Beacon(final Parcel parcel) {
        this.name = parcel.readString();
        this.address = parcel.readString();
        parcel.readByteArray(this.rawData);
        this.uuid = parcel.readString();
        this.major = parcel.readInt();
        this.minor = parcel.readInt();
        this.rssi = parcel.readInt();
        this.measuredRSSI = new ArrayList<>();
        parcel.readList(this.measuredRSSI, null);
    }

    /**
     * Creates an instance of Beacon using the values from the 
     * given in BeaconBuilder
     *
     * @param builder BeaconBuilder
     */
    public Beacon(final BeaconBuilder builder) {
        this(builder.getName(),
                builder.getAddress(),
                builder.getRawData(),
                builder.getUUID(),
                builder.getMajor(),
                builder.getMinor(),
                builder.getRSSI(),
                builder.getMeasuredRSSIValues());
    }

    /**
     * Creates an instance of beacon using the given values 
     *
     * @param name String
     * @param address String
     * @param rawData byte[]
     * @param uuid String
     * @param major int
     * @param minor int
     * @param rssi int
     * @param measuredRSSI List of Integers
     */
    public Beacon(final String name,
                  final String address,
                  final byte[] rawData,
                  final String uuid,
                  final int major,
                  final int minor,
                  final int rssi,
                  final List<Integer> measuredRSSI) {
        this.name = name;
        this.address = address;
        this.rawData = rawData;
        this.uuid = uuid;
        this.major = major;
        this.minor = minor;
        this.rssi = rssi;
        this.average = 0;
        this.measuredRSSI = measuredRSSI;
    }

    /**
     * Calculated the average value of all the measured rssi values
     *
     * @return average of all the measured rssi values
     */
    public double getAverageRSSIValue() {
        if (this.average == 0) {
            int total = 0;
            for (final int rssi : this.measuredRSSI) {
                total += rssi;
            }
            this.average = total / this.measuredRSSI.size();
        }
        return this.average;
    }

    @Override
    public int compareTo(final Beacon another) {
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

    /*
     * Generated by Eclipse
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + major;
        result = prime * result + minor;
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    /*
     * Generated by Eclipse
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Beacon)) {
            return false;
        }
        Beacon other = (Beacon) obj;
        if (major != other.major) {
            return false;
        }
        if (minor != other.minor) {
            return false;
        }
        if (uuid == null) {
            if (other.uuid != null) {
                return false;
            }
        } else if (!uuid.equals(other.uuid)) {
            return false;
        }
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeByteArray(this.rawData);
        dest.writeString(this.uuid);
        dest.writeInt(this.major);
        dest.writeInt(this.minor);
        dest.writeInt(this.rssi);
        dest.writeList(this.measuredRSSI);
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

    /**
     * Adds a new measured rssi value to the measured values
     *
     * @param rssi int
     */
    public void addMeasuredRSSI(final int rssi) {
        this.measuredRSSI.add(rssi);
    }
}