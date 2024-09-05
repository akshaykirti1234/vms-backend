package tech.csm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.csm.dto.CandidateDto;
import tech.csm.entity.Candidate;
import tech.csm.entity.Post;
import tech.csm.entity.User;
import tech.csm.repository.CandidateRepo;
import tech.csm.service.CandidateService;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepo candidateRepo;

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepo.getAllCandidates();
    }

    @Override
    public Candidate getCandidateById(Integer candidateId) {
        Optional<Candidate> optionalCandidate = candidateRepo.findById(candidateId);
        return optionalCandidate.orElseThrow(() -> new RuntimeException("Candidate not found with id :" + candidateId));
    }

    @Override
    public Candidate createCandidate(CandidateDto candidateDto) {

        User user = new User();
        Post post = new Post();

        user.setUserId(candidateDto.getUserId());
        post.setPostId(candidateDto.getPostId());

        Candidate candidate = new Candidate();
        candidate.setCandidateId(candidateDto.getCandidateId());
        candidate.setUser(user);
        candidate.setPost(post);
        if (user.getUserId() == null)
            candidate.setCreatedBy(candidateDto.getCreatedBy());
        else
            candidate.setUpdatedBy(candidateDto.getUpdatedBy());

        return candidateRepo.save(candidate);
    }

    @Override
    @Transactional
    public Integer deleteCandidate(Integer candidateId) {
        return candidateRepo.deleteCandidate(candidateId);
    }

    @Override
    public List<Candidate> getCandidateByPostId(Integer postId) {
        return candidateRepo.getCandidateByPostId(postId);
    }
}
