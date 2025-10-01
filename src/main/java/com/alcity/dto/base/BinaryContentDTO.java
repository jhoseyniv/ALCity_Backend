package com.alcity.dto.base;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor

public class BinaryContentDTO implements Serializable {

    private Long id;
    private String fileName;
    private Integer size;
    private byte[] content;
    private byte[] ios3Dcontent;
    private byte[] android3Dcontent;
    private byte[] web3Dcontent;
    private byte[] thumbnail;

    private String contentType;

    private Boolean is3dContent;

    private String tag1;
    private String tag2;
    private String tag3;

    public BinaryContentDTO(Long id,
                            String fileName, Integer size, byte[] content, byte[] thumbnail,
                            byte[] ios3Dcontent , byte[] android3Dcontent , byte[] web3Dcontent,
                            String contentType, Boolean is3dContent, String tag1, String tag2, String tag3) {
        this.id = id;
        this.fileName = fileName;
        this.size = size;
        this.content = content;
        this.ios3Dcontent = ios3Dcontent;
        this.android3Dcontent =android3Dcontent;
        this.web3Dcontent = web3Dcontent;
        this.thumbnail = thumbnail;
        this.contentType = contentType;
        this.is3dContent = is3dContent;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }
}
