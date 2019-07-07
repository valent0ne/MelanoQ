package it.univaq.disim.bioinformatics.melanoq.controller;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.service.A1ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);


    @ExceptionHandler(Exception.class)
    public Response exception(Exception e, HttpServletRequest request) {

        Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR);

        response.setPath(request.getRequestURI());
        response.setError(e.getMessage());

        LOGGER.error(e.getMessage());
        return response;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Response badCredentials(Exception e, HttpServletRequest request) {

        Response response = new Response(HttpStatus.UNAUTHORIZED);

        response.setPath(request.getRequestURI());
        response.setError(e.getMessage());

        LOGGER.error(e.getMessage());
        return response;
    }

    @ExceptionHandler(DisabledException.class)
    public Response disabled(Exception e, HttpServletRequest request) {

        Response response = new Response(HttpStatus.UNAUTHORIZED);

        response.setPath(request.getRequestURI());
        response.setError(e.getMessage());

        LOGGER.error(e.getMessage());
        return response;
    }

    @ExceptionHandler(BusinessException.class)
    public Response customException(BusinessException e, HttpServletRequest request){

        Response response = new Response(e.getHttpStatus());

        response.setPath(request.getRequestURI());
        response.setError(e.getMessage());

        LOGGER.error(e.getMessage());
        return response;
    }
}