package com.example.timecapsule.Controller;

import com.example.timecapsule.Service.UserService;
import com.example.timecapsule.Entity.User;
import com.example.timecapsule.UserDTO;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final String SECRET = "RmV2dDJDZzJ5MkVma1B4R3lNdE1qYzBHRnBzYklBUTA=";

    @Autowired
    private UserService userService;

    @GetMapping(value="/users/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    //register a new user
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> register (@RequestBody User user) {
        userService.saveUser(user);
        System.out.println("User added: " + user.getName());
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    //authenticate(log in) user and generate JWT
    @PostMapping("/authenticate")
    public Map<String, String> authenticate(@RequestBody Map<String, String> credentials) throws JOSEException {
        String name = credentials.get("name");
        String password = credentials.get("password");
        System.out.println("log in input " + name + password);

        User user = userService.findByName(name);
        System.out.println(name);
        if (user != null && userService.checkPassword(password, user.getPassword())) {
            //JWT
            JWSSigner signer = new MACSigner(SECRET);
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(name)
                    .claim("userId", user.getId())
                    .issuer("https://example.com")
                    .expirationTime(new Date(new Date().getTime() + 60 * 60 * 1000)) // 1 hour validity
                    .build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            String jwt = signedJWT.serialize();
            System.out.println("JWT " + jwt);
            return Map.of("token", jwt);
        } else {
            return Map.of("error", "Invalid name or pw");
        }

    }
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }
}