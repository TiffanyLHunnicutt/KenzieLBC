package com.kenzie.appserver.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.EvidenceCreateRequest;
import com.kenzie.appserver.controller.model.EvidenceResponse;
import com.kenzie.appserver.service.EvidenceService;
import com.kenzie.appserver.service.model.Evidence;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class EvidenceControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    EvidenceService evidenceService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createEvidence_CreateSuccessful() throws Exception {
        // GIVEN
        String caseId = randomUUID().toString();
        String author = mockNeat.strings().valStr();
        String description = mockNeat.strings().valStr();
        String location = mockNeat.strings().valStr();
        String timeDate = mockNeat.strings().valStr();

        EvidenceCreateRequest createRequest = new EvidenceCreateRequest();
        createRequest.setAuthor(author);
        createRequest.setDescription(description);
        createRequest.setLocation(location);
        createRequest.setTimeDate(timeDate);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/cases/{caseId}/evidence", caseId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createRequest)))
                // THEN
                .andExpect(jsonPath("timeStamp")
                        .exists())
                .andExpect(jsonPath("evidenceId")
                        .exists())
                .andExpect(jsonPath("caseId")
                        .value(is(caseId)))
                .andExpect(jsonPath("author")
                        .value(is(author)))
                .andExpect(jsonPath("description")
                        .value(is(description)))
                .andExpect(jsonPath("location")
                        .value(is(location)))
                .andExpect(jsonPath("timeDate")
                        .value(is(timeDate)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createEvidence_OnlyRequiredFields_CreateSuccessful() throws Exception{
        // GIVEN
        String caseId = randomUUID().toString();
        String description = mockNeat.strings().valStr();
        String location = mockNeat.strings().valStr();
        String timeDate = mockNeat.strings().valStr();

        EvidenceCreateRequest createRequest = new EvidenceCreateRequest();
        createRequest.setDescription(description);
        createRequest.setLocation(location);
        createRequest.setTimeDate(timeDate);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/cases/{caseId}/evidence", caseId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createRequest)))
                // THEN
                .andExpect(jsonPath("timeStamp")
                        .exists())
                .andExpect(jsonPath("evidenceId")
                        .exists())
                .andExpect(jsonPath("caseId")
                        .value(is(caseId)))
                .andExpect(jsonPath("description")
                        .value(is(description)))
                .andExpect(jsonPath("location")
                        .value(is(location)))
                .andExpect(jsonPath("timeDate")
                        .value(is(timeDate)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getEvidenceById_EvidenceExists() throws Exception {
        // GIVEN
        String caseId = randomUUID().toString();
        String evidenceId = randomUUID().toString();
        String timestamp = LocalDateTime.now().toString();
        String author = mockNeat.strings().valStr();
        String description = mockNeat.strings().valStr();
        String timeDate = mockNeat.strings().valStr();
        String location = mockNeat.strings().valStr();

        Evidence evidenceToGet = new Evidence(UUID.fromString(caseId), UUID.fromString(evidenceId),
                timestamp, location, timeDate, author, description);
        Evidence persistedEvidence = evidenceService.addNewEvidence(evidenceToGet);

        // WHEN
        mvc.perform(get("/cases/{caseId}/evidence/{evidenceId}",
                        persistedEvidence.getCaseId().toString(),
                        persistedEvidence.getEvidenceId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(jsonPath("caseId")
                        .value(is(caseId)))
                .andExpect(jsonPath("evidenceId")
                        .value(is(evidenceId)))
                .andExpect(jsonPath("timeStamp")
                        .value(is(timestamp)))
                .andExpect(jsonPath("author")
                        .value(is(author)))
                .andExpect(jsonPath("description")
                        .value(is(description)))
                .andExpect(jsonPath("location")
                        .value(is(location)))
                .andExpect(jsonPath("timeDate")
                        .value(is(timeDate)))
                .andExpect(status().isOk());
    }

    @Test
    public void getEvidence_EvidenceDoesNotExist() throws Exception {
        // GIVEN
        String caseId = randomUUID().toString();
        String evidenceId = randomUUID().toString();

        // WHEN
        mvc.perform(get("/cases/{caseId}/evidence/{evidenceId}", caseId, evidenceId)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllEvidenceForCase_OnlyReturnsEvidenceForCase() throws Exception {
        // GIVEN
        String caseIdToGet = randomUUID().toString();

        String evidenceId = randomUUID().toString();
        String timestamp = LocalDateTime.now().toString();
        String author = mockNeat.strings().valStr();
        String description = mockNeat.strings().valStr();
        String timeDate = mockNeat.strings().valStr();
        String location = mockNeat.strings().valStr();

        String evidenceId1 = randomUUID().toString();
        String timestamp1 = LocalDateTime.now().toString();
        String author1 = mockNeat.strings().valStr();
        String description1 = mockNeat.strings().valStr();
        String timeDate1 = mockNeat.strings().valStr();
        String location1 = mockNeat.strings().valStr();

        String caseIdNotIncluded = randomUUID().toString();
        String evidenceId2 = randomUUID().toString();
        String timestamp2 = LocalDateTime.now().toString();
        String author2 = mockNeat.strings().valStr();
        String description2 = mockNeat.strings().valStr();
        String timeDate2 = mockNeat.strings().valStr();
        String location2 = mockNeat.strings().valStr();

        Evidence evidenceForCase = new Evidence(UUID.fromString(caseIdToGet), UUID.fromString(evidenceId),
                timestamp, location, timeDate, author, description);
        Evidence evidenceForCase1 = new Evidence(UUID.fromString(caseIdToGet), UUID.fromString(evidenceId1),
                timestamp1, location1, timeDate1, author1, description1);
        Evidence evidenceNotForCase = new Evidence(UUID.fromString(caseIdNotIncluded), UUID.fromString(evidenceId2),
                timestamp2, location2, timeDate2, author2, description2);

        Evidence persistedEvidenceForCase = evidenceService.addNewEvidence(evidenceForCase);
        Evidence persistedEvidenceForCase1 = evidenceService.addNewEvidence(evidenceForCase1);
        Evidence persistedEvidenceNotForCase = evidenceService.addNewEvidence(evidenceNotForCase);

        // WHEN
        String response = mvc.perform(get("/cases/{caseId}/evidence/all",
                        persistedEvidenceForCase.getCaseId().toString())
                        .accept(MediaType.APPLICATION_JSON))
        // THEN
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<EvidenceResponse> resultList= mapper.readValue(response, new TypeReference<List<EvidenceResponse>>() {});

        assertThat(resultList).isNotNull();
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(resultList.get(0).getCaseId()).isEqualTo(caseIdToGet);
        assertThat(resultList.get(1).getCaseId()).isEqualTo(caseIdToGet);
    }

    @Test
    public void getAllEvidenceForCase_NoEvidenceForCase() throws Exception {
        // GIVEN
        String caseIdWithNoEvidence = randomUUID().toString();

        String caseId1 = randomUUID().toString();
        String evidenceId1 = randomUUID().toString();
        String timestamp1 = LocalDateTime.now().toString();
        String author1 = mockNeat.strings().valStr();
        String description1 = mockNeat.strings().valStr();
        String timeDate1 = mockNeat.strings().valStr();
        String location1 = mockNeat.strings().valStr();

        String caseId2 = randomUUID().toString();
        String evidenceId2 = randomUUID().toString();
        String timestamp2 = LocalDateTime.now().toString();
        String author2 = mockNeat.strings().valStr();
        String description2 = mockNeat.strings().valStr();
        String timeDate2 = mockNeat.strings().valStr();
        String location2 = mockNeat.strings().valStr();

        Evidence evidence1 = new Evidence(UUID.fromString(caseId1), UUID.fromString(evidenceId1),
                timestamp1, location1, timeDate1, author1, description1);
        Evidence evidence2 = new Evidence(UUID.fromString(caseId2), UUID.fromString(evidenceId2),
                timestamp2, location2, timeDate2, author2, description2);

        evidenceService.addNewEvidence(evidence1);
        evidenceService.addNewEvidence(evidence2);

        // WHEN
        mvc.perform(get("/cases/{caseId}/evidence/all", caseIdWithNoEvidence)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNoContent());
    }
}
