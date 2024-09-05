package tech.csm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.csm.dto.VoteDto;
import tech.csm.entity.Candidate;
import tech.csm.entity.Vote;
import tech.csm.service.CandidateService;
import tech.csm.service.VoteService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/vote")
public class CastVoteController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private VoteService voteService;

    @GetMapping("/getCandidateByPostId/{postId}")
    public ResponseEntity<?> getUserByPostId(@PathVariable Integer postId) {
        if (postId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            List<Candidate> candidateList = candidateService.getCandidateByPostId(postId);
            return new ResponseEntity<>(candidateList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/saveVote")
    public ResponseEntity<?> saveVote(@RequestBody VoteDto voteDto) {
        Integer count = voteService.checkUserVoteOrNot(voteDto.getUserId(), voteDto.getPostId());
        if (count > 0)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Vote vote = voteService.saveVote(voteDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
