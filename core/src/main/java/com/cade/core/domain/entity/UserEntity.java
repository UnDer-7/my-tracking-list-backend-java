package com.cade.core.domain.entity;

import com.cade.core.domain.TokenDomain;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "users")
public class UserEntity {

    @BsonId
    private ObjectId id;

    // todo: criar index para esse campo
    private String email;
    private String name;
    private String locale;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public void beforeSave() {
        final var currentTime = LocalDateTime.now();

        createdAt = currentTime;
        modifiedAt = currentTime;
    }

    public void beforeUpdate() {
        modifiedAt = LocalDateTime.now();
    }

    public static UserEntity fromToken(final TokenDomain token) {
        final var usr = new UserEntity();
        usr.email = token.getEmail();
        usr.name = token.getName();
        usr.locale = token.getLocale();

        return usr;
    }

}
