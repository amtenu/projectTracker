package com.amtenu.controller;


import com.amtenu.config.JwtProvider;
import com.amtenu.models.User;
import com.amtenu.repository.UserRepository;
import com.amtenu.request.LoginRequest;
import com.amtenu.response.AuthResponse;
import com.amtenu.service.CustomUserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<AuthResponse>createdUserHandler(@RequestBody User user) throws Exception {
        User userExists = userRepository.findByEmail(user.getEmail());
        if (userExists != null) {
            throw new Exception("Email is already exists ");
        }

        User createdUser=new User();
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());

        User savedUser=userRepository.save(createdUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        String jwt= JwtProvider.generateToken(authentication);

        AuthResponse res=new AuthResponse();
        res.setMessage("Signup success");
        res.setJwt(jwt);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin (@RequestBody LoginRequest loginRequest){
        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();

        Authentication authentication=autenticate(username,password);
        String jwt= JwtProvider.generateToken(authentication);

        AuthResponse res=new AuthResponse();
        res.setMessage("Signin success");
        res.setJwt(jwt);

        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }

    private Authentication autenticate(String username, String password) {
        UserDetails userDetails=customUserDetails.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid Username or password");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Username or password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
