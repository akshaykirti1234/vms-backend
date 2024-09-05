package tech.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tech.csm.entity.Candidate;

import java.util.List;

public interface CandidateRepo extends JpaRepository<Candidate, Integer> {

    @Query("from Candidate where deletedFlag = false")
    List<Candidate> getAllCandidates();

    @Modifying
    @Query("update Candidate set deletedFlag = true where candidateId = :candidateId")
    Integer deleteCandidate(Integer candidateId);

    @Query("from Candidate where post.postId = :postId and deletedFlag = false")
    List<Candidate> getCandidateByPostId(Integer postId);
}
