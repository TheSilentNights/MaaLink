package com.thesilentnights.maalinkserver.dao;


public class MaaStatus {
    private static boolean status = false;
    private static boolean connect = false;

    public static boolean isStatus() {
        return status;
    }

    public static void setStatus(boolean status) {
        MaaStatus.status = status;
    }

    public static void setConnect(boolean connect){
        MaaStatus.connect = connect;
    }

    public static boolean isConnected(){
        return MaaStatus.connect;
    }
}
