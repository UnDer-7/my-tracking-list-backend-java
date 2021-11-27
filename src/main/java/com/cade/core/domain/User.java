package com.cade.core.domain;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Getter
@AllArgsConstructor
@MongoEntity(collection = "users")
public class User {
    @BsonId
    @Setter
    private ObjectId id;

    private String email;

    private String name;

    private String givenName;

    private String familyName;
}
