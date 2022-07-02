package com.cade.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Content {
    private String titleName;
    private LocalDate releasedDate;
}
