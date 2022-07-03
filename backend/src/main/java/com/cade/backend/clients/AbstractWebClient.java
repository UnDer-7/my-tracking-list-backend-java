package com.cade.backend.clients;

import com.cade.core.domain.ErrorMessages;
import com.cade.core.exception.InternalServerErrorException;
import com.cade.core.utils.Assert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.ext.web.client.HttpResponse;
import io.vertx.mutiny.ext.web.client.WebClient;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import java.util.function.Function;

@Slf4j
@NoArgsConstructor
public abstract class AbstractWebClient {

    protected ObjectMapper objectMapper;
    protected WebClient webClient;

    protected AbstractWebClient(final Vertx vertx, final ObjectMapper objectMapper) {
        this.webClient = WebClient.create(vertx);
        this.objectMapper = objectMapper;
    }

    protected <T> Function<HttpResponse<Buffer>, T> handleResponse(final Class<T> clazz) {
        return response -> {
            final var json = response.bodyAsString();
            Assert.thatStatusCodeIsSuccess(response.statusCode(), new InternalServerErrorException(ErrorMessages.ERROR_WHILE_COMMUNICATING_WITH_CLIENT
                .setCustomMsg("StatusCode: %s | Response: %s".formatted(response.statusCode(), json))));

            return deserialize(json, clazz);
        };
    }

    protected <T> Function<HttpResponse<Buffer>, T> handleResponse(TypeReference<T> typeReference) {
        return response -> {
            final var json = response.bodyAsString();
            Assert.thatStatusCodeIsSuccess(response.statusCode(), new InternalServerErrorException(ErrorMessages.ERROR_WHILE_COMMUNICATING_WITH_CLIENT
                .setCustomMsg("StatusCode: %s | Response: %s".formatted(response.statusCode(), json))));

            return deserialize(json, typeReference);
        };
    }

    protected <T> T deserialize(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new InternalServerErrorException(
                ErrorMessages.SERIALIZATION_ERROR.setCustomMsg("Class with problem: %s - JSON: %s".formatted(typeReference.getClass(), json)),
                e
            );
        }
    }

    protected <T> T deserialize(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new InternalServerErrorException(
                ErrorMessages.SERIALIZATION_ERROR.setCustomMsg("Class with problem: %s - JSON: %s".formatted(clazz.getName(), json)),
                e
            );
        }
    }

}
