package com.ecomapp.user.Service;


import com.ecomapp.user.DTO.AddressDTO;
import com.ecomapp.user.DTO.Request;
import com.ecomapp.user.DTO.Response;
import com.ecomapp.user.Model.Address;
import com.ecomapp.user.Model.User;
import com.ecomapp.user.Repository.USerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements USerService {

    private final USerRepository userRepo;

    @Override
    public List<Response> getallUser() {
        return userRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public Response createUser(Request user) {

        Optional<User> user1 = userRepo.findByEmail(user.getEmail());
        if (user1.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user2 = new User();
        user2.setName(user.getFirstname());
        user2.setLastname(user.getLastname());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setPhone(user.getPhone());

        if (user.getAddress() != null) {
            Address address = new Address();
            address.setStreet(user.getAddress().getStreet());
            address.setCity(user.getAddress().getCity());
            address.setState(user.getAddress().getState());
            address.setCountry(user.getAddress().getCountry());
            address.setZipCode(user.getAddress().getZipCode());

            user2.setAddress(address);
        }

        User savedUser = userRepo.save(user2);

        return mapToResponse(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepo.findById(id);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found with id " + id);
        }

        userRepo.deleteById(id);
    }

    private Response mapToResponse(User user) {

        Response response = new Response();
        response.setId(user.getId());
        response.setFirstname(user.getName());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());

        if (user.getAddress() != null) {
            AddressDTO adr = new AddressDTO();
            adr.setStreet(user.getAddress().getStreet());
            adr.setCity(user.getAddress().getCity());
            adr.setState(user.getAddress().getState());
            adr.setCountry(user.getAddress().getCountry());
            adr.setZipCode(user.getAddress().getZipCode());
            response.setAddress(adr);
        }

        return response;
    }
}
