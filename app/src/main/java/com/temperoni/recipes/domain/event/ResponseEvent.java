package com.temperoni.recipes.domain.event;

/**
 * @author Leandro Temperoni
 */

public class ResponseEvent<T> {

    private boolean success;
    private T payload;
    private String errorMessage;

    public boolean isSuccess() {
        return success;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
        success = true;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        success = false;
    }
}
