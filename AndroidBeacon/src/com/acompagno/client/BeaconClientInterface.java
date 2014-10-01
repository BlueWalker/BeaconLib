package com.acompagno.client;

import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

/**
 * TODO
 * 
 * @author Andre Compagno (Last Edited: Andre Compagno)
 */
public interface BeaconClientInterface {

    public Context getContext();
    public Set<String> getValidUUIDs();
    public BluetoothAdapter.LeScanCallback getLeScanCallback();
    public int getScanInterval();
}