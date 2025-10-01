package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PLRuleDTO {
    private Long id;
    private String title;
    private Integer ordering;
    private Boolean ignoreRemaining;
    private StringBuffer condition;

    private Long puzzleLevelId;

    private String puzzleLeveTitle;

    private Long PLRuleEventId;

    private String  PLRuleEventName;
    private String  PLRuleSubEventName;

    private  Integer plRuleEventTypeId;
    private  String plRuleEventTypeTitle;

    public PLRuleDTO(Long id, String title, Integer ordering,Boolean ignoreRemaining, StringBuffer condition, Long puzzleLevelId, String puzzleLeveTitle, Long PLRuleEventId, String PLRuleEventName,String PLRuleSubEventName,
                     Integer plRuleEventTypeId, String plRuleEventTypeTitle) {
        this.id = id;
        this.title = title;
        this.ordering = ordering;
        this.ignoreRemaining = ignoreRemaining;
        this.condition = condition;
        this.puzzleLevelId = puzzleLevelId;
        this.puzzleLeveTitle = puzzleLeveTitle;
        this.PLRuleEventId = PLRuleEventId;
        this.PLRuleEventName = PLRuleEventName;
        this.PLRuleSubEventName = PLRuleSubEventName;
        this.plRuleEventTypeId = plRuleEventTypeId;
        this.plRuleEventTypeTitle = plRuleEventTypeTitle;
    }
}
