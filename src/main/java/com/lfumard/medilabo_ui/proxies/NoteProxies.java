package com.lfumard.medilabo_ui.proxies;

import com.lfumard.medilabo_ui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note", url = "${medilabo_gateway.url}")
public interface NoteProxies {
    @GetMapping(value = "/{id}")
    NoteBean getNoteById(@PathVariable String id);

    @PostMapping(value = "/add")
    void addNote(NoteBean note);

    @DeleteMapping(value = "/delete/{id}")
    void deleteNoteById(@PathVariable String noteId);

    @PutMapping(value = "/update")
    void updateNote(NoteBean note);

    @GetMapping(value = "/list/{patId}")
    List<NoteBean> getNotesByPatId(@PathVariable String patId);
}
