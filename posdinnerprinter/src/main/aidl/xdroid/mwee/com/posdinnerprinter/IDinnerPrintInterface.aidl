// IDinnerPrintInterface.aidl
package xdroid.mwee.com.posdinnerprinter;

// Declare any non-default types here with import statements

interface IDinnerPrintInterface {
    /**
         * Demonstrates some basic types that you can use as parameters
         * and return values in AIDL.
         */
        void cleanOrder();

        void processTask(String task);

        void printSyncTask(String task);

        void init();

        void refreshSetting();

        void killingProcess();

        void optTask(String nolist,String hostID);

        void updateTask( String fsHostId,  int printNo,String hashMap);

}
