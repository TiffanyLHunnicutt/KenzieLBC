package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.EvidenceRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface EvidenceRepository extends CrudRepository<EvidenceRecord, String> {
    List<EvidenceRecord> findByCaseId(String caseId);
}
