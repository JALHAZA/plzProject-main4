package com.lyj.securitydomo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RequestStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_status_id")
    private Long requestStatusId;

    @Column(nullable = false,length = 50)
    private String status;
}
