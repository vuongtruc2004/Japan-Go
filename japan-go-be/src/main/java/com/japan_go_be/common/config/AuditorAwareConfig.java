package com.japan_go_be.common.config;

import com.japan_go_be.common.constant.message.DefaultMessage;
import com.japan_go_be.common.i18n.I18nService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AuditorAwareConfig implements AuditorAware<String> {
    private final I18nService i18nService;

    @Override
    public Optional<String> getCurrentAuditor() {
        String systemName = i18nService.translation(DefaultMessage.DEFAULT_CREATE_BY);
        return Optional.ofNullable(systemName);
    }

}
