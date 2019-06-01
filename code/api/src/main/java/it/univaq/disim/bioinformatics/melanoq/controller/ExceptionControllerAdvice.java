package it.univaq.disim.bioinformatics.melanoq.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public Response exception(Exception e, HttpServletRequest request) {

        Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR);

        response.setPath(request.getRequestURI());
        response.setError(e.getMessage());

        return response;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Response badCredentials(Exception e, HttpServletRequest request) {

        Response response = new Response(HttpStatus.UNAUTHORIZED);

        response.setPath(request.getRequestURI());
        response.setError(e.getMessage());

        return response;
    }

    @ExceptionHandler(DisabledException.class)
    public Response disabled(Exception e, HttpServletRequest request) {

        Response response = new Response(HttpStatus.UNAUTHORIZED);

        response.setPath(request.getRequestURI());
        response.setError(e.getMessage());

        return response;
    }
}