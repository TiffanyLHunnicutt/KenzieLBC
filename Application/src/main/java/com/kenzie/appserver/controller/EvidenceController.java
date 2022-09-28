package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.EvidenceCreateRequest;
import com.kenzie.appserver.controller.model.EvidenceResponse;
import com.kenzie.appserver.service.EvidenceService;
import com.kenzie.appserver.service.model.Evidence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cases/{caseId}/evidence")
public class EvidenceController {

    private EvidenceService evidenceService;

    EvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EvidenceResponse>> getAllEvidenceForCase(@PathVariable("caseId") String caseId) {
        //replace with actual code
        return null;
    }

    @GetMapping("/{evidenceId}")
    public ResponseEntity<EvidenceResponse> getEvidenceById(@PathVariable("caseId") String caseId,
                                                            @PathVariable("evidenceId") String evidenceId) {
        //replace with actual code
        return null;
    }

    @PostMapping
    public ResponseEntity<EvidenceResponse> createEvidence(@PathVariable("caseId") String caseId,
                                                           @RequestBody EvidenceCreateRequest createRequest) {
        //replace with actual code
        return null;
    }
}
