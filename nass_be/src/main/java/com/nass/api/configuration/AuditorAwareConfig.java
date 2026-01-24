package com.nass.api.configuration;

import com.nass.application_service.services.i18n.I18nService;
import com.nass.contract.enums.messages.DefaultMessageEnum;
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
        String systemName = i18nService.translation(DefaultMessageEnum.DEFAULT_CREATE_BY.key);
        return Optional.ofNullable(systemName);
    }

}
