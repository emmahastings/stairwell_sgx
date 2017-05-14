package com.hastings.controller;

import com.hastings.exception.InvalidStepsPerStride;
import com.hastings.model.ExceptionDetails;
import com.hastings.model.Instruction;
import com.hastings.model.ProblemInput;
import com.hastings.model.ProblemOutput;
import com.hastings.service.InputProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    ResponseEntity<ProblemOutput> calculateMinNumberOfStrides(@RequestBody @Valid ProblemInput input) throws InvalidStepsPerStride {
        int minNumberOfStrides = inputProcessingService.calculateMinNumberOfStrides(input.getNumberOfStepsPerFlight(), input.getStepsPerStride());
        return new ResponseEntity<ProblemOutput>(new ProblemOutput(minNumberOfStrides), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody ExceptionDetails
    handleBadRequest(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMessage = fieldErrors.stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
        return new ExceptionDetails(HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage.toString());
    }
}
