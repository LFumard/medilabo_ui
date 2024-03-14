package com.lfumard.medilabo_ui.controller;

import com.lfumard.medilabo_ui.beans.PatientBean;
import com.lfumard.medilabo_ui.proxies.PatientProxies;
import com.lfumard.medilabo_ui.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequestMapping("/patient")
public class PatientController {

    //@Autowired
    private final PatientService patientService;

    private static final Logger logger = LogManager.getLogger("PatientController");

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/list")
    public String home(Model model) {
        logger.info("New request Mapping from UI : show all Patients");
        List<PatientBean> patientBeanList = patientService.findAll();
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
    public String addValidationPatient(PatientBean patient, BindingResult result, Model model) {

        if(result.hasErrors()) {
            logger.error("New request Post Mapping from IU : ERROR add new patient : " + patient);
            //return new RedirectView("/patient/add");
            return ("/patient/add");
        }
        //patientService.addPatient(patient);
        //model.addAttribute("patientList", patientService.findAll());
        //model.addAttribute("patientList", patientService.addPatient(patient));
        logger.info("New request Post Mapping from UI : Add new Patient : " + patient);
        model.addAttribute("patientList", patientService.addPatient(patient));
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
    public String showUpdatePatientForm(@PathVariable("patientId") Long patientId, Model model) {

        logger.info("New request Get Mapping from UI : update patient : " + patientId);
        model.addAttribute("patient", patientService.findById(patientId));
        return "patient/update";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable("id") Long id, PatientBean patient,
                                       BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("New request Post Mapping from UI : ERROR update patient : " + patient);
            return "patient/update";
        }
        logger.info("New request Post Mapping from UI : update patient : " + patient);
        //patientService.updatePatient(patient, patientId);
        model.addAttribute("patientList", patientService.updatePatient(id, patient));
        return "redirect:/patient/list";
    }

    @GetMapping(value = "/delete/{patientId}")
    public String deletePatientById(@PathVariable("patientId") Long patientId) {
        patientService.deleteById(patientId);
        logger.info("New request Get Mapping from UI : delete patient : " + patientId);
        return "redirect:/patient/list";
    }
}
