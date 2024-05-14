package com.lfumard.medilabo_ui.controller;

import com.lfumard.medilabo_ui.beans.PatientBean;
import com.lfumard.medilabo_ui.service.AssessmentService;
import com.lfumard.medilabo_ui.service.NoteService;
import com.lfumard.medilabo_ui.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;

import java.util.List;


@Controller
@RequestMapping("/patient")
public class PatientController {

    //@Autowired
    private final PatientService patientService;
    private final NoteService noteService;
    private final AssessmentService assessmentService;

    private static final Logger logger = LogManager.getLogger("PatientController");

    public PatientController(PatientService patientService, NoteService noteService, AssessmentService assessmentService) {
        this.patientService = patientService;
        this.noteService = noteService;
        this.assessmentService = assessmentService;
    }

    @GetMapping("/list")
    public String home(Model model, @CookieValue("medilabo") String medilaboCookie) {
        logger.info("New request Mapping from UI : show all Patients");
        List<PatientBean> patientBeanList = patientService.findAll(medilaboCookie);
        model.addAttribute("patientList", patientBeanList);
        return "patient/list";
    }

 /*   @GetMapping("/{patientId}")
    public String GetPatient((@PathVariable("patientId") Long patientId, Model model) {
        logger.info("New request Mapping from UI : show all Patients");
        PatientBean patientBean = patientProxy.findById(patientId);
        model.addAttribute("patient", patientBean);
        return "patient";
    }*/

    @GetMapping("/add")
    public String newPatient(Model model) {
        logger.info("New request Mapping from UI : show form to add new Patient");
        PatientBean patientBean = new PatientBean();
        model.addAttribute("patient", patientBean);
        return "patient/add";
    }

    @PostMapping("/validate")
    //public RedirectView addValidationPatient(PatientBean patient, BindingResult result, Model model) {
    public String addValidationPatient(PatientBean patient, BindingResult result, Model model, @CookieValue("medilabo") String medilaboCookie) {

        if(result.hasErrors()) {
            logger.error("New request Post Mapping from IU : ERROR add new patient : " + patient);
            //return new RedirectView("/patient/add");
            return ("/patient/add");
        }
        //patientService.addPatient(patient);
        //model.addAttribute("patientList", patientService.findAll());
        //model.addAttribute("patientList", patientService.addPatient(patient));
        logger.info("New request Post Mapping from UI : Add new Patient : " + patient);
        model.addAttribute("patientList", patientService.addPatient(patient, medilaboCookie));
        //return new RedirectView("redirect:/patient/list");
        return "redirect:/patient/list";
        //return "/patient/list";
    }

 /*   @PostMapping("/add")
    public String addPatient(PatientBean patient, BindingResult result, Model model) {

        if(result.hasErrors()) {
            logger.error("New request Post Mapping from UI : ERROR add new patient : " + patient);
            return "patient/add";
        }
        patientProxy.addPatient(patient);
        model.addAttribute("patientList", patientProxy.findAll());
        logger.info("New request Post Mapping from UI : Add new Patient : " + patient);
        return "redirect:/patient/list";
    }*/

   @GetMapping("/update/{patientId}")
    public String showUpdatePatientForm(@PathVariable("patientId") Long patientId, Model model, @CookieValue("medilabo") String medilaboCookie) {

        logger.info("New request Get Mapping from UI : update patient : " + patientId);
        model.addAttribute("patient", patientService.findById(patientId, medilaboCookie));
        model.addAttribute("note", noteService.getNoteByPatientId(patientId, medilaboCookie));
        model.addAttribute("assessment", assessmentService.getAssessment(patientId, medilaboCookie));
        return "patient/update";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable("id") Long id, PatientBean patient, BindingResult result, Model model, @CookieValue("medilabo") String medilaboCookie) {

        if (result.hasErrors()) {
            logger.error("New request Post Mapping from UI : ERROR update patient : " + patient);
            return "patient/update";
        }
        logger.info("New request Post Mapping from UI : update patient : " + patient);
        //patientService.updatePatient(patient, patientId);
        model.addAttribute("patientList", patientService.updatePatient(id, patient, medilaboCookie));
        return "redirect:/patient/list";
    }

    @GetMapping(value = "/delete/{patientId}")
    public String deletePatientById(@PathVariable("patientId") Long patientId, @CookieValue("medilabo") String medilaboCookie) {
        logger.info("New request Get Mapping from UI : delete patient : " + patientId);
        // Suppression des notes associées au patient
        patientService.deleteAllByPatientId(patientId, medilaboCookie);
        return "redirect:/patient/list";
    }
}
