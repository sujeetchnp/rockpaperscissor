package com.sujeet.project.rockpaperscissor.model;

import com.sujeet.project.rockpaperscissor.exception.InvalidOptionException;

public enum Choice {
    ROCK("rock"), PAPER("paper"), SCISSOR("scissor");

    private static String choice;

    private Choice(String choice){

    }

    public static String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public static Choice getChoice(String choice) throws InvalidOptionException {
        Choice options = null;

        if (choice.equals("rock")){
            options = Choice.ROCK;
        }else if (choice.equals("paper")){
            options = Choice.PAPER;
        }else if (choice.equals("scissor")){
            options = Choice.SCISSOR;
        }else{
            throw new InvalidOptionException();
        }
        return options;
    }
}