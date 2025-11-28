package com.ecomapp.user.DTO;

import lombok.Data;

@Data
public class Request {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private AddressDTO address;

}
