package com.alcity.dto.appmember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AppMemberNotificationDTO  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private StringBuffer notificationCode;

    public AppMemberNotificationDTO(Long id, StringBuffer notificationCode) {
        this.id = id;
        this.notificationCode = notificationCode;
    }
}
