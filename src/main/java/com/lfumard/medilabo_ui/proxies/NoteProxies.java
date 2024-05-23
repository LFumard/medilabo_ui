package com.lfumard.medilabo_ui.proxies;

import com.lfumard.medilabo_ui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note", url = "${medilabo_gateway.url}")
public interface NoteProxies {
    @GetMapping(value = "/service_note/{id}")
    NoteBean getNoteById(@PathVariable String id, @RequestHeader("Authorization") String token);

    @PostMapping(value = "/service_note/addNote")
    void addNote(NoteBean note, @RequestHeader("Authorization") String token);

    @DeleteMapping(value = "/service_note/delete/{id}")
    void deleteNoteById(@PathVariable String id, @RequestHeader("Authorization") String token);

    @DeleteMapping(value = "/service_note/deleteNoteByPatientId/{patId}")
    void deleteNoteByPatientId(@PathVariable Long patId, @RequestHeader("Authorization") String token);
    @PutMapping(value = "/service_note/update")
    void updateNote(NoteBean note, @RequestHeader("Authorization") String token);

    @GetMapping(value = "/service_note/list/{patId}")
    List<NoteBean> getNotesByPatId(@PathVariable Long patId, @RequestHeader("Authorization") String token);

}
