package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.CaseCreateRequest;
import com.kenzie.appserver.controller.model.CaseUpdateRequest;
import com.kenzie.appserver.service.CaseService;
import com.kenzie.appserver.service.model.Case;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class CaseControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    CaseService caseService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getAllOpenCase_CasesExist() throws Exception{
        // GIVEN
        UUID id = randomUUID();
        String timestamp = LocalDateTime.now().toString();
        String title = mockNeat.strings().valStr();
        String author = mockNeat.strings().valStr();
        String description = mockNeat.strings().valStr();
        String timeDate = mockNeat.strings().valStr();
        String location = mockNeat.strings().valStr();
        List<String> potentialSuspects = new ArrayList<>();

        Case caseToGet = new Case(id, timestamp, title, author, description,
                location, timeDate, potentialSuspects, true);
        Case persistedCase = caseService.addNewCase(caseToGet);

        // WHEN
        mvc.perform(get("/cases/all")
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isOk());
    }

    @Test
    public void getCase_CaseExists() throws Exception{
        // GIVEN
        UUID id = randomUUID();
        String timestamp = LocalDateTime.now().toString();
        String title = mockNeat.strings().valStr();
        String author = mockNeat.strings().valStr();
        String description = mockNeat.strings().valStr();
        String timeDate = mockNeat.strings().valStr();
        String location = mockNeat.strings().valStr();
        List<String> potentialSuspects = new ArrayList<>();

        Case caseToGet = new Case(id, timestamp, title, author, description,
                location, timeDate, potentialSuspects, true);
        Case persistedCase = caseService.addNewCase(caseToGet);

        // WHEN
        mvc.perform(get("/cases/{caseId}", persistedCase.getCaseId().toString())
                        .accept(MediaType.APPLICATION_JSON))
        // THEN
                .andExpect(jsonPath("caseId")
                        .value(is(id.toString())))
                .andExpect(jsonPath("timeStamp")
                        .value(is(timestamp)))
                .andExpect(jsonPath("title")
                        .value(is(title)))
                .andExpect(jsonPath("author")
                        .value(is(author)))
                .andExpect(jsonPath("description")
                        .value(is(description)))
                .andExpect(jsonPath("location")
                        .value(is(location)))
                .andExpect(jsonPath("timeDate")
                        .value(is(timeDate)))
                .andExpect(jsonPath("potentialSuspects")
                        .value(is(potentialSuspects)))
                .andExpect(jsonPath("openCase")
                        .value(is(true)))
                .andExpect(status().isOk());
    }

    @Test
    public void getCase_CaseDoesNotExist() throws Exception {
        // GIVEN
        String id = UUID.randomUUID().toString();

        // WHEN
        mvc.perform(get("/cases/{caseId}", id)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNotFound());
    }

    @Test
    public void createCase_CreateSuccessful() throws Exception {
        // GIVEN
        String title = mockNeat.strings().valStr();
        String author = mockNeat.strings().valStr();
        String description = mockNeat.strings().valStr();
        String location = mockNeat.strings().valStr();
        String timeDate = mockNeat.strings().valStr();
        List<String> potentialSuspects = new ArrayList<>();

        potentialSuspects.add(mockNeat.strings().valStr());
        potentialSuspects.add(mockNeat.strings().valStr());

        CaseCreateRequest createRequest = new CaseCreateRequest();
        createRequest.setTitle(title);
        createRequest.setAuthor(author);
        createRequest.setDescription(description);
        createRequest.setLocation(location);
        createRequest.setTimeDate(timeDate);
        createRequest.setPotentialSuspects(potentialSuspects);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/cases")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createRequest)))
                // THEN
                .andExpect(jsonPath("caseId")
                        .exists())
                .andExpect(jsonPath("timeStamp")
                        .exists())
                .andExpect(jsonPath("title")
                        .value(is(title)))
                .andExpect(jsonPath("author")
                        .value(is(author)))
                .andExpect(jsonPath("description")
                        .value(is(description)))
                .andExpect(jsonPath("location")
                        .value(is(location)))
                .andExpect(jsonPath("timeDate")
                        .value(is(timeDate)))
                .andExpect(jsonPath("potentialSuspects")
                        .value(is(potentialSuspects)))
                .andExpect(jsonPath("openCase")
                        .value(is(true)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createCase_OnlyRequiredFields_CreateSuccessful() throws Exception {
        // GIVEN
        String title = mockNeat.strings().valStr();
        String description = mockNeat.strings().valStr();
        String location = mockNeat.strings().valStr();
        String timeDate = mockNeat.strings().valStr();
        List<String> potentialSuspects = new ArrayList<>();

        CaseCreateRequest createRequest = new CaseCreateRequest();
        createRequest.setTitle(title);
        createRequest.setDescription(description);
        createRequest.setLocation(location);
        createRequest.setTimeDate(timeDate);
        createRequest.setPotentialSuspects(potentialSuspects);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/cases")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createRequest)))
                // THEN
                .andExpect(jsonPath("caseId")
                        .exists())
                .andExpect(jsonPath("timeStamp")
                        .exists())
                .andExpect(jsonPath("title")
                        .value(is(title)))
                .andExpect(jsonPath("description")
                        .value(is(description)))
                .andExpect(jsonPath("location")
                        .value(is(location)))
                .andExpect(jsonPath("timeDate")
                        .value(is(timeDate)))
                .andExpect(jsonPath("potentialSuspects")
                        .value(is(potentialSuspects)))
                .andExpect(jsonPath("openCase")
                        .value(is(true)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCase_PutSuccessful() throws Exception{
        // GIVEN
        UUID id = randomUUID();
        String timestamp = LocalDateTime.now().toString();
        String title = mockNeat.strings().valStr();
        String author = mockNeat.strings().valStr();
        String description = mockNeat.strings().valStr();
        String timeDate = mockNeat.strings().valStr();
        String location = mockNeat.strings().valStr();
        List<String> potentialSuspects = new ArrayList<>();

        Case addCase = new Case(id, timestamp, title, author, description,
                location, timeDate, potentialSuspects, true);
        Case persistedCase = caseService.addNewCase(addCase);

        String newDescription = mockNeat.strings().valStr();
        List<String> newSuspects = new ArrayList<>();
        newSuspects.add(mockNeat.strings().valStr());
        newSuspects.add(mockNeat.strings().valStr());

        CaseUpdateRequest updateRequest = new CaseUpdateRequest();
        updateRequest.setDescription(newDescription);
        updateRequest.setPotentialSuspects(newSuspects);
        updateRequest.setOpenCase(true);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(put("/cases//{caseId}", persistedCase.getCaseId().toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateRequest)))
                // THEN
                .andExpect(jsonPath("caseId")
                        .value(is(id.toString())))
                .andExpect(jsonPath("timeStamp")
                        .value(is(timestamp)))
                .andExpect(jsonPath("title")
                        .value(is(title)))
                .andExpect(jsonPath("author")
                        .value(is(author)))
                .andExpect(jsonPath("description")
                        .value(is(newDescription)))
                .andExpect(jsonPath("location")
                        .value(is(location)))
                .andExpect(jsonPath("timeDate")
                        .value(is(timeDate)))
                .andExpect(jsonPath("potentialSuspects")
                        .value(is(newSuspects)))
                .andExpect(jsonPath("openCase")
                        .value(is(true)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCase_CaseNotFound() throws Exception{
        // GIVEN
        String id = UUID.randomUUID().toString();

        CaseUpdateRequest updateRequest = new CaseUpdateRequest();
        updateRequest.setDescription(mockNeat.strings().valStr());
        updateRequest.setPotentialSuspects(new ArrayList<>());
        updateRequest.setOpenCase(true);

        // WHEN
        mvc.perform(put("/cases//{caseId}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateRequest)))
                // THEN
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCase_DeleteSuccessful() throws Exception {
        // GIVEN
        UUID id = randomUUID();
        String timestamp = LocalDateTime.now().toString();
        String title = mockNeat.strings().valStr();
        String author = mockNeat.strings().valStr();
        String description = mockNeat.strings().valStr();
        String timeDate = mockNeat.strings().valStr();
        String location = mockNeat.strings().valStr();
        List<String> potentialSuspects = new ArrayList<>();

        Case addCase = new Case(id, timestamp, title, author, description,
                location, timeDate, potentialSuspects, true);
        Case persistedCase = caseService.addNewCase(addCase);

        // WHEN
        mvc.perform(delete("/cases/{caseId}", persistedCase.getCaseId())
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNoContent());
        assertThat(caseService.findCaseByCaseId(id.toString())).isNull();
    }
}
