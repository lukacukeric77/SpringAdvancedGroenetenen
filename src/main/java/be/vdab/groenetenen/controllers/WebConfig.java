package be.vdab.groenetenen.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


// FixedLocaleResolver, setting the whole website on French in Belgium

//@Configuration
//class WebConfig {
//
//    @Bean
//    FixedLocaleResolver localeResolver(){
//        return new FixedLocaleResolver(new Locale("fr", "BE"));
//    }
//}

//SessionLocaleResolver

//@Configuration
//class WebConfig {
//
//    @Bean
//    SessionLocaleResolver localeResolver() {
//        return new SessionLocaleResolver();
//    }
//
//}

// SessionLocaleResolver

//@Configuration
//class WebConfig implements WebMvcConfigurer{
//
//    @Bean
//    LocaleResolver localeResolver(){
//        return new SessionLocaleResolver();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LocaleChangeInterceptor());
//    }


@Configuration
class WebConfig implements WebMvcConfigurer{

    @Bean
    CookieLocaleResolver localeResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieMaxAge(604_800);
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }

}

