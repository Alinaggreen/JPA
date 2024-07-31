package com.accenture.jive.books;

import com.accenture.jive.books.controller.dto.BookDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class PerformanceAspect {

    @Pointcut("execution(* com.accenture.jive.books.controller.BookController.readAllBooks(..))")
    public void pointcutReadAllBooks() {
    }

    @Before("pointcutReadAllBooks()")
    public void doBefore() {
        System.out.println("BEFORE");
    }

    @After("pointcutReadAllBooks()")
    public void doAfter() {
        System.out.println("AFTER");
    }

    @Around("pointcutReadAllBooks()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object result = pjp.proceed();
        long end = System.nanoTime();
        long duration = end-start;
        System.out.println("Duration: " + duration*Math.pow(10, -9) + "s");
        return result;
    }

}
