package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by inbar on 6/11/2018.
 */
public final class SharedProperties {
    public static final PropertiesManager propertiesManager = new FilePropertiesManager();
    public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static Map<Integer,String> categoryMap = getAllCategories();
    public static Map<Integer,String> debitTypesMap = getAllDebitTypes();
    public static final SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static Map<Integer,String> getAllCategories(){
        Map<Integer,String> allCategories = new Hashtable<>();
        allCategories.put(5,propertiesManager.getProperty("Category5"));
        allCategories.put(4,propertiesManager.getProperty("Category4"));
        allCategories.put(3,propertiesManager.getProperty("Category3"));
        allCategories.put(2, propertiesManager.getProperty("Category2"));
        allCategories.put(1, propertiesManager.getProperty("Category1"));
        return  allCategories;
    }

    public static Map<Integer,String> getAllDebitTypes(){
        Map<Integer,String> allDebitTypes = new Hashtable<>();
        allDebitTypes.put(3,propertiesManager.getProperty("DebitType3"));
        allDebitTypes.put(2, propertiesManager.getProperty("DebitType2"));
        allDebitTypes.put(1, propertiesManager.getProperty("DebitType1"));
        return  allDebitTypes;
    }

}
