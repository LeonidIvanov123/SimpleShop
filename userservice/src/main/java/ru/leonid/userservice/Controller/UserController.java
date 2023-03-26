package ru.leonid.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leonid.userservice.Service.TokenService;
import ru.leonid.userservice.Service.UserService;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${eureka.instance.instance-id}")
    private String instanceAplication;

    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    //registration
    @GetMapping("/{username}/{email}/{password}")
    public ResponseEntity<String> processRegistration(@PathVariable("username") String username,
                                                      @PathVariable("email") String email,
                                                      @PathVariable("password") String password) {
        return ResponseEntity.ok(userService.createUser(username, email, password));
    }

    //generate token for services with username & password
    @GetMapping("/token/{username}/{password}")
    public String getToken(@PathVariable String username,
                           @PathVariable String password) throws LoginException {
        userService.checkAuthority(username, password);
        return tokenService.generateToken(username);
    }

    @GetMapping("/upbalance/{user_id}/{balance}")
    public ResponseEntity<String> topUpBalance(@PathVariable("user_id") long user_id,
                                               @PathVariable("balance") long balance) {
        return ResponseEntity.ok(userService.upBalance(user_id, balance));
    }

    @GetMapping("/id/{user_id}")
    public ResponseEntity<String> getUserById(@PathVariable("user_id") long user_id) {
        return ResponseEntity.ok(userService.userById(user_id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllUser() {
        return ResponseEntity.ok(userService.allUsers());
    }

    ////
    @GetMapping("/test")
    public String testController() {
        return "request from #userservice#: " + instanceAplication;
    }
    /////

    @ExceptionHandler({LoginException.class})
    public ResponseEntity<String> handleUserRegistrationException(LoginException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage().toString());
    }
}