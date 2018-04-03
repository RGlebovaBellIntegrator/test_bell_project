package ru.bellintegrator.optional;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.xml.ws.Response;
import java.awt.*;
import java.math.BigDecimal;

@ControllerAdvice
public class DataAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                       MediaType selectedContentType,
                                       Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                       ServerHttpRequest request, ServerHttpResponse response) {

        if (body==null)
            return new Data();
        if (body.getClass()==String.class)
            return body;
        return new Data(body);
    }
}
