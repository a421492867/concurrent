package com.lordy.chapter5.concurrent_utils.cache;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        Thread.sleep(1000 * 5);
        return new BigInteger(arg);
    }
}
