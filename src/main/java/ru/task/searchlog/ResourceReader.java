package ru.task.searchlog;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <h1>Class for reading file path from properties file</h1>
 * The class reads properties file and assign property for file path
 * @author  Boburmirzo Umurzokov
 * @version 1.0
 * @since   10.08.2017
 */

public class ResourceReader {
    /**
     * The properties from resources
     */
    private final Properties properties;
    /**
     * The file path in a properties file
     */
    private final String domainPath;

    /**
     * This method read properties file in resource directory .
     * @exception IOException If a string is not suitable format, throws an parse exception.
     * @see IOException
     */
    public ResourceReader() {
        try (InputStream inputStream =
                     ResourceReader.class.getClassLoader().getResourceAsStream("default.properties")) {
            this.properties = new Properties();
            this.properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        domainPath = properties.getProperty("domainPath");
    }
    /**
     * The get method for domain path
     * @return domain path for file
     */
    public String domainPath() {
        return domainPath;
    }

}
