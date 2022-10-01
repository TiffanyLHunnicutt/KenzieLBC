package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.EvidenceRepository;
import com.kenzie.appserver.repositories.model.EvidenceRecord;
import com.kenzie.appserver.service.model.Evidence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EvidenceService {
    private EvidenceRepository evidenceRepository;

    public EvidenceService(EvidenceRepository evidenceRepository) {
        this.evidenceRepository = evidenceRepository;
    }

    public List<Evidence> findAllEvidenceForCase(String caseId) {
        List<Evidence> evidence = new ArrayList<>();

        Iterable<EvidenceRecord> evidenceRecordIterable = evidenceRepository.findAll();
        for(EvidenceRecord newRecord : evidenceRecordIterable) {
            evidence.add(new Evidence(UUID.fromString(newRecord.getCaseId()),
                    UUID.fromString(newRecord.getEvidenceId()),
                    newRecord.getTimeStamp(),
                    newRecord.getLocation(),
                    newRecord.getTimeDate(),
                    newRecord.getAuthor(),
                    newRecord.getDescription()));

        }
        return evidence;
    }

    public Evidence findEvidenceByEvidenceId(String caseId, String evidenceId) {
        Evidence evidenceFromRepository = evidenceRepository.findById(caseId)
                .map(evidence->new Evidence(UUID.fromString(evidence.getCaseId())))
                .orElse(null);

        return evidenceFromRepository;
    }

    public Evidence addNewEvidence(Evidence evidenceToAdd) {
        EvidenceRecord evidenceRecord = new EvidenceRecord();
        evidenceRecord.setCaseId(evidenceToAdd.getCaseId();
        evidenceRecord.setTimeStamp(evidenceToAdd.getTimeStamp());
        evidenceRecord.setEvidenceId(evidenceToAdd.getEvidenceId().toString());
        evidenceRecord.setLocation(evidenceRecord.getLocation());
        evidenceRecord.setAuthor(evidenceRecord.getAuthor());
        evidenceRecord.setDescription(evidenceRecord.getDescription());
        evidenceRecord.setTimeDate(evidenceRecord.getTimeDate());

        return evidenceToAdd;
    }
}
