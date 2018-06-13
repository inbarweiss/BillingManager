package main;

import java.util.Date;

/**
 * Created by inbar on 6/11/2018.
 */
public class BillRecord {
    private Date debitDate;
    private String business;
    private Double debitSum;
    private boolean isShared;
    private String category;
    private String comment;
    private String DebitType;

    public BillRecord(Date debitDate, String business, Double debitSum) {
        this.debitDate = debitDate;
        this.business = business;
        this.debitSum = debitSum;
        this.isShared = false;
        this.category = SharedProperties.propertiesManager.getProperty("CategoryUnknown");
        this.comment = "";
        this.DebitType="";
    }

    public Date getDebitDate() {
        return debitDate;
    }

    public void setDebitDate(Date debitDate) {
        this.debitDate = debitDate;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Double getDebitSum() {
        return debitSum;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setIsShared(boolean isShared) {
        this.isShared = isShared;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String catagory) {
        this.category = catagory;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDebitType() {
        return DebitType;
    }

    public void setDebitType(String debitType) {
        DebitType = debitType;
    }
}
