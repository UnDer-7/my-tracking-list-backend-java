package com.cade.core.domain.entity;

import com.cade.core.domain.TokenDomain;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Getter
@MongoEntity(collection = "users")
public class UserEntity {

    @BsonId
    private ObjectId id;

    private String email;
    private String name;
    private String locale;

    public static UserEntity fromToken(final TokenDomain token) {
        final var usr = new UserEntity();
        usr.email = token.getEmail();
        usr.name = token.getName();
        usr.locale = token.getLocale();

        return usr;
    }

}
