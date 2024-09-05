package tech.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.csm.entity.Vote;

import java.util.List;
import java.util.Map;

public interface VoteRepo extends JpaRepository<Vote, Integer> {

    @Query("select count(voteId) from Vote where user.userId = :userId and post.postId = :postId")
    Integer checkUserVoteOrNot(Integer userId, Integer postId);

    @Query(value = "SELECT p.post_name, u.user_name AS candidate_name, vote_counts.vote_count FROM (SELECT v.post_id, v.candidate_id, COUNT(v.vote_id) AS vote_count FROM vote v GROUP BY v.post_id, v.candidate_id) AS vote_counts JOIN candidate c ON vote_counts.candidate_id = c.candidate_id JOIN post p ON vote_counts.post_id = p.post_id JOIN user u ON c.user_id = u.user_id WHERE (vote_counts.post_id, vote_counts.vote_count) IN (SELECT post_id, MAX(vote_count) FROM (SELECT v.post_id, v.candidate_id, COUNT(v.vote_id) AS vote_count FROM vote v GROUP BY v.post_id, v.candidate_id) AS subquery GROUP BY post_id);", nativeQuery = true)
    List<Map<String, Object>> getwinners();
}
