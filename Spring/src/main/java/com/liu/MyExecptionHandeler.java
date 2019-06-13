package com.liu;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Liush
 * @description 增加全局处理
 * @date 2019/6/13 11:45
 **/
@ControllerAdvice
public class MyExecptionHandeler  extends ResponseEntityExceptionHandler{


    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> myHandleException(Exception ex, WebRequest request) throws Exception {

        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);




    }





}
