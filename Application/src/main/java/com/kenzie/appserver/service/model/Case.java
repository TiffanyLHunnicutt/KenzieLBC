package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Case {
    private final UUID caseId;
    private final String timeStamp;
    private final String title;
    private final String author;
    private final String description;
    private final String timeDate;
    private final String location;
    private final List<String> potentialSuspects;
    private final Boolean openCase;

    public Case(UUID caseId, String timeStamp, String title, String author, String description,
                String location, String timeDate, List<String> potentialSuspects, Boolean openCase) {
        this.caseId = caseId;
        this.timeStamp = timeStamp;
        this.title = title;
        this.author = author;
        this.description = description;
        this.timeDate = timeDate;
        this.potentialSuspects = new ArrayList<>(potentialSuspects);
        this.openCase = openCase;
        this.location = location;
    }

    public UUID getCaseId() {
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
