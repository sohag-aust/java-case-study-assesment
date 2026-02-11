package com.solvians.showcase;


public class CertificateUpdate {

    // TODO: implement me.
    private long timeStamp;
    private String ISIN;
    private float bidPrice;
    private int bidSize;
    private float askPrize;
    private float askSize;

    public CertificateUpdate() {
    }

    public CertificateUpdate(long timeStamp, String ISIN, float bidPrice, int bidSize, float askPrize, float askSize) {
        this.timeStamp = timeStamp;
        this.ISIN = ISIN;
        this.bidPrice = bidPrice;
        this.bidSize = bidSize;
        this.askPrize = askPrize;
        this.askSize = askSize;
    }

    @Override
    public String toString() {
        return "CertificateUpdate{" +
                "timeStamp=" + timeStamp +
                ", ISIN='" + ISIN + '\'' +
                ", bidPrice=" + bidPrice +
                ", bidSize=" + bidSize +
                ", askPrize=" + askPrize +
                ", askSize=" + askSize +
                '}';
    }
}
