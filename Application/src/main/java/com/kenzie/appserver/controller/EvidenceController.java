package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CaseResponse;
import com.kenzie.appserver.controller.model.EvidenceCreateRequest;
import com.kenzie.appserver.controller.model.EvidenceResponse;
import com.kenzie.appserver.service.EvidenceService;
import com.kenzie.appserver.service.model.Case;
import com.kenzie.appserver.service.model.Evidence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

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
        List<Evidence> allEvidence = evidenceService.findAllEvidenceForCase(caseId);
        if (allEvidence == null || allEvidence.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<EvidenceResponse> evidenceResponses = new ArrayList<>();
        for (Evidence evidence : allEvidence) {
            evidenceResponses.add(evidenceResponse(evidence));
        }

        return ResponseEntity.ok(evidenceResponses);
    }

    @GetMapping("/{evidenceId}")
    public ResponseEntity<EvidenceResponse> getEvidenceById(@PathVariable("caseId") String caseId,
                                                            @PathVariable("evidenceId") String evidenceId) {
        //replace with actual code
        Evidence evidence1 = evidenceService.findEvidenceByEvidenceId(caseId, evidenceId);
        if (evidence1 == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evidenceResponse(evidence1));
    }

    @PostMapping
    public ResponseEntity<EvidenceResponse> createEvidence(@PathVariable("caseId") String caseId,
                                                           @RequestBody EvidenceCreateRequest createRequest) {
        //replace with actual code
        Evidence evidence2 = new Evidence(UUID.randomUUID());
        evidenceService.addNewEvidence(evidence2);

        EvidenceResponse evidenceResponse1 = new EvidenceResponse();
        evidenceResponse1.setCaseId(evidence2.getCaseId());
        evidenceResponse1.setEvidenceId(evidence2.getEvidenceId());
        evidenceResponse1.setAuthor(evidence2.getAuthor());
        evidenceResponse1.setDescription(evidence2.getDescription());
        evidenceResponse1.setLocation(evidence2.getLocation());
        evidenceResponse1.setTimeDate(evidence2.getTimeDate());
        evidenceResponse1.setTimeStamp(evidence2.getTimeStamp());

        return ResponseEntity.created(URI.create("/evidence/" + evidenceResponse1.getCaseId())).body(evidenceResponse1);
    }
    private EvidenceResponse evidenceResponse(Evidence evidence2) {
        EvidenceResponse response = new EvidenceResponse();
        response.setCaseId(evidence2.getCaseId());
        response.setTimeStamp(evidence2.getTimeStamp());
        response.setAuthor(evidence2.getAuthor());
        response.setDescription(evidence2.getDescription());
        response.setLocation(evidence2.getLocation());
        response.setTimeDate(evidence2.getTimeDate());
        response.setEvidenceId(evidence2.getEvidenceId());

        return response;
    }
}
