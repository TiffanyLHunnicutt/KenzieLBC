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
        List<Case> cases = new ArrayList<>();

        Iterable<CaseRecord> caseRecordIterable = caseRepository.findAll();
        for (CaseRecord record : caseRecordIterable) {
            cases.add(new Case(record.getCaseId(),
                    record.getTimeStamp(),
                    record.getTitle(),
                    record.getAuthor(),
                    record.getDescription(),
                    record.getLocation(),
                    record.getPotentialSuspects(),
                    record.getOpenCase()));
        }

        return cases;
    }
    public Case findCaseByCaseId(String caseId) {
        //implement cache and cache check later...
        Case casesFromRepository = caseRepository.findById(caseId)
                .map(cases->new Case(cases.getCaseId()))
                .orElse(null);

        //implement cache if found then cache logic.
        return casesFromRepository;
    }


    public Case addNewCase(Case caseToAdd) {
        CaseRecord caseRecord = new CaseRecord();
        caseRecord.setCaseId(caseToAdd.getCaseId());
        caseRecord.setTimeStamp(caseToAdd.getTimeStamp());
        caseRecord.setTitle(caseToAdd.getTitle());
        caseRecord.setAuthor(caseToAdd.getAuthor());
        caseRecord.setDescription(caseToAdd.getDescription());
        caseRecord.setLocation(caseToAdd.getLocation());
        caseRecord.setTimeDate(caseToAdd.getTimeDate());
        caseRecord.setPotentialSuspects(caseToAdd.getPotentialSuspects());
        caseRecord.setOpenCase(caseToAdd.getOpenCase());
        caseRepository.save(caseRecord);
        return caseToAdd;
    }


    public void updateCase(Case caseToUpdate) {
        if (caseRepository.existsById(caseToUpdate.getCaseId())) {
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

    public void deleteCase(String caseId){
        caseRepository.deleteById(caseId);
    }
}
