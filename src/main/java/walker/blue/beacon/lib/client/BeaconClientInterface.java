package walker.blue.beacon.lib.client;


import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import java.util.Set;

import walker.blue.beacon.lib.service.ScanEndUserCallback;

/**
 * Basic interface for the client that defines the getters for the fields
 */
public interface BeaconClientInterface {

    /**
     * Surfaces the Context being used by the client
     * 
     * @return Context
     */
    public Context getContext();

    /**
     * Surfaces the set of Valid UUIDs for the client
     * 
     * @return Set of Strings
     */
    public Set<String> getValidUUIDs();

    /**
     * Surfaces the LeScanCallback being used by the client
     * 
     * @return BluetoothAdapter.LeScanCallback
     */
    public BluetoothAdapter.LeScanCallback getLeScanCallback();

    /**
     * Surfaces the scan interval of the client
     * 
     * @return int
     */
    public int getScanInterval();

    /**
     * Surfaces the user defined Callback executed once the client stops
     * scanning
     *
     * @return ScanEndUserCallback
     */
    public ScanEndUserCallback getUserCallback();
}