package com.alcity.dto.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor

public class ThumbnailDTO  implements Serializable {
    private Long id;
    private String fileName;
    private Integer size;
    private byte[] thumbnail;

    public ThumbnailDTO(Long id, String fileName, Integer size, byte[] thumbnail) {
        this.id = id;
        this.fileName = fileName;
        this.size = size;
        this.thumbnail = thumbnail;
    }
}
