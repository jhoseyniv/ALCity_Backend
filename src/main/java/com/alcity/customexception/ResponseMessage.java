package com.alcity.customexception;

import com.alcity.entity.alenum.ErrorType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class ResponseMessage implements Serializable {
    private ErrorType type;
    private String status; //ok or error
    private String entity;
    private Long recordId;
    private String  message;

    public ResponseMessage(ErrorType type, String status, String entity, Long recordId, String message) {
        this.type = type;
        this.status = status;
        this.entity = entity;
        this.recordId = recordId;
        this.message = message;
    }
}
