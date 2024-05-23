package com.lfumard.medilabo_ui.proxies;

import com.lfumard.medilabo_ui.beans.SignupRequestBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.lfumard.medilabo_ui.beans.PatientBean;

import java.util.List;


@FeignClient(name = "patient", url = "${medilabo_gateway.url}")
public interface PatientProxies {

    @GetMapping(value = "/patient/list")
    List<PatientBean> findAll(@RequestHeader("Authorization") String token);

    @PostMapping("/patient/validate")
    List<PatientBean> addPatient(PatientBean patientBean, @RequestHeader("Authorization") String token);

    @GetMapping("/patient/{patientId}")
    PatientBean getPatientById(@PathVariable("patientId") Long patientId, @RequestHeader("Authorization") String token);

    @PutMapping("/patient/update/{id}")
    List<PatientBean> updatePatient(@PathVariable("id") Long id, PatientBean patient, @RequestHeader("Authorization") String token);

    @DeleteMapping("/patient/delete/{patientId}")
    void deletePatientById(@PathVariable(value = "patientId") Long patientId, @RequestHeader("Authorization") String token);

    @PostMapping("/api/auth/signin")
    String signup(SignupRequestBean signupRequestBean);

}

