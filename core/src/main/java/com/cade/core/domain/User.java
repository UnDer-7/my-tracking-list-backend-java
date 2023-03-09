package com.cade.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collation = "users")
public class User {

    @Id
    private String id;

    private String email;

    private String password;

}
