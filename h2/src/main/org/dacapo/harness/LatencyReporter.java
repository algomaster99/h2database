/*
 * Stand-in for the DaCapo harness class of the same name, which records request latencies. The TPC-C driver in
 * org.dacapo.h2 calls these methods, and here they do nothing.
 */
package org.dacapo.harness;

public final class LatencyReporter {

    private LatencyReporter() {
    }

    public static void initialize(int transactions, int threads, int stride) {
    }

    public static int start(int threadID) {
        return 0;
    }

    public static void end(int threadID) {
    }

    public static void requestsStarting() {
    }

    public static void requestsFinished() {
    }
}
