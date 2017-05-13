package com.hastings.model;

/**
 * Created by emmakhastings on 13/05/2017.
 *
 * @author emmakhastings
 *
 *  Model class to handle returning potential input error as JSON
 */
public class ExceptionDetails {

    private String status;

    private String message;

    public ExceptionDetails(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
