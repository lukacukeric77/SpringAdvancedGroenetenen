package be.vdab.groenetenen.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

@Aspect
@Component
class Auditing {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //    @Before("execution(* be.vdab.groenetenen.services.*.*(..))")
//    @AfterReturning(pointcut = "execution(* be.vdab.groenetenen.services.*.*(..))", returning = "returnValue")
    @AfterThrowing(value = "be.vdab.groenetenen.aop.PointcutExpressions.services()", throwing = "ex")
    void schrijfAudit(JoinPoint joinPoint, /*Object returnValue*/ Throwable ex) {
        StringBuilder builder = new StringBuilder("Tijdstrip\t").append(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            builder.append("\nGebruiker\t").append(authentication.getName());
        }
        builder.append("\nMethod\t\t").append(joinPoint.getSignature().toLongString());
        Arrays.stream(joinPoint.getArgs()).forEach(object -> builder.append("\nParameter\t").append(object));
        //for afterreturning
//        if (returnValue != null){
//            builder.append("\nReturn\t\t");
//            if (returnValue instanceof Collection){
//                builder.append(((Collection<?>) returnValue).size()).append(" objects");
//            } else {
//                builder.append(returnValue.toString());
//            }
//        }
        //for afterthrowable
        logger.error(builder.toString(), ex);

//        logger.info(builder.toString());
    }
}
