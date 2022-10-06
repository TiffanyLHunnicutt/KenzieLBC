package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.EvidenceRepository;
import com.kenzie.appserver.repositories.model.EvidenceRecord;
import com.kenzie.appserver.service.model.Case;
import com.kenzie.appserver.service.model.Evidence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class EvidenceServiceTest {
    private EvidenceRepository evidenceRepository;
    private EvidenceService evidenceService;

    @BeforeEach
    void setUp() {
        evidenceRepository = mock(EvidenceRepository.class);
        evidenceService = new EvidenceService(evidenceRepository);
    }

    @Test
    void findAllEvidenceForCase() {
        // GIVEN
        String caseId = UUID.randomUUID().toString();

        EvidenceRecord record = new EvidenceRecord();
        record.setCaseId(caseId);
        record.setEvidenceId(UUID.randomUUID().toString());
        record.setTimeStamp(LocalDateTime.now().toString());
        record.setLocation("main street");
        record.setTimeDate("The time is now");
        record.setAuthor("me, myself, and I");
        record.setDescription("I have nothing to help your case, sorry.");

        EvidenceRecord record1 = new EvidenceRecord();
        record1.setCaseId(caseId);
        record1.setEvidenceId(UUID.randomUUID().toString());
        record1.setTimeStamp(LocalDateTime.now().toString());
        record1.setLocation("Over There!");
        record.setTimeDate("August 25");
        record.setAuthor("anonymous");
        record.setDescription("I found your stolen car! someone tried to sell it to me");

        List<EvidenceRecord> recordList = new ArrayList<>();
        recordList.add(record);
        recordList.add(record1);
        when(evidenceRepository.findByCaseId(caseId)).thenReturn(recordList);

        // WHEN
        List<Evidence> evidences = evidenceService.findAllEvidenceForCase(caseId);

        // THEN
        Assertions.assertNotNull(evidences, "The evidence list is returned");
        Assertions.assertEquals(evidences.size(), 2, "Expected the list to have 2 entries");
        verify(evidenceRepository).findByCaseId(caseId);

        for(Evidence evidence : evidences) {
            if (record.getEvidenceId().equals(evidence.getEvidenceId().toString())) {
                Assertions.assertEquals(record.getCaseId(), evidence.getCaseId().toString(),
                        "Expected the caseIds to match");
                Assertions.assertEquals(record.getTimeStamp(), evidence.getTimeStamp(),
                        "Expected timestamps to match");
                Assertions.assertEquals(record.getLocation(), evidence.getLocation(),
                        "Expected locations to match");
                Assertions.assertEquals(record.getTimeDate(), evidence.getTimeDate(),
                        "Expected time dates to match");
                Assertions.assertEquals(record.getAuthor(), evidence.getAuthor(),
                        "Expected Authors to match");
                Assertions.assertEquals(record.getDescription(), evidence.getDescription(),
                        "Expected descriptions to match");
            } else if (record1.getEvidenceId().equals(evidence.getEvidenceId().toString())) {
                Assertions.assertEquals(record1.getCaseId(), evidence.getCaseId().toString(),
                        "Expected the caseIds to match");
                Assertions.assertEquals(record1.getTimeStamp(), evidence.getTimeStamp(),
                        "Expected timestamps to match");
                Assertions.assertEquals(record1.getLocation(), evidence.getLocation(),
                        "Expected locations to match");
                Assertions.assertEquals(record1.getTimeDate(), evidence.getTimeDate(),
                        "Expected time dates to match");
                Assertions.assertEquals(record1.getAuthor(), evidence.getAuthor(),
                        "Expected Authors to match");
                Assertions.assertEquals(record1.getDescription(), evidence.getDescription(),
                        "Expected descriptions to match");
            } else {
                Assertions.assertTrue(false, "Evidence returned that was not in the records!");
            }
        }
    }

    @Test
    void findEvidenceByEvidenceId_evidence_found() {
        // GIVEN
        String evidenceId = UUID.randomUUID().toString();

        EvidenceRecord record = new EvidenceRecord();
        record.setCaseId(UUID.randomUUID().toString());
        record.setEvidenceId(evidenceId);
        record.setTimeStamp(LocalDateTime.now().toString());
        record.setLocation("los santos");
        record.setTimeDate("sept 17, 2013");
        record.setAuthor("michael");
        record.setDescription("I was the one who stole your car.");

        when(evidenceRepository.findById(evidenceId)).thenReturn(Optional.of(record));

        // WHEN
        Evidence evidenceReturned = evidenceService.findEvidenceByEvidenceId(evidenceId);

        // THEN
        verify(evidenceRepository).findById(evidenceId);
        Assertions.assertNotNull(evidenceReturned, "The evidence is returned");

        Assertions.assertEquals(record.getEvidenceId(), evidenceReturned.getEvidenceId().toString(),
                "Expected the evidenceIds to match");
        Assertions.assertEquals(record.getCaseId(), evidenceReturned.getCaseId().toString(),
                "Expected the caseIds to match");
        Assertions.assertEquals(record.getTimeStamp(), evidenceReturned.getTimeStamp(),
                "Expected timestamps to match");
        Assertions.assertEquals(record.getLocation(), evidenceReturned.getLocation(),
                "Expected locations to match");
        Assertions.assertEquals(record.getTimeDate(), evidenceReturned.getTimeDate(),
                "Expected time dates to match");
        Assertions.assertEquals(record.getAuthor(), evidenceReturned.getAuthor(),
                "Expected Authors to match");
        Assertions.assertEquals(record.getDescription(), evidenceReturned.getDescription(),
                "Expected descriptions to match");
    }

    @Test
    void findEvidenceByEvidenceId_evidence_does_not_exist() {
        // GIVEN
        String notLegitId = randomUUID().toString();
        when(evidenceRepository.findById(notLegitId)).thenReturn(Optional.empty());

        // WHEN
        Evidence noEvidence = evidenceService.findEvidenceByEvidenceId(notLegitId);

        // THEN
        Assertions.assertNull(noEvidence, "The service should have returned null");
        verify(evidenceRepository).findById(notLegitId);
    }

    @Test
    void addNewEvidence() {
        // GIVEN
        Evidence evidence = new Evidence(randomUUID(),
                randomUUID(),
                LocalDateTime.now().toString(),
                "here",
                "now",
                "them",
                "this");

        ArgumentCaptor<EvidenceRecord> recordCaptor = ArgumentCaptor.forClass(EvidenceRecord.class);

        // WHEN
        evidenceService.addNewEvidence(evidence);

        // THEN
        verify(evidenceRepository).save(recordCaptor.capture());
        EvidenceRecord captureEvidence = recordCaptor.getValue();

        Assertions.assertNotNull(captureEvidence, "evidence should not be null");
        Assertions.assertEquals(evidence.getEvidenceId().toString(), captureEvidence.getEvidenceId(),
                "Expected the evidenceIds to match");
        Assertions.assertEquals(evidence.getCaseId().toString(), captureEvidence.getCaseId(),
                "Expected the caseIds to match");
        Assertions.assertEquals(evidence.getTimeStamp(), captureEvidence.getTimeStamp(),
                "Expected timestamps to match");
        Assertions.assertEquals(evidence.getLocation(), captureEvidence.getLocation(),
                "Expected locations to match");
        Assertions.assertEquals(evidence.getTimeDate(), captureEvidence.getTimeDate(),
                "Expected time dates to match");
        Assertions.assertEquals(evidence.getAuthor(), captureEvidence.getAuthor(),
                "Expected Authors to match");
        Assertions.assertEquals(evidence.getDescription(), captureEvidence.getDescription(),
                "Expected descriptions to match");
    }
}
