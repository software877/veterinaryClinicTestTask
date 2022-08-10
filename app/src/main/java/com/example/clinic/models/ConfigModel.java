package com.example.clinic.models;

public class ConfigModel {

    private Boolean isChatEnabled;
    private Boolean isCallEnabled;
    private String workHours;

    public ConfigModel(Boolean isChatEnabled, Boolean isCallEnabled, String workHours) {
        this.isCallEnabled = isCallEnabled;
        this.isChatEnabled = isChatEnabled;
        this.workHours = workHours;
    }

    public Boolean getCallEnabled() {
        return isCallEnabled;
    }

    public Boolean getChatEnabled() {
        return isChatEnabled;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setCallEnabled(Boolean callEnabled) {
        isCallEnabled = callEnabled;
    }

    public void setChatEnabled(Boolean chatEnabled) {
        isChatEnabled = chatEnabled;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }
}
