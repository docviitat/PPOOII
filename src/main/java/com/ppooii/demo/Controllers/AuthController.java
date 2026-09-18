package com.ppooii.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ppooii.demo.Config.JWTAuthenticationConfig;
import com.ppooii.demo.Config.Model.JwtRequest;
import com.ppooii.demo.Config.Model.JwtResponse;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private JWTAuthenticationConfig jwtAuthenticationConfig;

    @RequestMapping(
            value = "/api/auth/authenticate",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

        String token = jwtAuthenticationConfig.authenticateAndGetToken(
                authenticationRequest.getLogin(),
                authenticationRequest.getPassword()
        );

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login o password invalidos.");
        }

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
