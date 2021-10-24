package com.example.ppmtool.web;

import javax.validation.Valid;

import com.example.ppmtool.domain.User;
import com.example.ppmtool.payload.JWTLoginSuccessResponse;
import com.example.ppmtool.payload.LoginRequest;
import com.example.ppmtool.security.JwtTokenProvider;
import com.example.ppmtool.security.SecurityConstants;
import com.example.ppmtool.services.MapValidationErrorService;
import com.example.ppmtool.services.UserService;
import com.example.ppmtool.validator.UserValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator UserValidator;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) return errorMap;

        logger.info("Login request username: {}", loginRequest.getUsername());
        UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword().toString());
        // UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken("aang@test.com", "yip yip");
        logger.info("UPA token generated");
        Authentication authentication = authenticationManager.authenticate(upaToken);

        logger.info("Accessing security context...");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        // Validate passwords match
        UserValidator.validate(user, result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }

        User newUser = userService.saveUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}