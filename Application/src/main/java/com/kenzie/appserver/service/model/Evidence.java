package com.kenzie.appserver.service.model;

public class Evidence {
    private String caseId;
    private String evidenceId;
    private String timeStamp;
   private String location;
   private String timeDate;
    private String author;
   private String description;

    public Evidence(String caseId, String evidenceId, String timeStamp, String location, String timeDate, String author, String description) {
        this.caseId = caseId;
        this.evidenceId = evidenceId;
        this.timeStamp = timeStamp;
        this.location = location;
        this.timeDate = timeDate;
        this.author = author;
        this.description = description;
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



