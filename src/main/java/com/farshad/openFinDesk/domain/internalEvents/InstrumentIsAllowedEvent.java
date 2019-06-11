package com.farshad.openFinDesk.domain.internalEvents;

public class InstrumentIsAllowedEvent implements InternalEvent{

     private    String instrumentId;

     private  boolean isAllowed;

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }
}
