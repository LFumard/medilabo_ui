package com.lfumard.medilabo_ui.beans;

import java.time.LocalDateTime;

public class NoteBean {

    private String id;
    private long patientId;
    private LocalDateTime date;
    private String patientNote;

    public NoteBean(String id, long patientId, String patientNote) {
        this.id = id;
        this.patientId = patientId;
        //this.date = date;
        this.patientNote = patientNote;
    }

    public NoteBean() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPatientNote() {
        return patientNote;
    }

    public void setPatientNote(String patientNote) {
        this.patientNote = patientNote;
    }

    @Override
    public String toString() {
        return "NoteBean{" +
                "id='" + id + '\'' +
                ", patientId=" + patientId +
                ", date=" + date +
                ", patientNote='" + patientNote + '\'' +
                '}';
    }
}
