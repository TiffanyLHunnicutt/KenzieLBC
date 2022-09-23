package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.CaseRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface CaseRepository extends CrudRepository<CaseRecord, String> {
}
