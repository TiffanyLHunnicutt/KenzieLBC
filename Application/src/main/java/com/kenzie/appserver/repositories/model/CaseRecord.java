package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import net.bytebuddy.matcher.StringSetMatcher;

import java.util.List;

//tati is doing record this week for model
@DynamoDBTable(tableName = "CaseRecord")
public class CaseRecord {
    public String caseId;
    public String timeStamp;
    public String title;
    public String author;
    public String description;
    public String location;
    public String TimeDate;
    public List<String> potentialSuspects;
    public Boolean openCase;

    @DynamoDBRangeKey(attributeName = "caseId")
    public String getCaseId() {
        return caseId;
    }

    @DynamoDBAttribute(attributeName = "timeStamp")
    public String getTimeStamp() {
        return timeStamp;
    }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    @DynamoDBAttribute(attributeName = "author")
    public String getAuthor() {
        return author;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    @DynamoDBAttribute(attributeName = "location")
    public String getLocation() {
        return location;
    }

    @DynamoDBAttribute(attributeName = "TimeDate")
    public String getTimeDate() {
        return TimeDate;
    }

    @DynamoDBAttribute(attributeName = "potentialSuspects")
    public List<String> getPotentialSuspects() {
        return potentialSuspects;
    }

    @DynamoDBAttribute(attributeName = "openCase")
    public Boolean getOpenCase() {
        return openCase;
    }

}
