package com.hastings.controller;

import com.hastings.exception.InvalidStepsPerFlight;
import com.hastings.model.ExceptionDetails;
import com.hastings.model.Instruction;
import com.hastings.model.ProblemInput;
import com.hastings.model.ProblemOutput;
import com.hastings.service.InputProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
class ClimbingStairsController {

    private InputProcessingService inputProcessingService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ClimbingStairsController(InputProcessingService inputProcessingService) {
        this.inputProcessingService = inputProcessingService;
    }

    @GetMapping(produces = "application/json")
    ResponseEntity<Instruction> getInstructions() {
        String instruction = "Please supply input parameters:";
        String input1 = "array listing the number of flights (between 1 - 30, containing max 20 steps)";
        String input2 = "number of steps per stride (between 1 - 4)";
        return new ResponseEntity<Instruction>(new Instruction(instruction, input1, input2), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    ResponseEntity<ProblemOutput> calculateMinNumberOfStrides(@RequestBody @Valid ProblemInput input) throws InvalidStepsPerFlight {
        int minNumberOfStrides = inputProcessingService.calculateMinNumberOfStrides(input.getFlights(), input.getStepsPerStride());
        return new ResponseEntity<ProblemOutput>(new ProblemOutput(minNumberOfStrides), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ExceptionDetails
    handleBadRequest(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMessage = fieldErrors.stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
        logger.error("Attempted POST request with following errors: " + errorMessage);
        return new ExceptionDetails(HttpStatus.BAD_REQUEST, String.join(",", errorMessage));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidStepsPerFlight.class)
    @ResponseBody
    ExceptionDetails
    handleBadRequest(InvalidStepsPerFlight ex) {
        return new ExceptionDetails(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}