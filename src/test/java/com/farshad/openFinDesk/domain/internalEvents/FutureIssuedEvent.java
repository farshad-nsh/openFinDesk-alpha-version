package com.farshad.openFinDesk.domain.internalEvents;


public class FutureIssuedEvent implements InternalEvent{

    private String instrumentId;

    private String amount;


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }
}
