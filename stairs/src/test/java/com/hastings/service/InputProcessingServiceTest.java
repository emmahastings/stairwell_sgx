package com.hastings.service;

import com.hastings.exception.InvalidStepsPerFlight;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by emmakhastings on 13/05/2017.
 *
 * @author emmakhastings
 *         <p>
 *         Unit test based on examples provided
 */
@RunWith(MockitoJUnitRunner.class)
public class InputProcessingServiceTest {

    private InputProcessingService inputProcessingService;

    @Before
    public void setUp() throws Exception {
        inputProcessingService = new InputProcessingService();
    }

    @Test
    public void calculateMinNumberOfStrides_OneFlight_IsEqualTo6() throws Exception {
        int[] input = {17};
        int stepsPerStride = 3;
        assertThat(inputProcessingService.calculateMinNumberOfStrides(input, stepsPerStride)).isEqualTo(6);
    }

    @Test
    public void calculateMinNumberOfStrides_TwoFlights_IsEqualTo14() throws Exception {
        int[] input = {17, 17};
        int stepsPerStride = 3;
        assertThat(inputProcessingService.calculateMinNumberOfStrides(input, stepsPerStride)).isEqualTo(14);
    }

    @Test
    public void calculateMinNumberOfStrides_MultipleFlights_IsEqualTo50() throws Exception {
        int[] input = {4, 9, 8, 11, 7, 20, 14};
        int stepsPerStride = 2;
        assertThat(inputProcessingService.calculateMinNumberOfStrides(input, stepsPerStride)).isEqualTo(50);
    }

    @Test
    public void calculateMinNumberOfStrides_StepsPerStrideMoreThanStepsInFlight_IsEqualTo50() throws Exception {
        int[] input = {2,2};
        int stepsPerStride = 3;
        assertThat(inputProcessingService.calculateMinNumberOfStrides(input, stepsPerStride)).isEqualTo(4);
    }

    @Test(expected=InvalidStepsPerFlight.class)
    public void calculateMinNumberOfStrides_InvalidSteps_ShouldThrowException() throws Exception {
        int[] input = {100, 0};
        int stepsPerStride = 3;
        inputProcessingService.calculateMinNumberOfStrides(input, stepsPerStride);
    }
}