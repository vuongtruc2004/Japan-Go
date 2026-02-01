package com.nass.common.middleware;

import com.nass.common.annotation.ApiResponseFormat;
import com.nass.common.constant.DefaultMessage;
import com.nass.common.dto.ApiResponse;
import com.nass.common.i18n.I18nService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import tools.jackson.databind.ObjectMapper;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {
    private final ObjectMapper objectMapper;
    private final I18nService i18nService;

    private ApiResponse<Object> getObjectApiResponse(Object body, ServerHttpResponse response, ApiResponseFormat apiResponseFormat) {
        String devMessage = (apiResponseFormat != null && !apiResponseFormat.devMessage().isBlank())
                ? i18nService.translation(apiResponseFormat.devMessage())
                : i18nService.translation(DefaultMessage.DEFAULT_BLANK_DESCRIPTION);

        String clientMessage = (apiResponseFormat != null && !apiResponseFormat.clientMessage().isBlank())
                ? i18nService.translation(apiResponseFormat.clientMessage())
                : i18nService.translation(DefaultMessage.DEFAULT_BLANK_DESCRIPTION);

        int statusCode = 200;
        if (response instanceof ServletServerHttpResponse serverHttpResponse) {
            statusCode = serverHttpResponse.getServletResponse().getStatus();
        }

        return new ApiResponse<>(statusCode, devMessage, clientMessage, body);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // nếu controller đã trả ra ApiResponse rồi thì không wrap nữa
        return !ApiResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof byte[]
                || body instanceof Resource
                || body instanceof ApiResponse<?>) {
            return body;
        }

        ApiResponseFormat apiResponseFormat = returnType.getMethodAnnotation(ApiResponseFormat.class);
        ApiResponse<Object> wrapped = getObjectApiResponse(body, response, apiResponseFormat);

        // return type là String -> phải trả String (JSON)
        if (StringHttpMessageConverter
                .class.isAssignableFrom(selectedConverterType)) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return objectMapper.writeValueAsString(wrapped);
        }
        return wrapped;
    }
}
