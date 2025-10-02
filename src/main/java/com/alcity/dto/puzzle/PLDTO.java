package com.alcity.dto.puzzle;

import com.alcity.dto.player.PermitedPlayerDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor
public class PLDTO {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;
    private String updatedBy;

    private String approveDate;
    private Long ordering;
    private String title;
    private String code;
    private Integer fromAge;
    private Integer toAge;

    private Integer row;

    private Integer col;
    private Float maxScore;
    private Float firstStarScore;
    private Float secondStarScore;
    private Float thirdStartScore;
    private Long puzzleGroupId;

    private String puzzleGroupTitle;

    private Long plGroundId;

    private String puzzleLevelStatus;

    private String puzzleLevelPrivacy;

    private String puzzleLevelDifficulty;

    private Long iconId;
    private Long picId;

    public PLDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,
                 String approveDate, Long plGroundId, Long puzzleGroupId, String puzzleGroupTitle, Long ordering, String title, String code, Integer fromAge, Integer toAge, Float maxScore, Float firstStarScore , Float secondStarScore, Float thirdStarScore, String puzzleLevelStatus, String puzzleLevelPrivacy, String puzzleLevelDifficulty) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.approveDate = approveDate;
        this.ordering = ordering;
        this.title = title;
        this.approveDate =approveDate;
        this.plGroundId = plGroundId;
        this.puzzleGroupId = puzzleGroupId;
        this.puzzleGroupTitle = puzzleGroupTitle;
        this.code = code;
        this.fromAge = fromAge;
        this.toAge = toAge;
        this.maxScore = maxScore;
        this.firstStarScore = firstStarScore;
        this.secondStarScore =secondStarScore;
        this.thirdStartScore = thirdStartScore;
        this.puzzleLevelStatus = puzzleLevelStatus;
        this.puzzleLevelPrivacy = puzzleLevelPrivacy;
        this.puzzleLevelDifficulty = puzzleLevelDifficulty;
    }
}
