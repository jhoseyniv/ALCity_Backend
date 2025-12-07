package com.alcity.entity.challenge;


import com.alcity.entity.alenum.NotifType;
import com.alcity.entity.alenum.UserGender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="title" )
    private String title;

    @Column(name="message" )
    private String message;

    @Enumerated(EnumType.ORDINAL)
    private NotifType notifType;

    @Column(name="active" )
    private Boolean active;

    @Column(name="repeatNum" )
    private Integer repeatNum;

    @Column(name="sendRateHour" )
    private Integer sendRateHour;

    @Column(name="startTime" )
    private ZonedDateTime startTime;

    @Column(name="endTime" )
    private ZonedDateTime endTime;





}
