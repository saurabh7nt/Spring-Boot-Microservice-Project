package com.example.user_service.serviceImpl;

import com.example.common.exception.ResourceNotFoundException;
import com.example.user_service.entity.Users;
import com.example.user_service.repository.UsersRepository;
import com.example.user_service.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void createUser(Users user) {
        user.setId(null);
        usersRepository.save(user);
    }

    @Override
    public Users getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void updateUser(Users user) {
        Users existingUser = usersRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
        existingUser.setCreatedAt(user.getCreatedAt());
        existingUser.setUpdatedAt(user.getUpdatedAt());
        existingUser.setActive(user.isActive());

        usersRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        Users existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        usersRepository.delete(existingUser);
    }
}
