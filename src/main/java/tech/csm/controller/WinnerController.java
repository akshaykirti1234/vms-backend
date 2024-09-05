package tech.csm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.csm.service.VoteService;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/winner")
public class WinnerController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/getWinners")
    public ResponseEntity<?> getWinners() {
        List<Map<String, Object>> winners = voteService.getWinners();
        return new ResponseEntity<>(winners, HttpStatus.OK);
    }

}
