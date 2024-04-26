package com.amtenu.controller;


import com.amtenu.models.User;
import com.amtenu.repository.UserRepository;
import com.amtenu.service.CustomUserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailImpl customUserDetails;

    @PostMapping("/signup")
    public ResponseEntity<User>createdUserHandler(@RequestBody User user) throws Exception {
        User userExists = userRepository.findByEmail(user.getEmail());
        if (userExists != null) {
            throw new Exception("Email is already exists ");
        }

        User createdUser=new User();
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());

        User savedUser=userRepository.save(createdUser);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

}
