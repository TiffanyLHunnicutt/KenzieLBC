package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CaseUpdateRequest {

    @JsonProperty("description")
    private String description;

    @JsonProperty("potentialSuspects")
    private List<String> potentialSuspects;

    @JsonProperty("openCase")
    private Boolean openCase;

    @JsonProperty("caseId")
    private String caseId;

    @JsonProperty("timeStamp")
    private String timeStamp;

    @JsonProperty("timeDate")
    private String timeDate;

    @JsonProperty("location")
    private String location;

    @JsonProperty("author")
    private String author;

    @JsonProperty("title")
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
