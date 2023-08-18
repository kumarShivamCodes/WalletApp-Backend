package com.nexxtuple.walletapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Random;

@Data
@NoArgsConstructor
@Document(collection="user")
public class User {
    @Id
    private String id;

    @Indexed(unique=true)
    private String username;

    @Indexed(unique = true)
    private String email;

    private String password;

    private Integer balance=0;

    private Integer accNo;

    public User (String username,String email,String password)
    {
        this.username=username;
        this.email=email;
        this.password=password;
        this.accNo=generateUniqueAccNo();
    }

    public int generateUniqueAccNo()
    {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
