package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CaseCreateRequest;
import com.kenzie.appserver.controller.model.CaseResponse;
import com.kenzie.appserver.service.CaseService;
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
        return null;
    }

    @PostMapping
    public ResponseEntity<CaseResponse> createCase(@RequestBody CaseCreateRequest createRequest) {
        //replace this with actual code
        return null;
    }
}
