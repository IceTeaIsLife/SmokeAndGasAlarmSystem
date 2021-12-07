package ru.mirea.smokeandgasalarmsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppResponse<T> {
    private static final Integer OK_CODE = 0;
    private static final String OK_MSG = "OK";

    private static final Integer ERROR_CODE = 1;
    private static final String ERROR_MSG = "ERROR";

    private Meta meta;

    private T dataBlock;

    public static AppResponse<Void> ok() {
        return new AppResponse<>(new Meta(OK_CODE, OK_MSG), null);
    }

    public static <T> AppResponse<T> ok(T data) {
        return new AppResponse<>(new Meta(OK_CODE, OK_MSG), data);
    }

    public static AppResponse<Void> error() {
        return new AppResponse<>(new Meta(ERROR_CODE, ERROR_MSG), null);
    }

    public static <T> AppResponse<T> error(String message, T data) {
        return new AppResponse<>(new Meta(ERROR_CODE, message), data);
    }
}