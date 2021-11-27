package com.cade.api.v1.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {
    private String id;
    private String email;
    private String name;
    private String givenName;
    private String familyName;
}
