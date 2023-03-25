package ru.leonid.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.leonid.userservice.Model.User;
import ru.leonid.userservice.Model.UserRepository;
import ru.leonid.userservice.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${eureka.instance.instance-id}")
    private String instanceAplication;

    @Autowired
    UserService userService;

    @GetMapping("/{username}/{email}/{password}")
    public User processRegistration(@PathVariable("username") String username,
                                    @PathVariable("email") String email,
                                    @PathVariable("password") String password){
        return userService.createUser(username,email,password);
    }
    @GetMapping("/upbalance/{user_id}/{balance}")
    public String topUpBalance(@PathVariable("user_id")long user_id,
                               @PathVariable("balance")long balance){
        return userService.upBalance(user_id, balance);
    }


    //for admin
    @GetMapping("/id/{user_id}")
    public String getUserById(@PathVariable("user_id")long user_id){
        return userService.userById(user_id);
    }

    @GetMapping("/getAll")
    public String getAllUser(){
        return userService.allUsers();
    }

    ////
    @GetMapping("/test")
    public String testController(){
        return "request from #userservice#: " + instanceAplication;
    }
    /////
}
