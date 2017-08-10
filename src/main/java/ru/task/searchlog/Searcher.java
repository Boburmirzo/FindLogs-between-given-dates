package ru.task.searchlog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * <h1>Show logs with entries from date to date</h1>
 * The class searchs for logs between dates, and do input and output operation on a file
 *
 * @author  Boburmirzo Umurzokov
 * @version 1.0
 * @since   10.08.2017
 */

public class Searcher {

    /**
     * The log file which is observed
     */
    private File logFile;

    /**
     * This method initialize new object on File and check if file exists or not.
     * @param pathToLocalFile a String path to file location
     * @exception FileNotFoundException If file is not founded, throws an exception.
     * @see FileNotFoundException
     */

    public void openLog(String pathToLocalFile) throws FileNotFoundException {
        logFile = new File(pathToLocalFile);
        if(!logFile.exists())
            throw new FileNotFoundException();

    }

    /**
     * This method finds log entries between start date and end date.
     * @param from  in “Mon D HH:MM:SS” format, where day is started and a space padded integer
     * @param to a in “Mon D HH:MM:SS” format, where day is ended and a space padded integer
     * @exception Exception  check if the log file has been opened, otherwise it should
     * throw an exception. The method should check if end time is greater or equal
     * to start time, otherwise it should throw an exception
     * @see Exception
     */

    public List<LogEntry> searchLog(String from, String to) throws Exception {
        if(from != null && to != null) {
            Date dateFrom = extractDate(from);
            Date dateTo = extractDate(to);
            if(dateFrom != null && dateTo != null)
                if (dateFrom.before(dateTo) || dateFrom.equals(dateTo)) {
                    try {
                        BufferedReader reader=new BufferedReader(new FileReader(logFile));
                        List<LogEntry> logEntries = new ArrayList<>();
                        String line=null;
                        while((line=reader.readLine())!=null){
                            Date checkingDate = extractDate(line.substring(0,15));
                            if(checkingDate != null) {
                                if ((checkingDate.before(dateTo) || checkingDate.equals(dateTo)) && (checkingDate.after(dateFrom) || checkingDate.equals(dateFrom))){
                                    LogEntry logEntry = new LogEntry(line);
                                    logEntries.add(logEntry);
                                }
                                //Assuming that all logs in an ascending order in give file
                                if(checkingDate.after(dateTo))
                                    break;
                            }
                        }
                        return logEntries;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("An exception in reading file");
                    }
                }else
                    throw new Exception();
        }
        return null;
    }

    /**
     * This method initialize SimpleDateFormat and converts a string to date by give format.
     * @param date a String date from each line in log file
     * @exception ParseException If string is not suitable format, throws an parse exception.
     * @see ParseException
     */
    private Date extractDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss", Locale.ENGLISH);
        try {
            return sdf.parse("2017 "+date);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
