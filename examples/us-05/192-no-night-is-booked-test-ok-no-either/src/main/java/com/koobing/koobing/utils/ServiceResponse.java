package com.koobing.koobing.utils;

/**
 * A Java-idiomatic response wrapper for service layer operations.
 * Replaces the functional Either pattern with a more traditional Java approach.
 *
 * @param <T> the type of the successful response data
 * @param <E> the type of the error
 */
public class ServiceResponse<T, E> {

    private final T data;
    private final E error;
    private final boolean success;

    private ServiceResponse(T data, E error, boolean success) {
        this.data = data;
        this.error = error;
        this.success = success;
    }

    /**
     * Creates a successful response with data.
     */
    public static <T, E> ServiceResponse<T, E> success(T data) {
        return new ServiceResponse<>(data, null, true);
    }

    /**
     * Creates a failure response with an error.
     */
    public static <T, E> ServiceResponse<T, E> failure(E error) {
        return new ServiceResponse<>(null, error, false);
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFailure() {
        return !success;
    }

    public T getData() {
        return data;
    }

    public E getError() {
        return error;
    }
}
