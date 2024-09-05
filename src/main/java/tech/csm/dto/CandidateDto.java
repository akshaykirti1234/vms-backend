package tech.csm.dto;

import lombok.Data;

@Data
public class CandidateDto {
    private Integer candidateId;
    private Integer postId;
    private Integer userId;
    private Integer createdBy;
    private Integer updatedBy;
}
