package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.EvidenceRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface EvidenceRepository extends CrudRepository<EvidenceRecord, String> {
}
