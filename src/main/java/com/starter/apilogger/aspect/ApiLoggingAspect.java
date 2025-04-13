package com.starter.apilogger.aspect;


import com.starter.apilogger.properties.ApiLoggingProperties;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;


@Aspect
@Component
public class ApiLoggingAspect {

    private final ApiLoggingProperties properties;
    private  final Logger logger = LoggerFactory.getLogger(ApiLoggingAspect.class);

    public ApiLoggingAspect(ApiLoggingProperties properties) {
        this.properties = properties;
    }

    @Around("@annotation(com.starter.apilogger.annotation.LoggableApi)")
    public Object logApiCall(ProceedingJoinPoint joinPoint) throws Throwable {
        if(!properties.isEnabled()){
            return joinPoint.proceed();
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        Object[] args = joinPoint.getArgs();

        log("Api Call : " + methodName + " | Arguments : " + Arrays.toString(args));

        Object result = joinPoint.proceed();

        log("Api Response : " + methodName + " | returned : " + result);
        return result;
    }

    private void log(String message){
        switch(properties.getLevel().toLowerCase()){
            case "warn" -> logger.warn(message);
            case "error" -> logger.error(message);
            case "debug" -> logger.debug(message);
            default -> logger.info(message);
        }
    }

}
