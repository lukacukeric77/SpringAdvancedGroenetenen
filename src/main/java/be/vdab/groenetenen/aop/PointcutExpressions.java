package be.vdab.groenetenen.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
class PointcutExpressions {
    @Pointcut("execution(* be.vdab.groenetenen.services.*.*(..))")
    void services() {}
}
