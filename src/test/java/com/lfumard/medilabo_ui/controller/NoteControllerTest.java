package com.lfumard.medilabo_ui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lfumard.medilabo_ui.beans.NoteBean;
import com.lfumard.medilabo_ui.beans.PatientBean;
import com.lfumard.medilabo_ui.proxies.NoteProxies;
import com.lfumard.medilabo_ui.proxies.PatientProxies;
import com.lfumard.medilabo_ui.service.NoteService;
import com.lfumard.medilabo_ui.service.PatientService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PatientService patientService;

    @MockBean
    private NoteProxies noteProxies;

    @MockBean
    private PatientProxies patientProxies;

    private List<PatientBean> patientList;

    Cookie cookie = new Cookie("medilabo", "test");

    @Test
    public void testGetNoteById() throws Exception {

        PatientBean patient = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(1971,1,1), "1111111111", "M", "patientAddress1");
        when(patientProxies.getPatientById(1L, "Bearer test")).thenReturn(patient);

        NoteBean note = new NoteBean("1", 1L, "Note1 Patient 1");
        doReturn(new NoteBean("1", 1L, "Note1 Patient 1")).when(noteProxies).getNoteById("1", "Bearer test");

        mockMvc.perform(get("/note/1").contentType(APPLICATION_JSON).cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(view().name("note/updateNote"))
                .andExpect(model().attributeExists("note"))
                .andExpect(model().attributeExists("patientName"))
                .andExpect(model().size(2))
                .andExpect(model().attribute("patientName", "patientFirstName1 patientLastName1"))

                .andExpect(content().string(containsString("patientFirstName1")))
                .andExpect(content().string(containsString("patientLastName1")))
                .andExpect(content().string(containsString("Note1 Patient 1")));
    }

    @Test
    public void testAddNote() throws Exception {

        NoteBean note = new NoteBean("1", 1L, "Note1 Patient 1");

        this.mockMvc.perform(post("/note/addNote")
                        .contentType(APPLICATION_JSON)
                        .cookie(cookie)
                        .flashAttr("note", note))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/patient/update/1"));
    }

    @Test
    public void testAddNoteForm() throws Exception {

        PatientBean patient = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(1971,1,1), "1111111111", "M", "patientAddress1");
        when(patientProxies.getPatientById(1L, "Bearer test")).thenReturn(patient);

        mockMvc.perform(get("/note/1/addNote").contentType(APPLICATION_JSON).cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(view().name("note/addNote"))
                .andExpect(model().attributeExists("note"))
                .andExpect(model().attributeExists("patientName"))
                .andExpect(model().attributeExists("patientId"))
                .andExpect(model().size(3))
                .andExpect(model().attribute("patientName", "patientFirstName1 patientLastName1"))
                .andExpect(model().attribute("patientId", 1L))

                .andExpect(content().string(containsString("patientFirstName1")))
                .andExpect(content().string(containsString("patientLastName1")));
    }

    @Test
    public void testUpdateNoteById() throws Exception {

        NoteBean note = new NoteBean("1", 1L, "Note1 Patient 1");

        this.mockMvc.perform(post("/note/updateNote")
                        .contentType(APPLICATION_JSON)
                        .cookie(cookie)
                        .flashAttr("note", note))
                .andExpect(status().is(302));
    }

    @Test
    public void testDeleteNoteById() throws Exception {

        this.mockMvc.perform(get("/note/delete/1,1")
                        .contentType(APPLICATION_JSON)
                        .cookie(cookie))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/patient/update/1"));
    }

}
