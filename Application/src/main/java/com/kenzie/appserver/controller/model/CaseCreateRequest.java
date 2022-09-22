package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

//Hello Team members! As you can see, this class does not have caseId, timestamp, or openCase
//because those fields aren't input from the user; they are generated by the service upon creation
//I hope this comment helped! Talk to you all later (the rest of the class is just getters and setters)
//oh Also the author and potentialSuspects fields can be empty, everything else is required. elise
public class CaseCreateRequest {

    @NotEmpty
    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @NotEmpty
    @JsonProperty("description")
    private String description;

    @NotEmpty
    @JsonProperty("location")
    private String location;

    @NotEmpty
    @JsonProperty("timeDate")
    private String timeDate;

    @JsonProperty("potentialSuspects")
    private List<String> potentialSuspects;

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
}
