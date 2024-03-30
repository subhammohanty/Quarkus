package org.acme.dto;

import lombok.Data;

@Data
public class CitizenDto {

    private Long id;
    private String fullName;
    private String address;
    private String pinCode;
    private String gender;
}
