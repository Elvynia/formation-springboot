package fr.wcs.wildemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ErrorController {

    private static Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(Exception e) {
        LOGGER.error("Erreur non gérée dans l'application : ", e);
    	return "error";
    }
}
