package com.lfumard.medilabo_ui.proxies;

//import org.springframework.cloud.openfeign.FeignClient;
import com.lfumard.medilabo_ui.beans.SignupRequestBean;
import com.lfumard.medilabo_ui.security.AuthFeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import com.lfumard.medilabo_ui.beans.PatientBean;

import java.util.List;


@FeignClient(name = "patient", url = "${medilabo_gateway.url}")
//@Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTAiLCJpYXQiOjE3MTUwOTA1NzcsImV4cCI6MTcxNTE3Njk3N30.R9XqM5fyv1eZSs8Zzk4vO48i3mSKgJrvAzEbuKHHeUo")
public interface PatientProxies {

    /*@Value("${medilabo_token}")
    private String token;*/

    @GetMapping(value = "/patient/list")
    List<PatientBean> findAll(@RequestHeader("Authorization") String token);

    //@PostMapping(value = "/patient/add")
    //List<PatientBean> addPatient(PatientBean patient);

    @PostMapping("/patient/validate")
    List<PatientBean> addPatient(PatientBean patientBean, @RequestHeader("Authorization") String token);
    //List<PatientBean> addPatient(@Validated PatientBean patientBean);
    //List<PatientBean> validate(@Validated PatientBean patientBean);

    //@GetMapping(value = "/{id}")
    //PatientBean findById(@PathVariable("id") Long id);



    @GetMapping("/patient/{patientId}")
    PatientBean getPatientById(@PathVariable("patientId") Long patientId, @RequestHeader("Authorization") String token);
    //PatientBean showUpdatePatientForm(Long patientId);
    //PatientBean findById(@PathVariable("patientId") Long patientId);

    //@PostMapping(value = "/add")
    //List<PatientBean> validate(PatientBean patient);

    @PutMapping("/patient/update/{id}")
    //List<PatientBean> updatePatient(PatientBean patient, Long patientId);
    List<PatientBean> updatePatient(@PathVariable("id") Long id, PatientBean patient, @RequestHeader("Authorization") String token);

    @DeleteMapping("/patient/delete/{patientId}")
    void deletePatientById(@PathVariable(value = "patientId") Long patientId, @RequestHeader("Authorization") String token);

    @PostMapping("/api/auth/signin")
    String signup(SignupRequestBean signupRequestBean);

    //@GetMapping("/update/{patientId}")
    //PatientBean findById(@PathVariable("patientId") Long patientId);

    //@GetMapping("/update/{patientId}")
    //PatientBean findById(Long patientId);

    //@PostMapping("/patient/add")
    //void addPatient(PatientBean patient);

    //@PostMapping("/patient/validate")
    //List<PatientBean> validate();

    //@GetMapping(value = "/update/{id}")
    //PatientBean showUpdatePatientForm(@PathVariable(value = "patientId") Long patientId);

    //@PostMapping("/patient/update/{patientId}")
    //void updatePatient(PatientBean patient, Long patientId);
}

