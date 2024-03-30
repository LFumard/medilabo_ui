package com.lfumard.medilabo_ui.proxies;

import com.lfumard.medilabo_ui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "assessment", url = "${medilabo_gateway.url}")
public interface AssessmentProxies {

    @GetMapping(value = "/assessment/{id}")
    String getAssessment(@PathVariable Long id);
}
