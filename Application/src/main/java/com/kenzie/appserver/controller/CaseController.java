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

import java.util.List;

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
        return null;
    }

    @GetMapping("/{caseId}")
    public ResponseEntity<CaseResponse> getCaseById(@PathVariable("caseId") String caseId) {
        //replace with actual code
        Case case = caseService.findCaseByCaseId(caseId);
        if (case == null) {
            return ResponseEntity.notFound().build();
        }

        CaseResponse caseResponse = new CaseResponse();
        caseResponse.setCaseId(caseResponse.getCaseId());
        caseResponse.setTimeStamp(caseResponse.getTimeStamp());
        caseResponse.setAuthor(caseResponse.getAuthor());
        caseResponse.setDescription(caseResponse.getDescription());
        caseResponse.setLocation(caseResponse.getLocation());
        caseResponse.setTimeDate(caseResponse.getTimeDate());
        caseResponse.setPotentialSuspects(caseResponse.getPotentialSuspects());
        caseResponse.setTitle(caseResponse.getTitle());
        return ResponseEntity.ok(caseResponse);
    }

    @PostMapping
    public ResponseEntity<CaseResponse> createCase(@RequestBody CaseCreateRequest createRequest) {
        //replace this with actual code
        return null;
    }

    @PutMapping("/{caseId}")
    public ResponseEntity<CaseResponse> updateCase(@PathVariable("caseId") String caseId,
                                                   @RequestBody CaseUpdateRequest updateRequest) {
        //replace this with actual code
        return null;
    }
}
