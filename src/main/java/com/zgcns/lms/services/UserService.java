package com.zgcns.lms.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zgcns.lms.exception.UserNotFoundException;
import com.zgcns.lms.model.Employee;
import com.zgcns.lms.model.User;
import com.zgcns.lms.repositories.EmployeeRepository;
import com.zgcns.lms.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public User saveUser(User user) {
        Employee employee = user.getEmployee();
        if (employee == null) {
            employee = new Employee();
            user.setEmployee(employee);
            employee.setUser(user);
        }

        employee.setFirstName(user.getFirstName());
        employee.setLastName(user.getLastName());
        employee.setEmail(user.getEmail());
        // Set other employee fields if necessary

        // This will save both user and employee due to CascadeType.ALL
        return userRepository.save(user);
    }

    @Transactional
    public ResponseEntity<Map<String, String>> authenticateUser(User user) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("No user found for this email!");
        }

        User databaseUser = userOptional.get();

        // Check if passwords match
        if (!bCryptPasswordEncoder.matches(user.getPassword(), databaseUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of("error", "Invalid password")
            );
        }

        // Return user details if authentication is successful
        return ResponseEntity.ok(Map.of(
            "email", databaseUser.getEmail(),
            "role", databaseUser.getRole(),
            "userId", databaseUser.getUserId().toString()
        ));
    }

    @Transactional
    public String addUser(User user) {
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        User savedUser = saveUser(user);
        return savedUser.getEmail() + " added to database successfully";
    }

    @Transactional
    public User updateUser(Long userId, User user) {
        User updatedUser = userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException("No user found with userId: " + userId)
        );

        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return saveUser(updatedUser);
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
            () -> new UserNotFoundException("No user found with email: " + email)
        );
    }

    public User getUserByUserId(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException("No user found with userId: " + userId)
        );
    }

    public User getUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
