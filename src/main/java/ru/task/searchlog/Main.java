package ru.task.searchlog;

import java.time.Duration;
import java.time.Instant;

/**
 * <h1>Main class for demonstrating search operation on file</h1>
 * @author  Boburmirzo Umurzokov
 * @version 1.0
 * @since   10.08.2017
 */
public class Main {
    private static final long MEGABYTE = 1024L * 1024L;
    public static void main(String[] args) throws Exception {
        ResourceReader resourceReader = new ResourceReader();
        Searcher searcher = new Searcher();
        searcher.openLog(resourceReader.domainPath());

        Instant start = Instant.now();
        searcher.searchLog("Apr 12 11:01:50","Apr 12 11:02:05").forEach(System.out::println);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));

        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "
                + bytesToMegabytes(memory));
    }
    private static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
}
