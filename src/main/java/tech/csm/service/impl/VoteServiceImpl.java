package tech.csm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.csm.dto.VoteDto;
import tech.csm.entity.Candidate;
import tech.csm.entity.Post;
import tech.csm.entity.User;
import tech.csm.entity.Vote;
import tech.csm.repository.VoteRepo;
import tech.csm.service.VoteService;

import java.util.List;
import java.util.Map;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepo voteRepo;

    @Override
    public Integer checkUserVoteOrNot(Integer userId, Integer postId) {
        return voteRepo.checkUserVoteOrNot(userId, postId);
    }

    @Override
    public Vote saveVote(VoteDto voteDto) {
        Vote vote = new Vote();
        User user = new User();
        Post post = new Post();
        Candidate candidate = new Candidate();

        user.setUserId(voteDto.getUserId());
        candidate.setCandidateId(voteDto.getCandidateId());
        post.setPostId(voteDto.getPostId());

        vote.setVoteId(voteDto.getVoteId());
        vote.setUser(user);
        vote.setCandidate(candidate);
        vote.setPost(post);

        return voteRepo.save(vote);
    }

    @Override
    public List<Map<String, Object>> getWinners() {
        return voteRepo.getwinners();
    }
}
