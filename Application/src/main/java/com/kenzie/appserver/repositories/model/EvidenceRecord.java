package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

//tati is doing record this week for model
@DynamoDBTable(tableName = "EvidenceRecord")
public class EvidenceRecord {
    public String caseId;
    public String evidenceId;
    public String timeStamp;
    public String location;
    public String TimeDate;
    public String author;
    public String description;

    @DynamoDBHashKey(attributeName = "caseId")
    public String getCaseId() {
        return caseId;
    }

    @DynamoDBHashKey(attributeName = "evidenceId")
    public String getEvidenceId() {
        return evidenceId;
    }

    @DynamoDBAttribute(attributeName = "timeStamp")
    public String getTimeStamp() {
        return timeStamp;
    }

    @DynamoDBAttribute(attributeName = "location")
    public String getLocation() {
        return location;
    }

    @DynamoDBAttribute(attributeName = "TimeDate")
    public String getTimeDate() {
        return TimeDate;
    }

    @DynamoDBAttribute(attributeName = "author")
    public String getAuthor() {
        return author;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }




}
