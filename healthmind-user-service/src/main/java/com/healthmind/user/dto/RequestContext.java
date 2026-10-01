package com.healthmind.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestContext {

    private UUID requestID;
    private Instant requestTime;
    private String clientIp;
    private String userAgent;
    private String correlationID;

}
