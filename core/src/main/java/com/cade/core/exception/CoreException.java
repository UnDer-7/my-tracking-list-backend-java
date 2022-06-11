package com.cade.core.exception;

import com.cade.core.domain.ErrorMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
public abstract class CoreException extends RuntimeException {

    private final String errCode;
    private final String userMsg;
    private final String customMsg;
    private final int statusCode;
    private final String timestamp;
    private final String errUniqueIdentifier;

    protected CoreException(final ErrorMessages errorMessages, final StatusCode statusCode) {
        super(errorMessages.getExceptionMessage());
        this.errCode = errorMessages.getErrCode();
        this.userMsg = errorMessages.getUserMsg();
        this.customMsg = errorMessages.getCustomMsg();
        this.statusCode = statusCode.code;
        this.timestamp = LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        this.errUniqueIdentifier = UUID.randomUUID().toString();
    }

    protected CoreException(
        final ErrorMessages errorMessages,
        final StatusCode statusCode,
        final Throwable throwable) {
        super(errorMessages.getExceptionMessage(), throwable);
        this.errCode = errorMessages.getErrCode();
        this.userMsg = errorMessages.getUserMsg();
        this.customMsg = errorMessages.getCustomMsg();
        this.statusCode = statusCode.code;
        this.timestamp = LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        this.errUniqueIdentifier = UUID.randomUUID().toString();
    }

    protected abstract LogLevel getLogLevel();

    protected abstract Logger getLogger();

    public void executeLogging() {
        final var className = this.getClass().getSimpleName();
        final var defaultMsg = """
            An CoreException has occurred | \
            [exception_class={}] \
            [err_code={}] \
            [user_msg={}] \
            [custom_msg={}] \
            [status_code={}] \
            [timestamp={}] \
            [err_unique_identifier={}]
            """;

        switch (getLogLevel()) {
            case TRACE -> getLogger().trace(
                defaultMsg,
                className,
                this.getErrCode(),
                this.getUserMsg(),
                this.getCustomMsg(),
                this.getStatusCode(),
                this.getTimestamp(),
                this.getErrUniqueIdentifier());
            case DEBUG -> getLogger().debug(
                defaultMsg,
                className,
                this.getErrCode(),
                this.getUserMsg(),
                this.getCustomMsg(),
                this.getStatusCode(),
                this.getTimestamp(),
                this.getErrUniqueIdentifier());
            case INFO -> getLogger().info(
                defaultMsg,
                className,
                this.getErrCode(),
                this.getUserMsg(),
                this.getCustomMsg(),
                this.getStatusCode(),
                this.getTimestamp(),
                this.getErrUniqueIdentifier());
            case WARN -> getLogger().warn(
                defaultMsg,
                className,
                this.getErrCode(),
                this.getUserMsg(),
                this.getCustomMsg(),
                this.getStatusCode(),
                this.getTimestamp(),
                this.getErrUniqueIdentifier());
            case ERROR -> getLogger().error(
                defaultMsg,
                className,
                this.getErrCode(),
                this.getUserMsg(),
                this.getCustomMsg(),
                this.getStatusCode(),
                this.getTimestamp(),
                this.getErrUniqueIdentifier());
            default -> getLogger().info(
                "An CoreException has occurred and it does not have a LogLeve defined | [exception_class={}] [err_code={}]",
                className,
                this.getErrCode());
        }
    }

    @Getter
    @RequiredArgsConstructor
    enum StatusCode {
        BAD_REQUEST(400),
        UNAUTHORIZED(401),
        FORBIDDEN(403),
        NOT_FOUND(404),
        UNPROCESSABLE_ENTITY(422),
        INTERNAL_SERVER_ERROR(500);

        private final int code;
    }

    enum LogLevel {
        TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

}
