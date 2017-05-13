package com.hastings.model;

/**
 * Created by emmakhastings on 13/05/2017.
 *
 * @author emmakhastings
 *         <p>
 *         Model class to represent JSON input
 */
public class ProblemInput {

    private int[] numberOfStepsPerFlight;

    private int stepsPerStride;

    public ProblemInput() {
    }

    public ProblemInput(int[] numberOfStepsPerFlight, int stepsPerStride) {
        this.numberOfStepsPerFlight = numberOfStepsPerFlight;
        this.stepsPerStride = stepsPerStride;
    }

    public int[] getNumberOfStepsPerFlight() {
        return numberOfStepsPerFlight;
    }

    public int getStepsPerStride() {
        return stepsPerStride;
    }
}
