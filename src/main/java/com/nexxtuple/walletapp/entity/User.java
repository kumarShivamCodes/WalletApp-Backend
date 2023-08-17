package com.nexxtuple.walletapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
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
}
