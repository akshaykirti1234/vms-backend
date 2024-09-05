package tech.csm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.csm.dto.CandidateDto;
import tech.csm.entity.Candidate;
import tech.csm.service.CandidateService;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/candidate")
@RestController
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/getAllCandidates")
    public ResponseEntity<?> getAllCandidates() {
        List<Candidate> candidateList = candidateService.getAllCandidates();
        return new ResponseEntity<>(candidateList, HttpStatus.OK);
    }

    @PutMapping("/getCandidateById/{candidateId}")
    public ResponseEntity<?> getCandidateById(@PathVariable Integer candidateId) {
        try {
            if (candidateId == null)
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

            Candidate candidate = candidateService.getCandidateById(candidateId);
            if (candidate != null) {
                return new ResponseEntity<>(candidate, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/createCandidate")
    public ResponseEntity<?> createCandidate(@RequestBody CandidateDto candidateDto) {
        try {
            Candidate candidate = candidateService.createCandidate(candidateDto);
            if (candidate != null) {
                return new ResponseEntity<>(candidate, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deleteCandidate/{candidateId}")
    public ResponseEntity<?> deleteCandidate(@PathVariable Integer candidateId) {
        try {
            if (candidateId == null)
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

            Integer count = candidateService.deleteCandidate(candidateId);
            if (count > 0) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
