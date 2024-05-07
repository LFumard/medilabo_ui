package com.lfumard.medilabo_ui.controller;

import com.lfumard.medilabo_ui.beans.NoteBean;
import com.lfumard.medilabo_ui.beans.PatientBean;
import com.lfumard.medilabo_ui.service.NoteService;
import com.lfumard.medilabo_ui.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private Logger logger = LoggerFactory.getLogger(NoteController.class);

    private final NoteService noteService;
    private final PatientService patientService;

    public NoteController(NoteService noteService, PatientService patientService) {
        this.noteService = noteService;
        this.patientService = patientService;
    }
    @GetMapping("/{id}")
    public String getNoteById(@PathVariable("id") String id, Model model) {
        logger.info("New request Mapping  : getNoteById " + id);
        NoteBean noteBean = noteService.getNoteById(id);
        PatientBean patientBean = patientService.findById(noteBean.getPatientId());

        model.addAttribute("note", noteBean);
        model.addAttribute("patientName", patientBean.getFirstName() + " " + patientBean.getLastName());
        return "note/updateNote";
    }

    /*@GetMapping("/list")
    public String getAllNote(Model model) {
        logger.info("New request Mapping  : getAllNote ");
        model.addAttribute("note", noteService.getAllNote());
        //return "note";
        return "patient/list";
    }*/

    @PostMapping("/addNote")
    public String addNote(@ModelAttribute("note")  NoteBean note){
        logger.info("New request PostMapping  : addNote " + note.toString());
        noteService.addNote(note);
        return "redirect:/patient/update/"+note.getPatientId();
        //return "note/addNote";
    }
    @GetMapping("/{id}/addNote")
    public String addNoteForm(@PathVariable(value = "id") Long patId, Model model){

        if(patId == 0) return "redirect:/patient/list";

        logger.info("New request Mapping  : addNoteForm " + patId);
        NoteBean noteBean = new NoteBean();
        PatientBean patientBean = patientService.findById(patId);
        model.addAttribute("patientName", patientBean.getFirstName() + " " + patientBean.getLastName());
        model.addAttribute("patientId", patientBean.getId());

        //note.setPatientId(patId);
        model.addAttribute("note", noteBean);
        return "note/addNote";
    }
    //@GetMapping("/delete")
    @GetMapping("/delete/{id},{patId}")
    public String deleteNoteById(@PathVariable("id") String noteId, @PathVariable("patId") Long patientId){
    //public String deleteNoteById(@RequestParam("id") String noteId, @RequestParam("patId") Long patientId){
        logger.info("New request DeleteMapping  : deleteNoteById " + noteId);
        noteService.deleteNoteById(noteId);
        //return "redirect:/patient/" + toString().valueOf(patientId.floatValue());
        return "redirect:/patient/update/" + patientId.toString();
    }
    @PostMapping("/updateNote")
    //public String updateNoteById(@ModelAttribute  NoteBean note, Model model){
    public String updateNoteById(@ModelAttribute  NoteBean note){
        logger.info("New request PostMapping updateNoteById : " + note.toString());
        noteService.updateNote(note);
        return "redirect:/patient/update/"+note.getPatientId();
    }
}
