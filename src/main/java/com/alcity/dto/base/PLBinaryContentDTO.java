package com.alcity.dto.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor

public class PLBinaryContentDTO implements Serializable {

    private Long id;
    private String fileName;
    private Integer size;
    private String contentType;
    private Boolean is3dContent;
    private String deviceType;


    public PLBinaryContentDTO(Long id, String fileName, Integer size, String contentType, Boolean is3dContent, String deviceType) {
        this.id = id;
        this.fileName = fileName;
        this.size = size;
        this.contentType = contentType;
        this.is3dContent = is3dContent;
        this.deviceType = deviceType;
    }
}
