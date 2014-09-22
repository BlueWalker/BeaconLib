AndroidBeacon (Andre's branch)
===

Current Done
---
- Basic framework for Beacon and ScanClient somewhat implemented
- Started implementing a builder for the client. This will keep growing as more options and functionality is implemented into the Client
- Placeholder class made to parse the iBeacon signal
- Added permission to android manifest

Current TODO list
---
- Actually implement the BeaconDataParser
- Check that the Beacon compareTo method was done correctly
- describeContents method for Beacon. Forgot what I'm supposed to return here.
- Write up the service that actually scans for iBeacons
- Figure out more functionality for the library
- Add more checks to compatibility manager? 
- Implement an example
