package com.accenture.jive.books;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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

}
