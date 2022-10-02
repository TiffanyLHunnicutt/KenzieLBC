package com.kenzie.appserver.service.model;

import java.util.UUID;

public class Evidence {
    private UUID caseId;
    private UUID evidenceId;
    private String timeStamp;
    private String location;
    private String timeDate;
    private String author;
    private String description;

    public Evidence(UUID caseId, UUID evidenceId, String timeStamp, String location, String timeDate, String author, String description) {
        this.caseId = caseId;
        this.evidenceId = evidenceId;
        this.timeStamp = timeStamp;
        this.location = location;
        this.timeDate = timeDate;
        this.author = author;
        this.description = description;
    }

    public Evidence(UUID caseId) {
        this.caseId = caseId;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getEvidenceId() {
        return evidenceId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getLocation() {
        return location;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}
