package be.vdab.groenetenen.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
class Statistieken {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ConcurrentHashMap<String, AtomicInteger> statistieken = new ConcurrentHashMap<>();

    @After(value = "be.vdab.groenetenen.aop.PointcutExpressions.services()")
    void statistiekBijwerken(JoinPoint joinPoint){
        String joinPointSignatuur = joinPoint.getSignature().toLongString();
        AtomicInteger vorigAantalOproepen = statistieken.putIfAbsent(joinPointSignatuur, new AtomicInteger(1));
        int aantalOproepen = vorigAantalOproepen == null ? 1 : vorigAantalOproepen.incrementAndGet();
        logger.info("{} werd {} keer opgeroepen", joinPointSignatuur, aantalOproepen);
    }
}
