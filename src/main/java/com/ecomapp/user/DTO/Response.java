package com.ecomapp.user.DTO;

import lombok.Data;

@Data
public class Response {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private AddressDTO address;
}
