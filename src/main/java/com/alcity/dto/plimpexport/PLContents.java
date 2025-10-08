package com.alcity.dto.plimpexport;

import com.alcity.dto.base.PLBinaryContentDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor

public class PLContents implements Serializable {
    Collection<PLBinaryContentDTO> contents;

}
