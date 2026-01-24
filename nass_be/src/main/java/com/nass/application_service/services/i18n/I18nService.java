package com.nass.application_service.services.i18n;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class I18nService {
    private final MessageSource messageSource;

    /*
    Object... args dùng để truyền 0, 1 hoặc nhiều hơn tham số vào mảng mà không sử dụng array
     */
    public String translation(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}
