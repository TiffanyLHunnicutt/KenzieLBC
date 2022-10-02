package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CaseCreateRequest;
import com.kenzie.appserver.controller.model.CaseResponse;
import com.kenzie.appserver.controller.model.CaseUpdateRequest;
import com.kenzie.appserver.controller.model.ExampleResponse;
import com.kenzie.appserver.service.CaseService;
import com.kenzie.appserver.service.model.Case;
import com.kenzie.appserver.service.model.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/cases")
public class CaseController {

    private CaseService caseService;

    CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CaseResponse>> getAllOpenCases() {
        List<Case> allCases = caseService.findAllOpenCases();
        if (allCases == null || allCases.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<CaseResponse> caseResponses = new ArrayList<>();
        for (Case cased : allCases) {
            caseResponses.add(caseResponse(cased));
        }

        return ResponseEntity.ok(caseResponses);
    }

    @GetMapping("/{caseId}")
    public ResponseEntity<CaseResponse> getCase(@PathVariable("caseId") String caseId) {
        Case caseToGet = caseService.findCaseByCaseId(caseId);
        if (caseToGet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(caseResponse(caseToGet));
    }

    @PostMapping
    public ResponseEntity<CaseResponse> createCase(@RequestBody CaseCreateRequest createRequest) {
        Case caseToAdd = new Case(randomUUID(),
                LocalDateTime.now().toString(),
                createRequest.getTitle(),
                createRequest.getAuthor(),
                createRequest.getDescription(),
                createRequest.getTimeDate(),
                createRequest.getLocation(),
                createRequest.getPotentialSuspects(),
                true);
        caseService.addNewCase(caseToAdd);

        CaseResponse caseResponse = caseResponse(caseToAdd);
        return ResponseEntity.created(URI.create("/cases/" + caseResponse.getCaseId())).body(caseResponse);
    }

    @PutMapping("/{caseId}")
    public ResponseEntity<CaseResponse> updateCase(@PathVariable("caseId") String caseId,
                                                   @RequestBody CaseUpdateRequest updateRequest) {
        Case grabCase = caseService.findCaseByCaseId(caseId);
        if(grabCase == null) {
            return ResponseEntity.notFound().build();
        }

        Case caseUpdate = new Case(grabCase.getCaseId(),
                grabCase.getTimeStamp(),
                grabCase.getTitle(),
                grabCase.getAuthor(),
                updateRequest.getDescription(),
                grabCase.getTimeDate(),
                grabCase.getLocation(),
                updateRequest.getPotentialSuspects(),
                updateRequest.getOpenCase());

        CaseResponse caseResponse = caseResponse(caseUpdate);
        return ResponseEntity.ok(caseResponse);
    }

    @DeleteMapping("/{caseId}")
    public ResponseEntity deleteCase(@PathVariable("caseId") String caseId) {
        caseService.deleteCase(caseId);
        return ResponseEntity.noContent().build();
    }

    private CaseResponse caseResponse(Case caseToResponse) {
        CaseResponse response = new CaseResponse();
        response.setCaseId(caseToResponse.getCaseId().toString());
        response.setTimeStamp(caseToResponse.getTimeStamp());
        response.setTitle(caseToResponse.getTitle());
        response.setAuthor(caseToResponse.getAuthor());
        response.setDescription(caseToResponse.getDescription());
        response.setLocation(caseToResponse.getLocation());
        response.setTimeDate(caseToResponse.getTimeDate());
        response.setPotentialSuspects(caseToResponse.getPotentialSuspects());
        response.setOpenCase(caseToResponse.getOpenCase());
        return response;
    }
}
