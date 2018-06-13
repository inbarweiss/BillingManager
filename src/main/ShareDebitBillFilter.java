package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inbar on 6/12/2018.
 */
public class ShareDebitBillFilter implements BillFilter {
    Bill billToFilter;
    boolean isSharedDebit;
    public ShareDebitBillFilter(Bill BillToFilter, boolean isSharedDebit) {
        this.billToFilter = BillToFilter;
        this.isSharedDebit = isSharedDebit;
    }

    @Override
    public Bill filter() {
        List<BillRecord> filteredBillRecordList = new ArrayList<>();

        for (BillRecord billRecord:billToFilter.getBillRecords()){
            if(billRecord.isShared()==isSharedDebit){
                filteredBillRecordList.add(billRecord);
            }
        }
        Bill filteredBill = new Bill(billToFilter.getName(),filteredBillRecordList);
        return filteredBill;
    }
}
