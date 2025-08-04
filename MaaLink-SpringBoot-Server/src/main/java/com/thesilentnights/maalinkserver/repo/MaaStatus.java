package com.thesilentnights.maalinkserver.repo;

import org.springframework.stereotype.Repository;

@Repository
public class MaaStatus {
    private boolean connected = false;
    private boolean loaded = false;

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
