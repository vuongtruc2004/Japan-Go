package com.nass.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class LocalResolverConfig {

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver sessionLocaleResolver = new AcceptHeaderLocaleResolver();
        Locale defaultLocale = new Locale.Builder()
                .setLanguage("vi")
                .setRegion("VN")
                .build();
        sessionLocaleResolver.setDefaultLocale(defaultLocale);
        return sessionLocaleResolver;
    }
}
