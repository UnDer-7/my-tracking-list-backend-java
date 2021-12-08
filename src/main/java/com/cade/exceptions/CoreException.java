package com.cade.exceptions;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;

import javax.ws.rs.core.Response;

@Getter
@JsonIncludeProperties({
    "errCode",
    "userMsg",
    "devMsg",
    "howToSolve",
    "statusCode"
})
public abstract class CoreException extends RuntimeException {
    private final String userMsg;
    private final String devMsg;
    private final String howToSolve;
    private final String errCode;
    private final Throwable cause;
    private final ErrorMessages errorMessage;
    private int statusCode;

    public abstract void logErr();

    protected CoreException(
        final ErrorMessages message,
        final Response.Status statusCode,
        final Throwable cause) {

        this(message, cause);
        this.statusCode = statusCode.getStatusCode();
    }

    protected CoreException(final ErrorMessages message, final Response.Status statusCode) {
        this(message, (Throwable) null);
        this.statusCode = statusCode.getStatusCode();
    }


    protected CoreException(final ErrorMessages message, final int statusCode, final Throwable cause) {
        this(message, cause);
        this.statusCode = statusCode;
    }

    protected CoreException(final ErrorMessages message, final int statusCode) {
        this(message, (Throwable) null);
        this.statusCode = statusCode;
    }

    private CoreException(final ErrorMessages message, final Throwable cause) {
        super(cause);
        this.errorMessage = message;
        this.userMsg = message.getUserMsg();
        this.devMsg = message.getDevMsg();
        this.howToSolve = message.getHowToSolve();
        this.errCode = message.getErrCode();
        this.cause = cause;
    }
}
