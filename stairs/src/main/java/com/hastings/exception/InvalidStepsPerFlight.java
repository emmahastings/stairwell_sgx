package com.hastings.exception;

/**
 * Created by emmakhastings on 14/05/2017.
 *
 * @author emmakhastings
 *         <p>
 *         Custom exception to handle invalid number of steps per flight
 */
public class InvalidStepsPerFlight extends Exception {
    public InvalidStepsPerFlight(String message) {
        super(message);
    }
}
