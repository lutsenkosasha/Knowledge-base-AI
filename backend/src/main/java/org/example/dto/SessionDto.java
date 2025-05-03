package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionDto {
    private Long sessionId;
    private String userName;
    private String directoryName;

}
