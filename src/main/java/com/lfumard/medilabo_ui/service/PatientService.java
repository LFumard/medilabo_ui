package com.lfumard.medilabo_ui.service;

import com.lfumard.medilabo_ui.beans.PatientBean;
import com.lfumard.medilabo_ui.proxies.NoteProxies;
import com.lfumard.medilabo_ui.proxies.PatientProxies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientProxies patientProxy;
    private final NoteProxies noteProxies;

    public PatientService(PatientProxies patientProxy, NoteProxies noteProxies) {
        this.patientProxy = patientProxy;
        this.noteProxies = noteProxies;
    }

    public List<PatientBean> findAll(String medilaboCookie) {

        return patientProxy.findAll("Bearer "+medilaboCookie);
    }

    public List<PatientBean> updatePatient(Long id, PatientBean patient, String medilaboCookie) {

        return patientProxy.updatePatient(id, patient, "Bearer "+medilaboCookie);
    }


    public PatientBean findById(Long patientId, String medilaboCookie) {
        return patientProxy.getPatientById(patientId, "Bearer "+medilaboCookie);
    }

    public List<PatientBean> addPatient(PatientBean patient, String medilaboCookie) {
        return patientProxy.addPatient(patient, "Bearer "+medilaboCookie);
    }

    public void deleteAllByPatientId(Long patientId, String medilaboCookie) {

        noteProxies.deleteNoteByPatientId(patientId, "Bearer "+medilaboCookie);
        patientProxy.deletePatientById(patientId, "Bearer "+medilaboCookie);
    }
}
