package com.cade.core.domain;

import com.cade.backend.adapter.mapper.Default;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Getter
@Setter
@ToString
@AllArgsConstructor(onConstructor_={@Default})
@NoArgsConstructor
@MongoEntity(collection = "users")
public class User {

    @BsonId
    private ObjectId id;

    private String email;
    private String name;
    private String givenName;
    private String familyName;
    private String pictureUrl;

    public User(
        final String email,
        final String name,
        final String givenName,
        final String familyName,
        final String pictureUrl) {

        this(null, email, name, givenName, familyName, pictureUrl);
    }
}
