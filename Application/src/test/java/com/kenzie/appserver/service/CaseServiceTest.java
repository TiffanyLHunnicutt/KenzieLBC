package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.CaseRepository;
import com.kenzie.appserver.repositories.model.CaseRecord;
import com.kenzie.appserver.service.model.Case;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CaseServiceTest {
    private CaseRepository caseRepository;
    private CaseService caseService;

    @BeforeEach
    void setUp() {
        caseRepository = mock(CaseRepository.class);
        caseService = new CaseService(caseRepository);
    }

    @Test
    void findAllOpenCases_three_open_cases() {
        // GIVEN
        List<String> suspects = new ArrayList<>();
        suspects.add("Tiffany");
        suspects.add("Macherie");
        suspects.add("Tati");
        suspects.add("Elise");

        CaseRecord record1 = new CaseRecord();
        record1.setCaseId(randomUUID().toString());
        record1.setTimeStamp(LocalDateTime.now().toString());
        record1.setTitle("Missing Test File, Please Help");
        record1.setAuthor("anonymous");
        record1.setDescription("I can't seem to find where the test file ended up");
        record1.setLocation("Somewhere in the file structure");
        record1.setTimeDate("Just the other day");
        record1.setPotentialSuspects(suspects);
        record1.setOpenCase(true);

        CaseRecord record2 = new CaseRecord();
        record2.setCaseId(randomUUID().toString());
        record2.setTimeStamp(LocalDateTime.now().toString());
        record2.setTitle("Doggo Doggo Where Did You Go?");
        record2.setAuthor("Tati");
        record2.setDescription("The doggos are missing! They are fluffy and cute, collar has a heart with phone number");
        record2.setLocation("In the neighborhood");
        record2.setTimeDate("Yesterday");
        record2.setPotentialSuspects(new ArrayList<>());
        record2.setOpenCase(true);

        suspects.add("the fry cook");

        CaseRecord record3 = new CaseRecord();
        record3.setCaseId(randomUUID().toString());
        record3.setTimeStamp(LocalDateTime.now().toString());
        record3.setTitle("Someone keeps stealing my lunch");
        record3.setAuthor("gourmet chef");
        record3.setDescription("I make really good food, but someone keeps taking it");
        record3.setLocation("in the kitchen");
        record3.setTimeDate("one hour ago");
        record3.setPotentialSuspects(suspects);
        record3.setOpenCase(true);

        List<CaseRecord> recordList = new ArrayList<>();
        recordList.add(record1);
        recordList.add(record2);
        recordList.add(record3);
        when(caseRepository.findAll()).thenReturn(recordList);

        // WHEN
        List<Case> cases = caseService.findAllOpenCases();

        // THEN
        Assertions.assertNotNull(cases, "The case list is returned");
        Assertions.assertEquals(3, cases.size(), "There are three open cases");
        verify(caseRepository).findAll();

        for (Case caseReturned : cases) {
            if (record1.getCaseId().equals(caseReturned.getCaseId().toString())) {
                Assertions.assertEquals(record1.getTimeStamp(), caseReturned.getTimeStamp(),
                        "Timestamps should match");
                Assertions.assertEquals(record1.getTitle(), caseReturned.getTitle(),
                        "The case names should match");
                Assertions.assertEquals(record1.getAuthor(), caseReturned.getAuthor(),
                        "The authors should match");
                Assertions.assertEquals(record1.getDescription(), caseReturned.getDescription(),
                        "The descriptions should match");
                Assertions.assertEquals(record1.getLocation(), caseReturned.getLocation(),
                        "The locations should match");
                Assertions.assertEquals(record1.getTimeDate(), caseReturned.getTimeDate(),
                        "The timeDates should match");
                Assertions.assertEquals(record1.getOpenCase(), caseReturned.getOpenCase(),
                        "The openCase values should match");
                Assertions.assertTrue(caseReturned.getOpenCase(),
                        "The list should only have open cases");

                List<String> recordSuspects = record1.getPotentialSuspects();
                List<String> returnedSuspects = caseReturned.getPotentialSuspects();
                Assertions.assertEquals(recordSuspects.size(), 4,
                        "The suspect lists should be the same size of 4");
                Assertions.assertEquals(returnedSuspects.size(), 4,
                        "The suspect lists should be the same size of 4");
                for(String suspect : returnedSuspects) {
                    if(!(suspect.equals(recordSuspects.get(0)) || suspect.equals(recordSuspects.get(1)) ||
                    suspect.equals(recordSuspects.get(2)) || suspect.equals(recordSuspects.get(3)))) {
                        fail("The suspects in the list did not all match");
                    }
                }
            } else if (record2.getCaseId().equals(caseReturned.getCaseId().toString())) {
                Assertions.assertEquals(record2.getTimeStamp(), caseReturned.getTimeStamp(),
                        "Timestamps should match");
                Assertions.assertEquals(record2.getTitle(), caseReturned.getTitle(),
                        "The case names should match");
                Assertions.assertEquals(record2.getAuthor(), caseReturned.getAuthor(),
                        "The authors should match");
                Assertions.assertEquals(record2.getDescription(), caseReturned.getDescription(),
                        "The descriptions should match");
                Assertions.assertEquals(record2.getLocation(), caseReturned.getLocation(),
                        "The locations should match");
                Assertions.assertEquals(record2.getTimeDate(), caseReturned.getTimeDate(),
                        "The timeDates should match");
                Assertions.assertEquals(record2.getOpenCase(), caseReturned.getOpenCase(),
                        "The openCase values should match");
                Assertions.assertTrue(caseReturned.getOpenCase(),
                        "The list should only have open cases");
                Assertions.assertTrue(record2.getPotentialSuspects().isEmpty(),
                        "The suspect list should be empty");
                Assertions.assertTrue(caseReturned.getPotentialSuspects().isEmpty(),
                        "The suspect list should be empty");
            } else if (record3.getCaseId().equals(caseReturned.getCaseId().toString())) {
                Assertions.assertEquals(record3.getTimeStamp(), caseReturned.getTimeStamp(),
                        "Timestamps should match");
                Assertions.assertEquals(record3.getTitle(), caseReturned.getTitle(),
                        "The case names should match");
                Assertions.assertEquals(record3.getAuthor(), caseReturned.getAuthor(),
                        "The authors should match");
                Assertions.assertEquals(record3.getDescription(), caseReturned.getDescription(),
                        "The descriptions should match");
                Assertions.assertEquals(record3.getLocation(), caseReturned.getLocation(),
                        "The locations should match");
                Assertions.assertEquals(record3.getTimeDate(), caseReturned.getTimeDate(),
                        "The timeDates should match");
                Assertions.assertEquals(record3.getOpenCase(), caseReturned.getOpenCase(),
                        "The openCase values should match");
                Assertions.assertTrue(caseReturned.getOpenCase(),
                        "The list should only have open cases");

                List<String> recordSuspects = record3.getPotentialSuspects();
                List<String> returnedSuspects = caseReturned.getPotentialSuspects();
                Assertions.assertEquals(recordSuspects.size(), 5,
                        "The suspect lists should be the same size of 5");
                Assertions.assertEquals(returnedSuspects.size(), 5,
                        "The suspect lists should be the same size of 5");
                for(String suspect : returnedSuspects) {
                    if(!(suspect.equals(recordSuspects.get(0)) || suspect.equals(recordSuspects.get(1)) ||
                            suspect.equals(recordSuspects.get(2)) || suspect.equals(recordSuspects.get(3)) ||
                            suspect.equals(recordSuspects.get(4)))) {
                        fail("The suspects in the list did not all match");
                    }
                }
            } else {
                Assertions.assertTrue(false, "Case returned that was not in the records!");
            }
        }
    }

    @Test
    void findAllOpenCases_one_open_case_one_closed_case() {
        // GIVEN
        List<String> suspects = new ArrayList<>();
        suspects.add("Tiffany");
        suspects.add("Macherie");
        suspects.add("Tati");
        suspects.add("Elise");

        CaseRecord record1 = new CaseRecord();
        record1.setCaseId(randomUUID().toString());
        record1.setTimeStamp(LocalDateTime.now().toString());
        record1.setTitle("SOMEONE STOLE MY MINECRAFT ACCOUNT AND I WANT IT BACK");
        record1.setAuthor("anonymous");
        record1.setDescription("my account got hacked. username: twinkletwinklelittlestar");
        record1.setLocation("on my really cool minecraft server");
        record1.setTimeDate("two hours ago");
        record1.setPotentialSuspects(new ArrayList<>());
        record1.setOpenCase(true);

        CaseRecord record2 = new CaseRecord();
        record2.setCaseId(randomUUID().toString());
        record2.setTimeStamp(LocalDateTime.now().toString());
        record2.setTitle("Case Closed");
        record2.setAuthor("Elise");
        record2.setDescription("Nothing to see here");
        record2.setLocation("nowhere");
        record2.setTimeDate("didn't happen");
        record2.setPotentialSuspects(suspects);
        record2.setOpenCase(false);

        List<CaseRecord> recordList = new ArrayList<>();
        recordList.add(record1);
        recordList.add(record2);
        when(caseRepository.findAll()).thenReturn(recordList);

        // WHEN
        List<Case> cases = caseService.findAllOpenCases();

        // THEN
        Assertions.assertNotNull(cases, "The case list is returned");
        Assertions.assertEquals(1, cases.size(), "There is one open case");
        verify(caseRepository).findAll();

        for (Case caseReturned : cases) {
            if (record1.getCaseId().equals(caseReturned.getCaseId().toString())) {
                Assertions.assertEquals(record1.getTimeStamp(), caseReturned.getTimeStamp(),
                        "Timestamps should match");
                Assertions.assertEquals(record1.getTitle(), caseReturned.getTitle(),
                        "The case names should match");
                Assertions.assertEquals(record1.getAuthor(), caseReturned.getAuthor(),
                        "The authors should match");
                Assertions.assertEquals(record1.getDescription(), caseReturned.getDescription(),
                        "The descriptions should match");
                Assertions.assertEquals(record1.getLocation(), caseReturned.getLocation(),
                        "The locations should match");
                Assertions.assertEquals(record1.getTimeDate(), caseReturned.getTimeDate(),
                        "The timeDates should match");
                Assertions.assertEquals(record1.getOpenCase(), caseReturned.getOpenCase(),
                        "The openCase values should match");
                Assertions.assertTrue(caseReturned.getOpenCase(),
                        "The list should only have open cases");
                Assertions.assertTrue(record1.getPotentialSuspects().isEmpty(),
                        "The suspect list should be empty");
                Assertions.assertTrue(caseReturned.getPotentialSuspects().isEmpty(),
                        "The suspect list should be empty");
            } else {
                Assertions.assertTrue(false, "Case returned that was not in the records!");
            }
        }
    }

    @Test
    void findCaseByCaseId_case_found() {
        // GIVEN
        List<String> suspects = new ArrayList<>();
        suspects.add("Scarlemange");
        suspects.add("Doctor Amelia");

        String caseId = randomUUID().toString();

        CaseRecord record = new CaseRecord();
        record.setCaseId(caseId);
        record.setTimeStamp(LocalDateTime.now().toString());
        record.setTitle("Lost Dad");
        record.setAuthor("kipo");
        record.setDescription("I can't find my dad");
        record.setLocation("las Vistas");
        record.setTimeDate("It's been a while");
        record.setPotentialSuspects(suspects);
        record.setOpenCase(true);
        when(caseRepository.findById(caseId)).thenReturn(Optional.of(record));

        // WHEN
        Case caseMatch = caseService.findCaseByCaseId(caseId);

        // THEN
        verify(caseRepository).findById(caseId);

        Assertions.assertNotNull(caseMatch, "The case is returned");
        Assertions.assertEquals(record.getCaseId(), caseMatch.getCaseId().toString(),
                "The case ids match");
        Assertions.assertEquals(record.getTimeStamp(), caseMatch.getTimeStamp(),
                "Timestamps should match");
        Assertions.assertEquals(record.getTitle(), caseMatch.getTitle(),
                "The case titles should match");
        Assertions.assertEquals(record.getAuthor(), caseMatch.getAuthor(),
                "The authors should match");
        Assertions.assertEquals(record.getDescription(), caseMatch.getDescription(),
                "The descriptions should match");
        Assertions.assertEquals(record.getLocation(), caseMatch.getLocation(),
                "The locations should match");
        Assertions.assertEquals(record.getTimeDate(), caseMatch.getTimeDate(),
                "The timeDates should match");
        Assertions.assertEquals(record.getOpenCase(), caseMatch.getOpenCase(),
                "The openCase values should match");

        List<String> recordSuspects = record.getPotentialSuspects();
        List<String> matchSuspects = caseMatch.getPotentialSuspects();
        Assertions.assertEquals(recordSuspects.size(), 2,
                "The suspect lists should be the same size of 2");
        Assertions.assertEquals(matchSuspects.size(), 2,
                "The suspect lists should be the same size of 2");
        for(String suspect : matchSuspects) {
            if(!(suspect.equals(recordSuspects.get(0)) || suspect.equals(recordSuspects.get(1)))) {
                fail("The suspects in the list did not all match");
            }
        }
    }

    @Test
    void findCaseByCaseId_case_does_not_exist() {
        // GIVEN
        String notLegitId = randomUUID().toString();
        when(caseRepository.findById(notLegitId)).thenReturn(Optional.empty());

        // WHEN
        Case caseMatch = caseService.findCaseByCaseId(notLegitId);

        // THEN
        Assertions.assertNull(caseMatch, "The service should have returned null");
        verify(caseRepository).findById(notLegitId);
    }

    @Test
    void addNewCase_case_is_added() {
        // GIVEN
        Case caseToAdd = new Case(randomUUID(),
                LocalDateTime.now().toString(),
                "Test title",
                "test author",
                "Nothing to see here, just a test",
                "nowhere",
                "never",
                new ArrayList<>(),
                true);

        ArgumentCaptor<CaseRecord> recordCaptor = ArgumentCaptor.forClass(CaseRecord.class);

        // WHEN
        caseService.addNewCase(caseToAdd);

        // THEN
        verify(caseRepository).save(recordCaptor.capture());
        CaseRecord captureCase = recordCaptor.getValue();

        Assertions.assertNotNull(captureCase, "case should not be null");
        Assertions.assertEquals(caseToAdd.getCaseId().toString(), captureCase.getCaseId(),
                "case Ids should match");
        Assertions.assertEquals(caseToAdd.getTimeStamp(), captureCase.getTimeStamp(),
                "time stamps should match");
        Assertions.assertEquals(caseToAdd.getTitle(), captureCase.getTitle(),
                "titles should match");
        Assertions. assertEquals(caseToAdd.getAuthor(), captureCase.getAuthor(),
                "authors should match");
        Assertions.assertEquals(caseToAdd.getDescription(), captureCase.getDescription(),
                "descriptions should match");
        Assertions.assertEquals(caseToAdd.getLocation(), captureCase.getLocation(),
                "locations should match");
        Assertions.assertEquals(caseToAdd.getTimeDate(), captureCase.getTimeDate(),
                "time date should match");
        Assertions.assertTrue(captureCase.getPotentialSuspects().isEmpty(),
                "potential suspects should have been blank");
        Assertions.assertEquals(caseToAdd.getOpenCase(), captureCase.getOpenCase(),
                "both cases should be open");
    }

    @Test
    void updateCase_case_updates() {
        // GIVEN
        Case caseToUpdate = new Case(randomUUID(),
                LocalDateTime.now().toString(),
                "Test title",
                "test author",
                "Nothing to see here, just a test",
                "nowhere",
                "never",
                new ArrayList<>(),
                true);

        when(caseRepository.existsById(caseToUpdate.getCaseId().toString())).thenReturn(true);
        ArgumentCaptor<CaseRecord> recordCaptor = ArgumentCaptor.forClass(CaseRecord.class);

        // WHEN
        caseService.updateCase(caseToUpdate);

        // THEN
        verify(caseRepository).existsById(caseToUpdate.getCaseId().toString());
        verify(caseRepository).save(recordCaptor.capture());
        CaseRecord captureCase = recordCaptor.getValue();

        Assertions.assertNotNull(captureCase, "case should not be null");
        Assertions.assertEquals(caseToUpdate.getCaseId().toString(), captureCase.getCaseId(),
                "case Ids should match");
        Assertions.assertEquals(caseToUpdate.getTimeStamp(), captureCase.getTimeStamp(),
                "time stamps should match");
        Assertions.assertEquals(caseToUpdate.getTitle(), captureCase.getTitle(),
                "titles should match");
        Assertions. assertEquals(caseToUpdate.getAuthor(), captureCase.getAuthor(),
                "authors should match");
        Assertions.assertEquals(caseToUpdate.getDescription(), captureCase.getDescription(),
                "descriptions should match");
        Assertions.assertEquals(caseToUpdate.getLocation(), captureCase.getLocation(),
                "locations should match");
        Assertions.assertEquals(caseToUpdate.getTimeDate(), captureCase.getTimeDate(),
                "time date should match");
        Assertions.assertTrue(captureCase.getPotentialSuspects().isEmpty(),
                "potential suspects should have been blank");
        Assertions.assertEquals(caseToUpdate.getOpenCase(), captureCase.getOpenCase(),
                "both cases should be open");
    }

    @Test
    void udateCase_not_legit_id_does_not_update() {
        // GIVEN
        Case caseToUpdate = new Case(randomUUID(),
                LocalDateTime.now().toString(),
                "Test title",
                "test author",
                "Nothing to see here, just a test",
                "nowhere",
                "never",
                new ArrayList<>(),
                true);

        when(caseRepository.existsById(caseToUpdate.getCaseId().toString())).thenReturn(false);

        // WHEN
        caseService.updateCase(caseToUpdate);

        // THEN
        verify(caseRepository).existsById(caseToUpdate.getCaseId().toString());
        verify(caseRepository, times(0)).save(any());
    }

    @Test
    void deleteCase() {
        // GIVEN
        String legitCaseId = randomUUID().toString();

        // WHEN
        caseService.deleteCase(legitCaseId);

        // THEN
        verify(caseRepository).deleteById(legitCaseId);
    }
}