package com.hastings.model;

import org.springframework.http.HttpStatus;

/**
 * Created by emmakhastings on 13/05/2017.
 *
 * @author emmakhastings
 *
 *  Model class to handle returning potential input error as JSON
 */
public class ExceptionDetails {

    private HttpStatus status;

    private String message;

    public ExceptionDetails(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
