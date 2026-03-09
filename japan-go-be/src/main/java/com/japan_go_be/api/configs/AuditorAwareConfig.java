package com.japan_go_be.api.configs;

import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.contract.constants.message.DefaultMessage;
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
