package com.thesilentnights.maalinkserver.jna;

public class MaaStatus {
    private static boolean status = false;

    public static boolean isStatus() {
        return status;
    }

    public static void setStatus(boolean status) {
        MaaStatus.status = status;
    }
}
