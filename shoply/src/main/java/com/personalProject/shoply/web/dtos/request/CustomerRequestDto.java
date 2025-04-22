package com.personalProject.shoply.web.dtos.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerRequestDto {
    private Long id;
    private String fullName;
    private String phoneNumber;
}
