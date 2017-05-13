package com.hastings.exception;

/**
 * Created by emmakhastings on 13/05/2017.
 *
 * @author emmakhastings
 *         <p>
 *         Custom exception to handle invalid steps per stride
 */
public class InvalidStepsPerStride extends Exception {
    public InvalidStepsPerStride(String message) {
        super(message);
    }
}
