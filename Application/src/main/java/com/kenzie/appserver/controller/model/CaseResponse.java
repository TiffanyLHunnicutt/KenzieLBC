package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaseResponse {

    @JsonProperty("caseId")
    public String caseId;

    @JsonProperty("timeStamp")
    public String timeStamp;

    @JsonProperty("title")
    public String title;

    @JsonProperty("author")
    public String author;

    @JsonProperty("description")
    public String description;

    @JsonProperty("location")
    public String location;

    @JsonProperty("timeDate")
    public String timeDate;

    @JsonProperty("potentialSuspects")
    public List<String> potentialSuspects;

    @JsonProperty("openCase")
    public Boolean openCase;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public List<String> getPotentialSuspects() {
        return potentialSuspects;
    }

    public void setPotentialSuspects(List<String> potentialSuspects) {
        this.potentialSuspects = potentialSuspects;
    }

    public Boolean getOpenCase() {
        return openCase;
    }

    public void setOpenCase(Boolean openCase) {
        this.openCase = openCase;
    }
}