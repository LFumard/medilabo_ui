package com.lfumard.medilabo_ui.proxies;

//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.lfumard.medilabo_ui.beans.PatientBean;

import java.util.List;

@FeignClient(name = "patient", url = "${medilabo_gateway.url}")
public interface PatientProxies {

    @GetMapping(value = "patient/list")
    List<PatientBean> findAll();

    //@PostMapping(value = "/patient/add")
    //List<PatientBean> addPatient(PatientBean patient);

    @PostMapping("/patient/validate")
    List<PatientBean> addPatient(PatientBean patientBean);
    //List<PatientBean> addPatient(@Validated PatientBean patientBean);
    //List<PatientBean> validate(@Validated PatientBean patientBean);

    //@GetMapping(value = "/{id}")
    //PatientBean findById(@PathVariable("id") Long id);



    @GetMapping("/patient/update/{patientId}")
    PatientBean getPatientById(@PathVariable("patientId") Long patientId);
    //PatientBean showUpdatePatientForm(Long patientId);
    //PatientBean findById(@PathVariable("patientId") Long patientId);

    //@PostMapping(value = "/add")
    //List<PatientBean> validate(PatientBean patient);

    @PutMapping("/patient/update/{id}")
    //List<PatientBean> updatePatient(PatientBean patient, Long patientId);
    List<PatientBean> updatePatient(@PathVariable("id") Long id, PatientBean patient);

    @DeleteMapping("/patient/delete/{patientId}")
    void deletePatientById(@PathVariable(value = "patientId") Long patientId);

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
