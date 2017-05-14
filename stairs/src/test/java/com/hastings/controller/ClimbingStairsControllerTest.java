package com.hastings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hastings.model.ProblemInput;
import com.hastings.service.InputProcessingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by emmakhastings on 14/05/2017.
 *
 * @author emmakhastings
 *         <p>
 *         Unit test for ClimbingStairsController
 */
@RunWith(MockitoJUnitRunner.class)
public class ClimbingStairsControllerTest {

    @Mock
    private InputProcessingService inputProcessingService;

    private ClimbingStairsController climbingStairsController;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        climbingStairsController = new ClimbingStairsController(inputProcessingService);
        mockMvc = MockMvcBuilders.standaloneSetup(climbingStairsController).build();
    }

    @Test
    public void getInstructions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("instructions").value("Please supply input parameters:"))
                .andExpect(jsonPath("input1").value("array listing the number of flights (between 1 - 30, containing max 20 steps)"))
                .andExpect(jsonPath("input2").value("number of steps per stride (between 1 - 4)"));

        verifyZeroInteractions(inputProcessingService);
    }

    @Test
    public void calculateMinNumberOfStrides() throws Exception {

        // Create test input and convert to JSON
        ProblemInput input = new ProblemInput(new int[]{4, 4}, 2);
        String jsonInString = objectMapper.writeValueAsString(input);

        // Stub method response
        when(inputProcessingService.calculateMinNumberOfStrides(new int[]{4, 4}, 2)).thenReturn(6);

        mockMvc.perform(post("/")
                .content(jsonInString)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("minNumberOfStrides").value("6"));

        verify(inputProcessingService, times(1)).calculateMinNumberOfStrides(new int[]{4, 4}, 2);
    }

    @Test
    public void calculateMinNumberOfStrides_InvalidStepsPerStride_ShouldReturnBadRequest() throws Exception {

        // Create test input and convert to JSON
        ProblemInput input = new ProblemInput(new int[]{4, 4}, 10);
        String jsonInString = objectMapper.writeValueAsString(input);

        mockMvc.perform(post("/")
                .content(jsonInString)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("message").value("Steps per stride must be between 1 - 4"))
                .andExpect(jsonPath("status").value("BAD_REQUEST"));

        verifyZeroInteractions(inputProcessingService);
    }

    @Test
    public void calculateMinNumberOfStrides_NoFlights_ShouldReturnBadRequest() throws Exception {

        // Create test input and convert to JSON
        ProblemInput input = new ProblemInput(new int[]{}, 2);
        String jsonInString = objectMapper.writeValueAsString(input);

        mockMvc.perform(post("/")
                .content(jsonInString)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("message").value("A stairwell must have between 1 - 30 flights"))
                .andExpect(jsonPath("status").value("BAD_REQUEST"));

        verifyZeroInteractions(inputProcessingService);
    }

    @Test
    public void calculateMinNumberOfStrides_MaxNumberOfFlights_ShouldReturnBadRequest() throws Exception {

        // Create test input and convert to JSON
        ProblemInput input = new ProblemInput(new int[31], 2);
        String jsonInString = objectMapper.writeValueAsString(input);

        mockMvc.perform(post("/")
                .content(jsonInString)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("message").value("A stairwell must have between 1 - 30 flights"))
                .andExpect(jsonPath("status").value("BAD_REQUEST"));

        verifyZeroInteractions(inputProcessingService);
    }

    @Test
    public void calculateMinNumberOfStrides_NullNumberOfStepsPerFlight_ShouldReturnBadRequest() throws Exception {

        // Create test input and convert to JSON
        ProblemInput input = new ProblemInput(null, 2);
        String jsonInString = objectMapper.writeValueAsString(input);

        mockMvc.perform(post("/")
                .content(jsonInString)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("message").value("Number of steps per flight cannot be null"))
                .andExpect(jsonPath("status").value("BAD_REQUEST"));

        verifyZeroInteractions(inputProcessingService);
    }
}