package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor

public class CameraSetupDTO implements Serializable {
    private Long id;
    private String title;
    private Integer XPosition;
    private Integer YPosition;
    private Integer ZPosition;

    private Integer XRotation;
    private Integer YRotation;
    private Integer ZRotation;



    public CameraSetupDTO(Long id, String title, Integer XPosition, Integer YPosition, Integer ZPosition,
                          Integer XRotation, Integer YRotation, Integer ZRotation) {
        this.id = id;
        this.title = title;
        this.XPosition = XPosition;
        this.YPosition = YPosition;
        this.ZPosition = ZPosition;
        this.XRotation = XRotation;
        this.YRotation = YRotation;
        this.ZRotation = ZRotation;
    }

}