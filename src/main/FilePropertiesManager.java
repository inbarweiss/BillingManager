package main;

import java.io.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Created by inbar on 6/11/2018.
 */
public final class FilePropertiesManager implements PropertiesManager {
    static final Properties props = new Properties();

    public FilePropertiesManager(){
        FileInputStream input = null;
        try {
            input = new FileInputStream(new File("C:\\Users\\inbar\\IdeaProjects\\BillingManager\\src\\main\\Constatnts.properties"));
            props.load(new InputStreamReader(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override

    public String getProperty(String key) {
        return props.getProperty(key);
    }

}
