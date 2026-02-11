package com.solvians.showcase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class CertificateUpdateGenerator {
    private final int threads;
    private final int quotes;

    public CertificateUpdateGenerator(int threads, int quotes) {
        this.threads = threads;
        this.quotes = quotes;
    }

    public Stream<CertificateUpdate> generateQuotes() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // TODO: Implement me.
        List<CertificateUpdate> updateList = new ArrayList<>();

        ISINGenerator isinGenerator = new ISINGenerator();
        List<String> isinList = isinGenerator.generateISINList();

        for (int i = 0; i < threads * quotes; i++) {
            if(i < isinList.size()) {
                updateList.add(getCertificate(isinList.get(i), random));
            }
            else {
                updateList.add(new CertificateUpdate());
            }
        }

        return updateList.stream().parallel();
    }

    public CertificateUpdate getCertificate(String isin, ThreadLocalRandom random) {
        long timeStamp = System.currentTimeMillis();
        float bidPrice = generateRandomValue(100f, 200f, random.nextFloat());
        int bidSize = generateRandomValue(1000, 5000, random.nextFloat());
        float askPrice = generateRandomValue(100f, 200f, random.nextFloat());
        int askSize = generateRandomValue(1000, 10000, random.nextFloat());

//        System.out.println(bidPrice + " " + bidSize + " " + askPrice + " " + askSize);

        return new CertificateUpdate(timeStamp, isin, bidPrice, bidSize, askPrice, askSize);
    }

    public float generateRandomValue(float lowerBound, float upperBound, float jitter) {
        float newValue = lowerBound*jitter;

        if(newValue < lowerBound) {
            return upperBound - newValue;
        }
        return newValue;
    }

    public int generateRandomValue(int lowerBound, int upperBound, float jitter) {
        float newValue = lowerBound*jitter;

        if(newValue < lowerBound) {
            newValue = upperBound - newValue;
            return (int) newValue;
        }
        return (int) newValue;
    }
}
