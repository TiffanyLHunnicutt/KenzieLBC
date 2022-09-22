package com.kenzie.appserver.service.model;

import java.util.List;

public class Case {
    private final String caseId;
    private final String timeStamp;
    private String title;
    private String author;
    private String description;
    private String timeDate;
    private String location;
    private List<String> potentialSuspects;
    private Boolean openCase;

    public Case(String caseId, String location,String timeStamp, String title, String author, String description, String timeDate, List<String> potentialSuspects, Boolean openCase) {
        this.caseId = caseId;
        this.timeStamp = timeStamp;
        this.title = title;
        this.author = author;
        this.description = description;
        this.timeDate = timeDate;
        this.potentialSuspects = potentialSuspects;
        this.openCase = openCase;
        this.location = location;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public List<String> getPotentialSuspects() {
        return potentialSuspects;
    }

    public Boolean getOpenCase() {
        return openCase;
    }

    public String getLocation() {
        return location;
    }
}


