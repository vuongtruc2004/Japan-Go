package com.nass.application_service.validators;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EnumValidator {
    public boolean isValidEnumValue(String enumValue, Class<? extends Enum<?>> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equalsIgnoreCase(enumValue));
    }
}
