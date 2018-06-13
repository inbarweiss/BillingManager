package main;

import java.util.List;

/**
 * Created by inbar on 6/11/2018.
 */
public class Bill {
    private String name;
    private List<BillRecord> billRecords;

    public Bill(String Name,List<BillRecord> BillRecords)
    {
        this.name = Name;
        this.billRecords = BillRecords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BillRecord> getBillRecords() {
        return billRecords;
    }

    public void setBillRecords(List<BillRecord> billRecords) {
        this.billRecords = billRecords;
    }
}
