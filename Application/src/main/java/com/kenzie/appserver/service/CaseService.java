package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.CaseRepository;
import com.kenzie.appserver.repositories.model.CaseRecord;
import com.kenzie.appserver.repositories.model.ExampleRecord;
import com.kenzie.appserver.service.model.Case;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CaseService {
    private CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public List<Case> findAllOpenCases() {
        List<Case> case = new ArrayList<>();

        Iterable<CaseRecord> caseRecordIterable = caseRepository.findAll();
        for (CaseRecord record : caseRecordIterable) {
            record.add(new Case(record.getCaseId(),
                    record.getTimeStamp(),
                    record.getTitle(),
                    record.getAuthor(),
                    record.getDescription(),
                    record.getLocation(),
                    record.getPotentialSuspects(),
                    record.getOpenCase();
        }

        return case;
    }

    public Case findCaseByCaseId(String id) {
        Optional<CaseRecord> optionalRecord = caseRepository.findById(id);

        if (optionalRecord.isPresent()) {
            CaseRecord record = optionalRecord.get();
            return new CaseRecord(record.getCaseId(id));
        } else {
            return null;
        }
    }

    public Case addNewCase(Case caseToAdd) {
        CaseRecord caseRecord = new CaseRecord();
        caseRecord.setCaseId(caseRecord.getCaseId());
        caseRecord.setTimeStamp(caseRecord.getTimeStamp());
        caseRecord.setTitle(caseRecord.getTitle());
        caseRecord.setAuthor(caseRecord.getAuthor());
        caseRecord.setDescription(caseRecord.getDescription());
        caseRecord.setLocation(caseRecord.getLocation());
        caseRecord.setTimeDate(caseRecord.getTimeDate());
        caseRecord.setPotentialSuspects(caseRecord.getPotentialSuspects());
        caseRecord.setOpenCase(caseRecord.getOpenCase());
        caseRepository.save(caseRecord);
        return caseToAdd;
    }


    public void updateCase(Case caseToUpdate) {
        CaseRecord caseRecord = new CaseRecord();
        caseRecord.setCaseId(caseRecord.getCaseId());
        caseRecord.setTimeStamp(caseRecord.getTimeStamp());
        caseRecord.setTitle(caseRecord.getTitle());
        caseRecord.setAuthor(caseRecord.getAuthor());
        caseRecord.setDescription(caseRecord.getDescription());
        caseRecord.setLocation(caseRecord.getLocation());
        caseRecord.setTimeDate(caseRecord.getTimeDate());
        caseRecord.setPotentialSuspects(caseRecord.getPotentialSuspects());
        caseRecord.setOpenCase(caseRecord.getOpenCase());
        caseRepository.save(caseRecord);
    }
}
