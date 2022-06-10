package com.cade.core.domain.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Getter
@Setter
@MongoEntity(collection = "users")
public class UserEntity {

    @BsonId
    private ObjectId id;

    private String email;
}
