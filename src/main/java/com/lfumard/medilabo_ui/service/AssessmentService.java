package com.lfumard.medilabo_ui.service;

import com.lfumard.medilabo_ui.proxies.AssessmentProxies;
import org.springframework.stereotype.Service;

@Service
public class AssessmentService {

    private final AssessmentProxies assessmentProxies;

    public AssessmentService(AssessmentProxies assessmentProxies) {
        this.assessmentProxies = assessmentProxies;
    }

    public String getAssessment(Long patientId, String medilaboCookie) {
        return assessmentProxies.getAssessment(patientId, "Bearer "+medilaboCookie);
    }
}
