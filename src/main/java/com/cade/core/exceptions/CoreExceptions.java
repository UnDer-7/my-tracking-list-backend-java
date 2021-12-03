package com.cade.core.exceptions;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;

import javax.ws.rs.core.Response;
import java.util.function.Supplier;

@Getter
@JsonIncludeProperties({
    "errCode",
    "userMsg",
    "devMsg",
    "howToSolve",
    "statusCode"
})
public abstract class CoreExceptions extends RuntimeException {
    private final String userMsg;
    private final String devMsg;
    private final String howToSolve;
    private final String errCode;
    private int statusCode;


    protected CoreExceptions(final ErrorMessages message, final Response.Status statusCode) {
        this(message);
        this.statusCode = statusCode.getStatusCode();
    }

    protected CoreExceptions(final ErrorMessages message, final int statusCode) {
        this(message);
        this.statusCode = statusCode;
    }

    private CoreExceptions(final ErrorMessages message) {
        this.userMsg = message.getUserMsg();
        this.devMsg = message.getDevMsg();
        this.howToSolve = message.getHowToSolve();
        this.errCode = message.getErrCode();
    }
}
