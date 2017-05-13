package com.hastings.model;

/**
 * Created by emmakhastings on 13/05/2017.
 *
 * @author emmakhastings
 *         <p>
 *         Model class to handle returning instructions as JSON
 */
public class Instruction {

    private String instructions;

    private String input1;

    private String input2;

    public Instruction(String instructions, String input1, String input2) {
        this.instructions = instructions;
        this.input1 = input1;
        this.input2 = input2;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getInput1() {
        return input1;
    }

    public String getInput2() {
        return input2;
    }
}
