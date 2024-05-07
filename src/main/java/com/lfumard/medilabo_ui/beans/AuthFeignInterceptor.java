package com.lfumard.medilabo_ui.beans;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class AuthFeignInterceptor implements RequestInterceptor {

    @Value("${medilabo.token}")
    private String token;
    @Override
    public void apply(RequestTemplate template) {
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            final HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
            //template.header("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTAiLCJpYXQiOjE3MTUwOTA1NzcsImV4cCI6MTcxNTE3Njk3N30.R9XqM5fyv1eZSs8Zzk4vO48i3mSKgJrvAzEbuKHHeUo");
            template.header("Authorization","Bearer "+ token);
        }
    }
}
