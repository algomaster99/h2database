/*
 * Copyright 2004-2025 H2 Group. Multiple-Licensed under the MPL 2.0,
 * and the EPL 1.0 (https://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */
package org.h2.test.unit;

import java.nio.file.Files;
import java.nio.file.Path;
import org.dacapo.h2.Main;
import org.h2.test.TestBase;

/**
 * Runs DaCapo's TPC-C driver (org.dacapo.h2) on a small database.
 */
public class TestDaCapoTPCC extends TestBase {

    /**
     * Run just this test.
     *
     * @param a ignored
     */
    public static void main(String... a) throws Exception {
        TestBase.createCaller().init().testFromMain();
    }

    @Override
    public void test() throws Exception {
        Path scratch = Files.createTempDirectory("dacapo-tpcc");
        try {
            Main.main(scratch.toString(), "--total-transactions", "1000", "--scale", "1", "--cleanup-in-iteration");
            // The full-size workload also loads Display, because C2 resolves it when it compiles
            // Standard.paymentById. This short run does not get that far.
            Class.forName("org.apache.derbyTesting.system.oe.client.Display");
        } finally {
            org.h2.store.fs.FileUtils.deleteRecursive(scratch.toString(), false);
        }
    }
}
