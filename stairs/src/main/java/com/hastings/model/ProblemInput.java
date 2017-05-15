package com.hastings.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by emmakhastings on 13/05/2017.
 *
 * @author emmakhastings
 *         <p>
 *         Model class to represent JSON input.
 *         Includes validation rules based on problem description
 */
public class ProblemInput {

    @NotNull(message = "Number of steps per flight cannot be null")
    @Size(message = "A stairwell must have between 1 - 30 flights", min = 1, max = 30)
    private int[] flights;

    @Min(value = 1, message = "Steps per stride must be between 1 - 4")
    @Max(value = 4, message = "Steps per stride must be between 1 - 4")
    private int stepsPerStride;

    public ProblemInput() {
    }

    public ProblemInput(int[] flights, int stepsPerStride) {
        this.flights = flights;
        this.stepsPerStride = stepsPerStride;
    }

    public int[] getFlights() {
        return flights;
    }

    public int getStepsPerStride() {
        return stepsPerStride;
    }
}
