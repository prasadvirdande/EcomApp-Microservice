package com.ecomapp.user.Service;

import com.ecomapp.user.DTO.Request;
import com.ecomapp.user.DTO.Response;

import java.util.List;

public interface USerService {
    List<Response> getallUser();

    Response createUser(Request user);

    void deleteUser(Long id);
}
