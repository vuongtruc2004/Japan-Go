package com.nass.api.middleware;

import com.nass.application_service.dto.response.base.ApiResponse;
import com.nass.contract.annotations.ApiResponseFormat;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // nếu controller đã trả ra ApiResponse rồi thì không wrap nữa
        return !ApiResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiResponse) {
            return body;
        }

        ApiResponseFormat apiResponseFormat = returnType.getMethodAnnotation(ApiResponseFormat.class);
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
        return new ApiResponse<>(
                statusCode,
                devMessage,
                userMessage,
                body
        );
    }
}
