package main;

import com.opencsv.CSVReader;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by inbar on 6/10/2018.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BillFileParser billFileParser = new OpenCsvBillFileParser(args[0]);
        Bill bill = billFileParser.Parse();
        BillEnricher enricher = new CMDBillEnricher(bill);
        Bill enrichedBill = enricher.enrich();
        BillFilter billFilter = new ShareDebitBillFilter(enrichedBill,true);
        Bill filteredBill = billFilter.filter();
        BillPrinter billPrinter = new CsvBillPrinter(filteredBill,args[1]);
        billPrinter.print();
    }
}
