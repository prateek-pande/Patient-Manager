package com.example.prateekpande.patientmanager.model;

import java.util.Date;

/**
 * Created by prateek.pande on 6/28/2016.
 */

public class Appointment {

    private String patientName;
    private Long patientContact;
    private String patientGender;
    private int patientAge;
    private String regarding;
    private Date appointmentDateTime;
    private String clinicLocation;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getPatientContact() {
        return patientContact;
    }

    public void setPatientContact(Long patientContact) {
        this.patientContact = patientContact;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getRegarding() {
        return regarding;
    }

    public void setRegarding(String regarding) {
        this.regarding = regarding;
    }

    public Date getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(Date appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public String getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(String clinicLocation) {
        this.clinicLocation = clinicLocation;
    }
}
