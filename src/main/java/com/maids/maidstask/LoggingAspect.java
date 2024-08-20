package com.maids.maidstask;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.maids.maidstask.books.services.BooksService;
import com.maids.maidstask.patrons.services.PatronsService;
@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Log method entry
    @Before("execution(* com.maids.maidstask.books.services.BooksService.addNewBook(..)) || " +
            "execution(* com.maids.maidstask.books.services.BooksService.updateBookById(..)) || " +
            "execution(* com.maids.maidstask.patrons..services.PatronService.processTransaction(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Entering method: " + methodName);
    }

    // Log method exit with result
    @AfterReturning(pointcut = "execution(* com.maids.maidstask.books.services.BooksService.addNewBook(..)) || " +
            "execution(* com.maids.maidstask.books.services.BooksService.updateBookById(..)) || " +
            "execution(* com.maids.maidstask.patrons..services.PatronService.processTransaction(..))",
            returning = "result")
    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Exiting method: " + methodName + ", Result: " + result);
    }

    // Log method exception
    @AfterThrowing(pointcut = "execution(* com.maids.maidstask.books.services.BooksService.addNewBook(..)) || " +
            "execution(* com.maids.maidstask.books.services.BooksService.updateBookById(..)) || " +
            "execution(* com.maids.maidstask.patrons..services.PatronService.processTransaction(..))",
            throwing = "exception")
    public void logAfterMethodThrowsException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("Exception in method: " + methodName + ", Exception: " + exception.getMessage());
    }

    // You can add additional advice for performance metrics if needed
}
