package main;

import sun.security.provider.SHA;

import java.util.Scanner;

/**
 * Created by inbar on 6/12/2018.
 */

public class CMDBillEnricher implements BillEnricher {
    private Bill bill;

    public CMDBillEnricher(Bill bill) {
     this.bill = bill;
    }

    @Override
    public Bill enrich() {
        printBegining();
        getUserEnrichments();
        return bill;
    }
    public void printBegining(){
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("          Your debits in  " +bill.getName() );
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    }
    public void getUserEnrichments(){
        Scanner scanner = new Scanner(System.in);
        for (BillRecord billRecord:bill.getBillRecords()){
            if (billRecord.getDebitType().equals(SharedProperties.debitTypesMap.get(2))){
                System.out.println("Date: " + SharedProperties.outputDateFormat.format(billRecord.getDebitDate()) + "\nBusiness: " +
                        billRecord.getBusiness() + "\nDebit Sum: " + billRecord.getDebitSum());
            }
            else{
                System.out.println("Date: " + SharedProperties.outputDateFormat.format(billRecord.getDebitDate()) + "\nBusiness: " +
                        new StringBuilder(billRecord.getBusiness()).reverse().toString() + "\nDebit Sum: " + billRecord.getDebitSum());

            }

            // Gets Shared Debit
            System.out.println("Is this a shared debit?(y=Yes,Enter=No)");
            String isShareDebit = scanner.nextLine();
            while (isShareDebit.equals("y")==false && isShareDebit.isEmpty()==false){
                System.out.println("Wrong Input,Try Again-->Is this a shared debit?(y=Yes,Enter=No)");
                isShareDebit = scanner.nextLine();
            }
            if (isShareDebit.equals("y")) {
                billRecord.setIsShared(true);

                // Gets category
                System.out.println("Pick debit category:");
                for (int key=1;key<=SharedProperties.categoryMap.keySet().size();key++){
                    System.out.println(key + "=" + SharedProperties.categoryMap.get(key));
                }
                System.out.println("Category:");
                try{
                    int category = Integer.parseInt(scanner.nextLine());
                    if (SharedProperties.categoryMap.containsKey(category)){
                    billRecord.setCategory(SharedProperties.categoryMap.get(category));}
                    else {
                        billRecord.setCategory(SharedProperties.propertiesManager.getProperty("CategoryUnknown"));
                    }
                }
                catch (Exception ex) {
                    billRecord.setCategory(SharedProperties.propertiesManager.getProperty("CategoryUnknown"));
                }



                // Gets comment
            System.out.println("Do you have any comment?(y=yes,Enter=No)");
                if (scanner.nextLine().equals("y")){
                    System.out.println("Comment:");
                    billRecord.setComment(scanner.nextLine());
                }
            }
        }
    }


}
