package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.EvidenceRepository;
import com.kenzie.appserver.service.model.Evidence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvidenceService {
    private EvidenceRepository evidenceRepository;

    public EvidenceService(EvidenceRepository evidenceRepository) {
        this.evidenceRepository = evidenceRepository;
    }

    public List<Evidence> findAllEvidenceForCase(String caseId) {
        return new ArrayList<>();
    }

    public Evidence findEvidenceByEvidenceId(String caseId, String evidenceId) {
        return null;
    }

    public Evidence addNewEvidence(Evidence evidence) {
        return null;
    }
}
