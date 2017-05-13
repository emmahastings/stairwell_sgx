package com.hastings.controller;

import com.hastings.model.Instruction;
import com.hastings.model.ProblemInput;
import com.hastings.model.ProblemOutput;
import com.hastings.service.InputProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by emmakhastings on 13/05/2017.
 *
 * @author emmakhastings
 *         <p>
 *         Controller to expose HTTP endpoint and handle user input
 */
@RestController
@RequestMapping("/")
public class ClimbingStairsController {

    private InputProcessingService inputProcessingService;

    @Autowired
    public ClimbingStairsController(InputProcessingService inputProcessingService) {
        this.inputProcessingService = inputProcessingService;
    }

    @GetMapping(produces = "application/json")
    ResponseEntity<Instruction> getInstructions() {
        String instruction = "Please supply input parameters:";
        String input1 = "array listing the number of stairs";
        String input2 = "number of steps per stride";
        return new ResponseEntity<Instruction>(new Instruction(instruction, input1, input2), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    ResponseEntity<ProblemOutput> calculateMinNumberOfStrides(@RequestBody ProblemInput input) {
        int minNumberOfStrides = inputProcessingService.calculateMinNumberOfStrides(input.getNumberOfStepsPerFlight(), input.getStepsPerStride());
        return new ResponseEntity<ProblemOutput>(new ProblemOutput(minNumberOfStrides), HttpStatus.OK);
    }
}
