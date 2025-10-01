package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PLCopyDTO {
    private String title;
    private String code;
    private Integer fromAge;
    private Integer toAge;
    private  Long puzzleLevelId;

    private  Boolean plGround;
    private  Boolean objective;
    private  Boolean instance;
    private  Boolean variables;
    private  Boolean rules;
    private  Boolean learningTopics;



    public PLCopyDTO(String title, String code, Integer fromAge, Integer toAge, Long puzzleLevelId,
                     Boolean plGround, Boolean objective, Boolean instance, Boolean variables, Boolean rules, Boolean learningTopics
                     ) {
        this.title = title;
        this.code = code;
        this.fromAge = fromAge;
        this.toAge = toAge;
        this.puzzleLevelId = puzzleLevelId;
        this.plGround = plGround;
        this.objective = objective;
        this.instance = instance;
        this.variables = variables;
        this.rules = rules;
        this.learningTopics = learningTopics;
    }

}
