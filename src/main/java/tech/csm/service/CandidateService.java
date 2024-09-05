package tech.csm.service;

import tech.csm.dto.CandidateDto;
import tech.csm.entity.Candidate;

import java.util.List;

public interface CandidateService {
    List<Candidate> getAllCandidates();

    Candidate getCandidateById(Integer candidateId);

    Candidate createCandidate(CandidateDto candidateDto);

    Integer deleteCandidate(Integer candidateId);

    List<Candidate> getCandidateByPostId(Integer postId);
}
