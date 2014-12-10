package walker.blue.beacon.lib.client;


import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import java.util.Set;

/**
 * Basic interface for the client that defines the getters for the fields
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
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
     * @return Set<String>
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
}