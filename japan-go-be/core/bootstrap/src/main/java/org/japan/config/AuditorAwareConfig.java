package org.japan.config;

import lombok.RequiredArgsConstructor;
import org.japan.i18n.I18nService;
import org.japan.message.DefaultMessage;
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
