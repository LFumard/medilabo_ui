package com.lfumard.medilabo_ui.service;

import com.lfumard.medilabo_ui.beans.PatientBean;
import com.lfumard.medilabo_ui.proxies.PatientProxies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientProxies patientProxy;

    public PatientService(PatientProxies patientProxy) {
        this.patientProxy = patientProxy;
    }

    public List<PatientBean> findAll() {

        return patientProxy.findAll();
    }

    public List<PatientBean> updatePatient(Long id, PatientBean patient) {

        return patientProxy.updatePatient(id, patient);
    }

    public void deleteById(Long patientId) {

        patientProxy.deletePatientById(patientId);
    }

    public PatientBean findById(Long patientId) {
        return patientProxy.getPatientById(patientId);
    }

    public List<PatientBean> addPatient(PatientBean patient) {
        return patientProxy.addPatient(patient);
    }
}
