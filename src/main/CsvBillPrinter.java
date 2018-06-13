package main;

import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

/**
 * Created by inbar on 6/12/2018.
 */
public class CsvBillPrinter implements BillPrinter {
    private Bill billToPrint;
    private String outputFileDir;
    public CsvBillPrinter(Bill bill, String outputFileDir) {
        this.billToPrint = bill;
        this.outputFileDir = outputFileDir;
    }

    @Override
    public void print() {
        try (

                Writer writer = new OutputStreamWriter(new FileOutputStream(new File(this.outputFileDir)));
                //Writer writer = Files.newBufferedWriter(Paths.get(this.outputFileDir), Charset.forName("UTF8"));

                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            String[] headerRecord = {"Date", "Business", "Sum", "Category", "Comment"};
            csvWriter.writeNext(headerRecord);
            for (BillRecord billRecord : billToPrint.getBillRecords()) {
                csvWriter.writeNext(new String[]{SharedProperties.outputDateFormat.format(billRecord.getDebitDate()),
                        billRecord.getBusiness(),
                        billRecord.getDebitSum().toString(),
                        billRecord.getCategory(),
                        billRecord.getComment()});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
