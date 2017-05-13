package com.hastings.model;

/**
 * Created by emmakhastings on 13/05/2017.
 *
 * @author emmakhastings
 *         <p>
 *         Model class to represent JSON response
 */
public class ProblemOutput {

    private int minNumberOfStrides;

    public ProblemOutput(int minNumberOfStrides) {
        this.minNumberOfStrides = minNumberOfStrides;
    }

    public int getMinNumberOfStrides() {
        return minNumberOfStrides;
    }
}
