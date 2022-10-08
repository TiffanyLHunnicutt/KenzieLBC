package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.EvidenceRepository;
import com.kenzie.appserver.repositories.model.EvidenceRecord;
import com.kenzie.appserver.service.model.Evidence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EvidenceService {
    private EvidenceRepository evidenceRepository;

    @Autowired
    public EvidenceService(EvidenceRepository evidenceRepository) {
        this.evidenceRepository = evidenceRepository;
    }

    public List<Evidence> findAllEvidenceForCase(String caseId) {
        List<Evidence> evidence = new ArrayList<>();

        List<EvidenceRecord> evidenceRecords = evidenceRepository.findByCaseId(caseId);
        for (EvidenceRecord record : evidenceRecords) {
            evidence.add(new Evidence(UUID.fromString(record.getCaseId()),
                    UUID.fromString(record.getEvidenceId()),
                    record.getTimeStamp(),
                    record.getLocation(),
                    record.getTimeDate(),
                    record.getAuthor(),
                    record.getDescription()));
        }

        return evidence;
    }

    public Evidence findEvidenceByEvidenceId(String evidenceId) {
        Evidence evidenceFromRepository = evidenceRepository.findById(evidenceId)
                .map(evidence -> new Evidence(UUID.fromString(evidence.getCaseId()),
                        UUID.fromString(evidence.getEvidenceId()),
                        evidence.getTimeStamp(),
                        evidence.getLocation(),
                        evidence.getTimeDate(),
                        evidence.getAuthor(),
                        evidence.getDescription()))
                .orElse(null);

        return evidenceFromRepository;
    }

    public Evidence addNewEvidence(Evidence evidenceToAdd) {
        EvidenceRecord evidenceRecord = new EvidenceRecord();
        evidenceRecord.setCaseId(evidenceToAdd.getCaseId().toString());
        evidenceRecord.setEvidenceId(evidenceToAdd.getEvidenceId().toString());
        evidenceRecord.setTimeStamp(evidenceToAdd.getTimeStamp());
        evidenceRecord.setLocation(evidenceToAdd.getLocation());
        evidenceRecord.setTimeDate(evidenceToAdd.getTimeDate());
        evidenceRecord.setAuthor(evidenceToAdd.getAuthor());
        evidenceRecord.setDescription(evidenceToAdd.getDescription());
        evidenceRepository.save(evidenceRecord);
        return evidenceToAdd;
    }
}
