package ru.task.searchlog;

import java.io.FileNotFoundException;

/**
 * <h1>Class for keeping data for each log entry</h1>
 * @author  Boburmirzo Umurzokov
 * @version 1.0
 * @since   10.08.2017
 */

public class LogEntry {

    /**
     * The log info
     */
    private String logData;

    /**
     * Constructor for LogEntry class receives parameter string and save it its field.
     * @param logData is to save
     */

    public LogEntry(String logData) {

        this.logData = logData;
    }

    public String getLogData() {
        return logData;
    }

    public void setLogData(String logData) {
        this.logData = logData;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                ", logData=" + logData +
                '}';
    }
}
