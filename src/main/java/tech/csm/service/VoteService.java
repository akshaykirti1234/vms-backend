package tech.csm.service;

import tech.csm.dto.VoteDto;
import tech.csm.entity.Vote;

import java.util.List;
import java.util.Map;

public interface VoteService {
    Integer checkUserVoteOrNot(Integer userId, Integer postId);

    Vote saveVote(VoteDto voteDto);

    List<Map<String, Object>> getWinners();
}
