package com.sujeet.project.rockpaperscissor.controller;

import com.sujeet.project.rockpaperscissor.exception.ExcessiveRoundsException;
import com.sujeet.project.rockpaperscissor.exception.InvalidInputException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public String handleInvalidInputException(InvalidInputException e, HttpServletResponse res, HttpServletRequest req) {
        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return e.getMessage();
    }

    @ExceptionHandler(ExcessiveRoundsException.class)
    public String handleExcessiveRoundsException(ExcessiveRoundsException e, HttpServletResponse response, HttpServletRequest request){
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return "Excessive Rounds Error: " + e.getMessage();
    }

}
