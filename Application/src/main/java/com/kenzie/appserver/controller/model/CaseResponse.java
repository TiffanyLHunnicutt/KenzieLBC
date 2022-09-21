package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaseResponse {

        @JsonProperty("caseNumber")
        private String caseNumber;

        @JsonProperty("name")
        private String name;

        @JsonProperty("caseType")
        private String caseType;


        public String getCaseNumber() {
            return caseNumber;
        }

        public void setCaseNumber(String caseNumber) {
            this.caseNumber = caseNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCaseType() {
            return caseType;
        }

        public void setCaseType(String caseType) {
            this.caseType = caseType;
        }
    }

