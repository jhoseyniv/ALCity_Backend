package com.alcity.dto.puzzle;

import com.alcity.entity.alenum.PLRuleEventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PLRuleEventDTO {

    private Long id;
    private String name;
    private String plRuleEventType;
    private Integer plRuleEventTypeId;
    public PLRuleEventDTO(Long id, String name, String plRuleEventType, Integer plRuleEventTypeId) {
        this.id = id;
        this.name = name;
        this.plRuleEventType = plRuleEventType;
        this.plRuleEventTypeId = plRuleEventTypeId;
    }
}
