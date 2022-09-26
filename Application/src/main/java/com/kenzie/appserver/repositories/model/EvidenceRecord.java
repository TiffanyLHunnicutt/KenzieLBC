package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

//tati is doing record this week for model
@DynamoDBTable(tableName = "EvidenceRecord")
public class EvidenceRecord {
    public String caseId;
    public String evidenceId;
    public String timeStamp;
    public String location;
    public String timeDate;
    public String author;
    public String description;


    @DynamoDBHashKey(attributeName = "caseId")
    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    @DynamoDBRangeKey(attributeName = "evidenceId")
    public String getEvidenceId() {
        return evidenceId;
    }
    public void setEvidenceId(String evidenceId) {
        this.evidenceId = evidenceId;
    }

    @DynamoDBAttribute(attributeName = "timeStamp")
    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
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
}
