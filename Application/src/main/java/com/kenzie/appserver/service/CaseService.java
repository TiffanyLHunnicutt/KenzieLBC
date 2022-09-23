package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.CaseRepository;
import com.kenzie.appserver.service.model.Case;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaseService {
    private CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public List<Case> findAllOpenCases() {
        return new ArrayList<>();
    }

    public Case findCaseByCaseId(String id) {
        return null;
    }

    public Case addNewCase(Case caseToAdd) {
        return null;
    }

    public void updateCase(Case caseToUpdate) {
    }
}
