package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.entity.base.BaseTable;

public class PuzzleLevelDTO extends BaseTableDTO {

    private String approveDate;
    private Long ordering;
    private String title;
    private String code;
    private Integer fromAge;
    private Integer toAge;
    private Float maxScore;

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public Long getOrdering() {
        return ordering;
    }

    public void setOrdering(Long ordering) {
        this.ordering = ordering;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getFromAge() {
        return fromAge;
    }

    public void setFromAge(Integer fromAge) {
        this.fromAge = fromAge;
    }

    public Integer getToAge() {
        return toAge;
    }

    public void setToAge(Integer toAge) {
        this.toAge = toAge;
    }

    public Float getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Float maxScore) {
        this.maxScore = maxScore;
    }

    public PuzzleLevelDTO() {
    }

    public PuzzleLevelDTO(Long id, Long version, String created, String updated, String approveDate, Long ordering, String title, String code, Integer fromAge, Integer toAge, Float maxScore) {
        super(id, version, created, updated);
        this.approveDate = approveDate;
        this.ordering = ordering;
        this.title = title;
        this.code = code;
        this.fromAge = fromAge;
        this.toAge = toAge;
        this.maxScore = maxScore;
    }
}
