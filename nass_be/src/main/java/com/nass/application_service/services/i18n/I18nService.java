package com.nass.application_service.services.i18n;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class I18nService {
    private final MessageSource messageSource;

    /**
     * @param key  key of the message (see in the messages.properties file)
     * @param args Object... is like an array of object
     * @return string in the current locale
     */
    public String translation(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}
