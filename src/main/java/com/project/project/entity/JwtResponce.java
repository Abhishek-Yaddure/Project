package com.project.project.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponce {

    private String jwtToken;
    private String userName;
}
