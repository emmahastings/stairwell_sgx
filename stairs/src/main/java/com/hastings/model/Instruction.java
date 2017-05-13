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

    public Instruction(String instructions) {
        this.instructions = instructions;
    }

    public String getInstructions() {
        return instructions;
    }
}
