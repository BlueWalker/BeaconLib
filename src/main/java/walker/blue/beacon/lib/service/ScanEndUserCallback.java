package walker.blue.beacon.lib.service;

/**
 * Callback used to determine what the client does once  the scan is stopped.
 */
public interface ScanEndUserCallback {

    /**
     * Gets executed after the client stops scanning.
     */
    public void execute();

}