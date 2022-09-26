package com.kenzie.appserver.controller;

import com.kenzie.appserver.service.CaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cases")
public class CaseController {

    private CaseService caseService;

    CaseController(CaseService caseService) {
        this.caseService = caseService;
    }
}
