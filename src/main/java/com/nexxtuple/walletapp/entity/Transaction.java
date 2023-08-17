package com.nexxtuple.walletapp.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private  String transactionId;

    private String username;

    private String type;

    private Integer transactionAmount;

    private String accNo;

    private String createdAt;

    //Constructor
    public Transaction(String username, String type,Integer transactionAmount,String accNo)
    {
        this.username=username;
        this.type=type;
        this.transactionAmount=transactionAmount;
        this.createdAt=getCurrentFormattedDate();
        this.accNo=accNo;
    }

    //method to format date
    private String getCurrentFormattedDate(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm dd-MM-yyyy");
        Date currentDate=new Date();
        return dateFormat.format(currentDate);
    }
}
