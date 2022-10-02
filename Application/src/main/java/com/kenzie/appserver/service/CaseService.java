package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.CaseRepository;
import com.kenzie.appserver.repositories.model.CaseRecord;
import com.kenzie.appserver.service.model.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CaseService {
    private CaseRepository caseRepository;

    @Autowired
    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public List<Case> findAllOpenCases() {
        List<Case> cases = new ArrayList<>();

        Iterable<CaseRecord> caseRecordIterable = caseRepository.findAll();
        for (CaseRecord record : caseRecordIterable) {
            //a check to see if the case is open
            if(record.getOpenCase()) {
                cases.add(new Case(UUID.fromString(record.getCaseId()),
                        record.getTimeStamp(),
                        record.getTitle(),
                        record.getAuthor(),
                        record.getDescription(),
                        record.getLocation(),
                        record.getTimeDate(),
                        record.getPotentialSuspects(),
                        record.getOpenCase()));
            }
        }

        return cases;
    }

    public Case findCaseByCaseId(String caseId) {
        //potentially implement cache and cache check later with cache if found then cache logic...
        Case casesFromRepository = caseRepository.findById(caseId)
                .map(caseMatch -> new Case(UUID.fromString(caseMatch.getCaseId()),
                        caseMatch.getTimeStamp(),
                        caseMatch.getTitle(),
                        caseMatch.getAuthor(),
                        caseMatch.getDescription(),
                        caseMatch.getLocation(),
                        caseMatch.getTimeDate(),
                        caseMatch.getPotentialSuspects(),
                        caseMatch.getOpenCase() ))
                .orElse(null);
        return casesFromRepository;
    }

    public Case addNewCase(Case caseToAdd) {
        CaseRecord caseRecord = new CaseRecord();
        caseRecord.setCaseId(caseToAdd.getCaseId().toString());
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
        if (caseRepository.existsById(caseToUpdate.getCaseId().toString())) {
            CaseRecord caseRecord = new CaseRecord();
            caseRecord.setCaseId(caseToUpdate.getCaseId().toString());
            caseRecord.setTimeStamp(caseToUpdate.getTimeStamp());
            caseRecord.setTitle(caseToUpdate.getTitle());
            caseRecord.setAuthor(caseToUpdate.getAuthor());
            caseRecord.setDescription(caseToUpdate.getDescription());
            caseRecord.setLocation(caseToUpdate.getLocation());
            caseRecord.setTimeDate(caseToUpdate.getTimeDate());
            caseRecord.setPotentialSuspects(caseToUpdate.getPotentialSuspects());
            caseRecord.setOpenCase(caseToUpdate.getOpenCase());
            caseRepository.save(caseRecord);
        }
    }

    public void deleteCase(String caseId){
        caseRepository.deleteById(caseId);
    }
}
