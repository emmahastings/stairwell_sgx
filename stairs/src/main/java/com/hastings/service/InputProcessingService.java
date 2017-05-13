package com.hastings.service;

import org.springframework.stereotype.Service;

/**
 * Created by emmakhastings on 13/05/2017.
 *
 * @author emmakhastings
 *         <p>
 *         Service to handle user supplied input and calculate the minimum
 *         number of strides required to reach top of a stairwell
 */
@Service
public class InputProcessingService {

    private static final int LANDING_STRIDES = 2;

    /**
     * Calculate the minimum number of strides required to reach top of a stairwell
     *
     * @param numberOfStepsPerFlight An array containing the number of steps in each flight
     * @param stepsPerStride         Number of steps you can cover in each stride
     */
    public int calculateMinNumberOfStrides(int[] numberOfStepsPerFlight, int stepsPerStride) {

        int minNumberOfStride = 0;

        for (int flight : numberOfStepsPerFlight) {
            minNumberOfStride += calculateStridesForFlight(stepsPerStride, flight);
        }

        // If the array supplied has more than one flight then calculate additional landing strides
        if (numberOfStepsPerFlight.length > 1) {
            minNumberOfStride += calculateLandingStrides(numberOfStepsPerFlight.length);
        }
        return minNumberOfStride;
    }

    /**
     * Calculate strides used to ascend a flight
     * by subtracting steps until there are no stairs left while tracking strides used.
     *
     * @param stepsPerStride        Number of steps you can cover in each stride
     * @param numberOfStepsInFlight Number of steps in a flight of stairs
     */
    private int calculateStridesForFlight(int stepsPerStride, int numberOfStepsInFlight) {
        int stridesUsed = 0;
        while (numberOfStepsInFlight > 0) {
            numberOfStepsInFlight -= stepsPerStride;
            stridesUsed++;
        }
        return stridesUsed;
    }

    /**
     * Calculate strides used to ascend a flight of stairs with landings in between.
     *
     * @param numberOfFlights Number of flights
     */
    private int calculateLandingStrides(int numberOfFlights) {
        return ((numberOfFlights - 1) * LANDING_STRIDES);
    }
}



