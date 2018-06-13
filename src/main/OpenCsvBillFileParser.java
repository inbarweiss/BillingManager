package main;
import com.opencsv.CSVReader;
import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Properties;

/**
 * Created by inbar on 6/11/2018.
 */
public class OpenCsvBillFileParser implements BillFileParser {
    private int indexOfOnTimeBillingRecords;
    private int indexOfDelayedBillingRecords;
    private int indexOfForeignCurrencyBillingRecords;
    private int indexOfEOF;
    private String fileName;
    private  List<String[]> fileRecords;
    OpenCsvBillFileParser(String FileName) throws IOException {
        this.fileRecords = readFileRecords(FileName);
        this.fileName = FileName;
        findIndexes(fileRecords);
    }
    @Override
    public Bill Parse() {
        try {
            return new Bill(fileName,getBillRecords(fileRecords));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String[]> readFileRecords(String FileName) throws IOException {
        File fileDir = new File(FileName);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileDir)));

        CSVReader csvReader = new CSVReader(in);
        List<String[]> records = csvReader.readAll();
        csvReader.close();
        return records;
    }
    public List<BillRecord> getBillRecords(List<String[]> allRecords) throws ParseException {
        List<BillRecord> allBillRecords = new ArrayList<>();
        allBillRecords.addAll(getOnTimeBillingRecords(allRecords));
        allBillRecords.addAll(getDelayedBillingRecords(allRecords));
        allBillRecords.addAll(getForeignCurrencyBillingRecords(allRecords));
        return allBillRecords;
    }
    public List<BillRecord> getOnTimeBillingRecords(List<String[]> allRecords) throws ParseException {
        List<String[]> ontTimeRawRecords = allRecords.subList(indexOfOnTimeBillingRecords+1,indexOfDelayedBillingRecords-3);
        List<BillRecord> onTimeBillingRecords = new ArrayList<>();
        for (String[] record:ontTimeRawRecords){
            BillRecord billRecord = new BillRecord(SharedProperties.dateFormat.parse(record[0].trim()),record[1].trim(),Double.parseDouble(record[3].trim().replaceAll(",","")));
            billRecord.setDebitType(SharedProperties.debitTypesMap.get(3));
            onTimeBillingRecords.add(billRecord);
        }
        return onTimeBillingRecords;
    }
    public List<BillRecord> getDelayedBillingRecords(List<String[]> allRecords) throws ParseException {
        List<String[]> delayedRawRecords = allRecords.subList(indexOfDelayedBillingRecords+2,indexOfForeignCurrencyBillingRecords-3);
        List<BillRecord> delayedBillingRecords = new ArrayList<>();
        for (String[] record:delayedRawRecords){
            BillRecord billRecord = new BillRecord(SharedProperties.dateFormat.parse(record[0].trim()),record[2].trim(),Double.parseDouble(record[4].trim().replaceAll(",", "")));
            billRecord.setDebitType(SharedProperties.debitTypesMap.get(1));
            delayedBillingRecords.add(billRecord);
        }
        return delayedBillingRecords;
    }
    public List<BillRecord> getForeignCurrencyBillingRecords(List<String[]> allRecords) throws ParseException {
        List<String[]> foreignCurrencyRawRecords = allRecords.subList(indexOfForeignCurrencyBillingRecords+2,indexOfEOF-3);
        List<BillRecord> foreignCurrencyBillingRecords = new ArrayList<>();
        for (String[] record:foreignCurrencyRawRecords){
            BillRecord billRecord = new BillRecord(SharedProperties.dateFormat.parse(record[0].trim()),record[2].trim(),Double.parseDouble(record[5].trim().replaceAll(",", "")));
            billRecord.setDebitType(SharedProperties.debitTypesMap.get(2));
            foreignCurrencyBillingRecords.add(billRecord);
        }
        return foreignCurrencyBillingRecords;
    }
    public void findIndexes(List<String[]> allRecords){
        int currRecord=0;
        for (String[] record : allRecords){
            if (record[0].trim().startsWith(SharedProperties.propertiesManager.getProperty("OnTimeBillingRecord"))){
                indexOfOnTimeBillingRecords=currRecord+1;
            }
            if (record[0].trim().equals(SharedProperties.propertiesManager.getProperty("DelayedBillingRecord"))){
                indexOfDelayedBillingRecords=currRecord+1;
            }
            if (record[0].trim().startsWith(SharedProperties.propertiesManager.getProperty("ForeignCurrencyBillingRecord"))){
                indexOfForeignCurrencyBillingRecords=currRecord+1;
            }
            if (record[0].trim().startsWith(SharedProperties.propertiesManager.getProperty("EOF"))){
                indexOfEOF=currRecord+1;
            }
            currRecord++;
        }

    }


}
