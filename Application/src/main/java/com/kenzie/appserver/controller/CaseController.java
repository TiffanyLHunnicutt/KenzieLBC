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
        //replace with actual code
        List<Case> allCases = caseService.findAllOpenCases();
        if (allCases == null || allCases.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<CaseResponse> caseResponses = new ArrayList<>();
        for (Case case3 : allCases) {
            caseResponses.add(caseResponse(case3));
        }
        return ResponseEntity.ok(caseResponses);
    }



    @GetMapping("/{caseId}")
    public ResponseEntity<CaseResponse> getCase(@PathVariable("caseId") String caseId) {
        Case case1 = caseService.findCaseByCaseId(caseId);
        if (case1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(caseResponse(case1));
    }

    @PostMapping
    public ResponseEntity<CaseResponse> createCase(@RequestBody CaseCreateRequest createRequest) {
        //replace this with actual code
        Case case4 = new Case(randomUUID().toString());
        caseService.addNewCase(case4);

        CaseResponse caseResponse1 = new CaseResponse();
        caseResponse1.setTitle(case4.getTitle());
        caseResponse1.setCaseId(case4.getCaseId());
        caseResponse1.setOpenCase(case4.getOpenCase());
        caseResponse1.setLocation(case4.getLocation());
        caseResponse1.setDescription(case4.getDescription());
        caseResponse1.setAuthor(case4.getAuthor());
        caseResponse1.setTimeStamp(case4.getTimeStamp());
        caseResponse1.setPotentialSuspects(case4.getPotentialSuspects());
        caseResponse1.setTimeDate(case4.getTimeDate());
        return ResponseEntity.created(URI.create("/case/" + caseResponse1.getCaseId())).body(caseResponse1);
    }

    @PutMapping("/{caseId}")
    public ResponseEntity<CaseResponse> updateCase(@RequestBody CaseUpdateRequest updateRequest) {
        //replace this with actual code
        Case case5 = new Case(updateRequest.getDescription(),
                updateRequest.getOpenCase(),
                updateRequest.getPotentialSuspects());
        CaseResponse caseResponse1 = caseResponse(case5);
        return ResponseEntity.ok(caseResponse1);
    }

    @DeleteMapping("/{caseId}")
    public ResponseEntity<CaseResponse> deleteCase(@PathVariable("caseId") String caseId) {
        caseService.deleteCase(caseId);
        return ResponseEntity.noContent().build();
    }

    private CaseResponse caseResponse(Case case2) {
        CaseResponse response = new CaseResponse();
        response.setCaseId(case2.getCaseId());
        response.setTimeStamp(case2.getTimeStamp());
        response.setAuthor(case2.getAuthor());
        response.setDescription(case2.getDescription());
        response.setLocation(case2.getLocation());
        response.setTimeDate(case2.getTimeDate());
        response.setPotentialSuspects(case2.getPotentialSuspects());
        response.setTitle(case2.getTitle());
        return response;
    }
}
