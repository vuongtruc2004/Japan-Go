package com.nass.api.middleware;

import com.nass.application_service.dto.responses.base.ApiResponse;
import com.nass.contract.annotations.ApiResponseFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
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

    private static ApiResponse<Object> getObjectApiResponse(Object body, ServerHttpResponse response, ApiResponseFormat apiResponseFormat) {
        String devMessage = (apiResponseFormat != null && !apiResponseFormat.devMessage().isBlank())
                ? apiResponseFormat.devMessage()
                : "No dev message";

        String userMessage = (apiResponseFormat != null && !apiResponseFormat.userMessage().isBlank())
                ? apiResponseFormat.userMessage()
                : "Thành công!";

        int statusCode = 200;
        if (response instanceof ServletServerHttpResponse serverHttpResponse) {
            statusCode = serverHttpResponse.getServletResponse().getStatus();
        }

        return new ApiResponse<>(statusCode, devMessage, userMessage, body);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // nếu controller đã trả ra ApiResponse rồi thì không wrap nữa
        return !ApiResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null || body instanceof ApiResponse || body instanceof byte[]) {
            return body;
        }

        ApiResponseFormat apiResponseFormat = returnType.getMethodAnnotation(ApiResponseFormat.class);
        ApiResponse<Object> wrapped = getObjectApiResponse(body, response, apiResponseFormat);

        // return type là String -> phải trả String (JSON)
        if (org.springframework.http.converter.StringHttpMessageConverter
                .class.isAssignableFrom(selectedConverterType)) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return objectMapper.writeValueAsString(wrapped);
        }
        return wrapped;
    }
}
