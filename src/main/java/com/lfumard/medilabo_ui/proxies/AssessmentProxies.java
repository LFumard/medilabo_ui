package com.lfumard.medilabo_ui.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "assessment", url = "${medilabo_gateway.url}")
public interface AssessmentProxies {

    @GetMapping(value = "/assessment/{id}")
    String getAssessment(@PathVariable Long id, @RequestHeader("Authorization") String token);
}
