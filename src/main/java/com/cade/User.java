package com.cade;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "user")
public class User {
    public ObjectId id;
    public String name;
    public String email;
}
