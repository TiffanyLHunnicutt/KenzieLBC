package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName = "CaseRecord")
public class CaseRecord {
    public String caseId;
    public String timeStamp;
    public String title;
    public String author;
    public String description;
    public String location;
    public String timeDate;
    public List<String> potentialSuspects;
    public Boolean openCase;

    @DynamoDBHashKey(attributeName = "caseId")
    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    @DynamoDBAttribute(attributeName = "timeStamp")
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @DynamoDBAttribute(attributeName = "timeDate")
    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    @DynamoDBAttribute(attributeName = "potentialSuspects")
    public List<String> getPotentialSuspects() {
        return new ArrayList<>(potentialSuspects);
    }

    public void setPotentialSuspects(List<String> potentialSuspects) {
        this.potentialSuspects = new ArrayList<>(potentialSuspects);
    }

    @DynamoDBAttribute(attributeName = "openCase")
    public Boolean getOpenCase() {
        return openCase;
    }

    public void setOpenCase(Boolean openCase) {
        this.openCase = openCase;
    }
}
