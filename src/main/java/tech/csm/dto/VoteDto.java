package tech.csm.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class VoteDto {
    private Integer voteId;
    private Integer userId;
    private Integer postId;
    private Integer candidateId;
    private Integer createdBy;
    private Integer updatedBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Boolean deletedFlag;
}
