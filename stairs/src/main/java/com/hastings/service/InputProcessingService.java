package com.hastings.service;

import com.hastings.exception.InvalidStepsPerFlight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by emmakhastings on 13/05/2017.
 *
 * @author emmakhastings
 *         <p>
 *         Service to handle user supplied input and calculate the minimum
 *         number of strides required to reach the top of a stairwell
 */
@Service
public class InputProcessingService {

    private static final int LANDING_STRIDES = 2;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Calculate the minimum number of strides required to the reach top of a stairwell
     *
     * @param flights        An array containing the number of steps in each flight
     * @param stepsPerStride Number of steps you can cover in each stride
     * @throws InvalidStepsPerFlight A flight should have between 1 - 20 steps based on problem specification
     */
    public int calculateMinNumberOfStrides(int[] flights, int stepsPerStride) throws InvalidStepsPerFlight {

        int minNumberOfStride = 0;

        for (int flightSteps : flights) {
            if (flightSteps >= 1 && flightSteps <= 20) {
                minNumberOfStride += calculateStridesForFlight(stepsPerStride, flightSteps);
            } else {
                logger.error("Invalid number of flights per step entered. Value entered: " + flightSteps);
                throw new InvalidStepsPerFlight("Steps per flight must be between 1 - 20");
            }
        }

        // If the array supplied has more than one flight then calculate additional landing strides
        if (flights.length > 1) {
            minNumberOfStride += calculateLandingStrides(flights.length);
        }
        return minNumberOfStride;
    }

    /**
     * Calculate strides used to ascend a flight
     *
     * @param stepsPerStride Number of steps you can cover in each stride
     * @param flightSteps    Number of steps in a flight of stairs
     */
    private int calculateStridesForFlight(int stepsPerStride, int flightSteps) {
        int stridesUsed = 0;
        return count(stridesUsed, stepsPerStride, flightSteps);
    }

    /**
     * Recursive algorithm counting steps used until the amount of steps remaining can be climbed in one stride
     *
     * @param stridesUsed    counter holding the number of strides used
     * @param stepsPerStride Number of steps you can cover in each stride
     * @param flightSteps    Number of steps in a flight of stairs
     */
    private int count(int stridesUsed, int stepsPerStride, int flightSteps) {
        if (flightSteps <= stepsPerStride) {
            return stridesUsed + 1;
        } else {
            flightSteps -= stepsPerStride;
            stridesUsed += 1;
            return count(stridesUsed, stepsPerStride, flightSteps);
        }
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



