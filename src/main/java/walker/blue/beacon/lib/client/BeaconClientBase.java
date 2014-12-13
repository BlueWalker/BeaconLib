package walker.blue.beacon.lib.client;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import java.util.Set;

import walker.blue.beacon.lib.service.ScanEndUserCallback;

/**
 * Base implementation of the client that includes all of the
 * clients fields and their getters
 */
public class BeaconClientBase implements BeaconClientInterface {

    /**
     * Amount of time in milliseconds the client will wait while the service 
     * is scanning for Beacons
     */
    protected int scanInterval;
    /**
     * Set of UUIDs that the client will use as a whitelist when scanning
     * for Beacons. Only Beacons whose UUID is in this set will be surfaced
     * by the client. If it is left empty, all Beacons will be surfaced by 
     * the client 
     */
    protected Set<String> validUUIDs;
    /**
     * Stores the LeScanCallback that will be used in the BeaconScanClient
     */
    protected BluetoothAdapter.LeScanCallback leScanCallback;
    /**
     * Stores the context that will be used in the BeaconScanClient
     */
    protected Context context;
    /**
     * Callback which get executed once the client stops scanning
     */
    protected ScanEndUserCallback userCallback;

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public Set<String> getValidUUIDs() {
        return this.validUUIDs;
    }

    @Override
    public BluetoothAdapter.LeScanCallback getLeScanCallback() {
        return this.leScanCallback;
    }

    @Override
    public int getScanInterval() {
        return this.scanInterval;
    }

    @Override
    public ScanEndUserCallback getUserCallback() {
        return this.userCallback;
    }
}