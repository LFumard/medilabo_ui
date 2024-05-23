package com.lfumard.medilabo_ui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lfumard.medilabo_ui.beans.NoteBean;
import com.lfumard.medilabo_ui.beans.PatientBean;
import com.lfumard.medilabo_ui.proxies.AssessmentProxies;
import com.lfumard.medilabo_ui.proxies.NoteProxies;
import com.lfumard.medilabo_ui.proxies.PatientProxies;
import com.lfumard.medilabo_ui.service.AssessmentService;
import com.lfumard.medilabo_ui.service.NoteService;
import com.lfumard.medilabo_ui.service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import jakarta.servlet.http.Cookie;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.containsString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PatientService patientService;

    @MockBean
    private AssessmentProxies assessmentProxies;

    @MockBean
    private NoteProxies noteProxies;

    @MockBean
    private PatientProxies patientProxies;

    private List<PatientBean> patientList;

    Cookie cookie = new Cookie("medilabo", "test");

    @Mock
    private HttpServletRequest request;
    @Test
    public void testHome() throws Exception {

        patientList = new ArrayList<>();
        patientList.add(new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(1971,1,1), "M", "patientAddress1", "1111111111"));
        patientList.add(new PatientBean(2L, "patientFirstName2", "patientLastName2", LocalDate.of(1972,2,2), "F", "patientAddress2", "2222222222"));
        when(patientProxies.findAll("")).thenReturn(patientList);

        this.mockMvc.perform(get("/patient/list").contentType(APPLICATION_JSON).cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/list"))
                .andExpect(model().attributeExists("patientList"))
                .andExpect(model().size(1));
    }

    @Test
    public void testNewPatient() throws Exception {

        this.mockMvc.perform(get("/patient/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/add"))
                .andExpect(model().attributeExists("patient"))
                .andExpect(model().size(1));

    }

    @Test
    public void testAddValidationPatient() throws Exception {

        PatientBean patientToAdd = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(1971,1,1), "1111111111", "M", "patientAddress1");
        patientList = new ArrayList<>();
        patientList.add(patientToAdd);

        when(patientProxies.addPatient(patientToAdd, "")).thenReturn(patientList);

        String strContent = objectMapper.writeValueAsString(patientToAdd);
        this.mockMvc.perform(post("/patient/validate")
                .contentType(APPLICATION_JSON)
                .content(strContent).cookie(cookie))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/patient/list"));
    }

    @Test
    public void testShowUpdatePatientForm() throws Exception {

        PatientBean patient = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(1971,1,1), "1111111111", "M", "patientAddress1");
        doReturn(patient).when(patientProxies).getPatientById(1L, "Bearer test");

        List<NoteBean> noteListAll = new ArrayList<>();
        noteListAll.add(new NoteBean("1", 1L, "Note1 Patient 1"));
        noteListAll.add(new NoteBean("2", 1L, "Note2 Patient 1"));
        given(noteProxies.getNotesByPatId(1L, "Bearer test")).willReturn(noteListAll);

        String assessment = "Assessment Test";
        given(assessmentProxies.getAssessment(1L, "Bearer test")).willReturn(assessment);

        this.mockMvc.perform(get("/patient/update/1")
                        .contentType(APPLICATION_JSON).cookie(cookie)
                        .header("Authorization", "test"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/update"))
                .andExpect(model().attributeExists("patient"))
                .andExpect(model().attributeExists("note"))
                .andExpect(model().attributeExists("assessment"))
                .andExpect(model().size(3))
                .andExpect(model().attribute("patient", patient))
                .andExpect(model().attribute("note", noteListAll))
                .andExpect(model().attribute("assessment", assessment))

                .andExpect(content().string(containsString("patientFirstName1")))
                .andExpect(content().string(containsString("patientLastName1")))
                .andExpect(content().string(containsString("1971-01-01")))
                .andExpect(content().string(containsString("M")))
                .andExpect(content().string(containsString("patientAddress1")))
                .andExpect(content().string(containsString("1111111111")))

                .andExpect(content().string(containsString("Note1 Patient 1")))
                .andExpect(content().string(containsString("Note2 Patient 1")))

                .andExpect(content().string(containsString("Assessment Test")));
    }

    @Test
    public void testUpdatePatient() throws Exception {

        PatientBean patientToUpdate = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(1971,1,1), "1111111111", "M", "patientAddress1");
        patientList = new ArrayList<>();
        patientList.add(patientToUpdate);
        when(patientProxies.updatePatient(1L, patientToUpdate, "")).thenReturn(patientList);

        String strContent = objectMapper.writeValueAsString(patientToUpdate);
        this.mockMvc.perform(post("/patient/update/1")
                        .contentType(APPLICATION_JSON)
                        .content(strContent).cookie(cookie))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/patient/list"));
    }

    @Test
    public void testDeletePatientById() throws Exception {

       this.mockMvc.perform(get("/patient/delete/1").cookie(cookie))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/patient/list"));

        verify(patientProxies, times(1)).deletePatientById(anyLong(), eq("Bearer test"));
        verify(noteProxies, times(1)).deleteNoteByPatientId(anyLong(), eq("Bearer test"));
    }
}
