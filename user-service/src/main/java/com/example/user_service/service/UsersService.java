package com.example.user_service.service;

import com.example.user_service.entity.Users;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UsersService {

    void createUser(Users user);

    Users getUserById(Long id);

    List<Users> getUsers();

    void updateUser(Users user);

    void deleteUser(Long id);

}
