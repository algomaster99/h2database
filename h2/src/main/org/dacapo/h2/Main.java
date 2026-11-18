/*
 * Runs DaCapo's h2 benchmark (23.11-MR2 chopin) once, as DaCapo's harness (org.dacapo.harness.H2) does.
 */
package org.dacapo.h2;

import java.io.File;
import java.util.TreeMap;

public final class Main {

    private Main() {
    }

    /**
     * @param args the scratch directory for the database, then optionally the driver's arguments. Without them,
     *     the driver runs DaCapo's default size: 100000 transactions at scale 16.
     */
    public static void main(String... args) throws Exception {
        File scratch = new File(args[0]);
        String[] sizeArgs = args.length > 1
                ? java.util.Arrays.copyOfRange(args, 1, args.length)
                : new String[] {"--total-transactions", "100000", "--scale", "16", "--cleanup-in-iteration"};
        String size = "default";
        // DaCapo's thread model for h2 is one client thread per hardware thread.
        TreeMap<String, String> threads = new TreeMap<>();
        threads.put(size, Integer.toString(Runtime.getRuntime().availableProcessors()));
        TreeMap<String, String[]> sizes = new TreeMap<>();
        sizes.put(size, sizeArgs);
        TPCC tpcc = TPCC.make(threads, sizes, scratch, false, false);
        tpcc.prepare(size);
        tpcc.preIteration(size);
        tpcc.iteration(size);
        tpcc.postIteration(size);
    }
}
